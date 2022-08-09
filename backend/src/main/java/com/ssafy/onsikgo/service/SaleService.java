package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.*;
import com.ssafy.onsikgo.entity.*;
import com.ssafy.onsikgo.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemRepository saleItemRepository;
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;
    private final NoticeRepository noticeRepository;
    private final FollowRepository followRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public ResponseEntity<String> register(SaleItemDto saleItemDto, Long store_id) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter noticeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String date = now.format(dayFormatter);

        Item findItem = itemRepository.findById(saleItemDto.getItemId()).get();
        Store findStore = storeRepository.findById(store_id).get();
        Optional<Sale> todaySale = saleRepository.findByStoreAndDate(findStore, date);

        boolean firstSale = false;
        if(!todaySale.isPresent()) {
            Sale sale = Sale.builder()
                    .date(date)
                    .totalPrice(0)
                    .closed(false)
                    .store(findStore)
                    .build();
            saleRepository.save(sale);
            firstSale = true;
        }

        Optional<Sale> findSale = saleRepository.findByStoreAndDateAndClosedFalse(findStore, date);
        if(!findSale.isPresent()) {
            return new ResponseEntity<>("이미 영업종료했습니다." ,HttpStatus.NO_CONTENT);
        }
        Optional<SaleItem> findTodaySaleItem = saleItemRepository.findBySaleAndItem(findSale.get(), findItem);

        if(findTodaySaleItem.isPresent()) {
            return new ResponseEntity<>("할인상품이 존재합니다.", HttpStatus.NO_CONTENT);
        } else {
            saleItemDto.setTotalStock(saleItemDto.getStock());
//            Sale sale = saleRepository.findByStoreOrderByDateDesc(findStore).get(0);

            SaleItem saleItem = saleItemDto.toEntity(findItem,findSale.get());
            saleItemRepository.save(saleItem);

            if(firstSale) {
                String storeName = findStore.getStoreName();
                String content = storeName + " 에서<br/> 할인상품이 등록되었습니다.";
                Order order = new Order();
                order.updateNotice(now.format(noticeFormatter), saleItem, findStore.getUser());
                orderRepository.save(order);

                List<Follow> storeFollowList = followRepository.findByStore(findStore);

                for(Follow follow : storeFollowList) {
                    User user = follow.getUser();
                    Notice notice = new Notice(content,findStore.getUser(), order, user.getUserId(), NoticeState.STORE);
                    noticeRepository.save(notice);
                }
            }
        }

        return new ResponseEntity<>("세일상품 등록완료", HttpStatus.OK);
    }

    public ResponseEntity<List<SaleItemDto>> getSaleItemList(Long store_id) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dayFormatter);

        Optional<Store> findStore = storeRepository.findById(store_id);
        if(!findStore.isPresent()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        Optional<Sale> findSale = saleRepository.findByStoreAndDateAndClosedFalse(findStore.get(), date);
        if(!findSale.isPresent()) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
        }

        List<SaleItem> saleItemList = saleItemRepository.findBySale(findSale.get());

        StoreDto storeDto = findSale.get().getStore().toDto();
        SaleDto saleDto = findSale.get().toDto(storeDto);
        List<SaleItemDto> saleItemDtoList = new ArrayList<>();
        for(int i = 0; i < saleItemList.size(); i++) {
            SaleItem saleItem = saleItemList.get(i);
            ItemDto itemDto = saleItem.getItem().toDto();
            SaleItemDto saleItemDto = saleItemList.get(i).toDto(itemDto,saleDto);
            saleItemDtoList.add(saleItemDto);
        }

        return new ResponseEntity<>(saleItemDtoList, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> updateStock(HashMap<String,Integer> map, Long sale_item_id) {

        Optional<SaleItem> findSaleItem = saleItemRepository.findById(sale_item_id);
        if(!findSaleItem.isPresent()) {
            return new ResponseEntity<>("등록된 판매상품이 없습니다.", HttpStatus.NO_CONTENT);
        }

        if(findSaleItem.get().getSale().getClosed()) {
            return new ResponseEntity<>("오늘 영업이 종료되었습니다", HttpStatus.NO_CONTENT);
        }

        Integer initStock = findSaleItem.get().getStock();
        Integer stock = findSaleItem.get().getStock();
        if(map.get("stock") != null) {
            stock = map.get("stock");
        }

        Integer salePrice = findSaleItem.get().getSalePrice();
        if(map.get("salePrice") != null) {
            salePrice = map.get("salePrice");
        }

        Integer totalStock = findSaleItem.get().getTotalStock();
        totalStock += (stock-initStock);

        findSaleItem.get().update(stock, salePrice,totalStock);
        saleItemRepository.save(findSaleItem.get());
        return new ResponseEntity<>("재고 수정완료" ,HttpStatus.OK);
    }

    /**
     * 삭제대상
     */
    @Transactional
    public ResponseEntity<String> delete(Long sale_item_id) {

        SaleItem findSaleItem = saleItemRepository.findById(sale_item_id).get();
        saleItemRepository.delete(findSaleItem);
        return new ResponseEntity<>("재고 상품 삭제 완료" ,HttpStatus.OK);
    }

    public ResponseEntity<SaleItemDto> getSaleItemInfo(Long item_id) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dayFormatter);

        Optional<Item> findItem = itemRepository.findById(item_id);
        Store store = findItem.get().getStore();
        Optional<Sale> findSale = saleRepository.findByStoreAndDateAndClosedFalse(store, date);

        if(!findSale.isPresent()) {
            SaleItemDto saleItemDto = new SaleItemDto();
            saleItemDto.setItemDto(findItem.get().toDto());
            saleItemDto.setSalePrice(0);
            saleItemDto.setStock(0);
            return new ResponseEntity<>(saleItemDto, HttpStatus.OK);
        }

        Optional<SaleItem> findSaleItem = saleItemRepository.findBySaleAndItem(findSale.get(), findItem.get());
        if(!findSaleItem.isPresent()) {
            SaleItemDto saleItemDto = new SaleItemDto();
            saleItemDto.setItemDto(findItem.get().toDto());

            saleItemDto.setSalePrice(0);
            saleItemDto.setStock(0);
            return new ResponseEntity<>(saleItemDto, HttpStatus.OK);
        }

        SaleItemDto saleItemDto = findSaleItem.get().toSaleItemDto();
        return new ResponseEntity<>(saleItemDto, HttpStatus.OK);
    }

    public ResponseEntity<List<SaleItemDto>> getSaleItemKeyword(SelectDto selectDto) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if (Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dayFormatter);

        List<SaleItem> saleItemKeywordList = saleItemRepository.findSaleItemKeyword(selectDto.getKeyword(), date);
        List<SaleItemDto> saleItemDtoList = new ArrayList<>();
        for (int i = 0; i < saleItemKeywordList.size(); i++) {
            SaleItem saleItem = saleItemKeywordList.get(i);
            Sale sale = saleItem.getSale();
            Store store = sale.getStore();
            ItemDto itemDto = saleItem.getItem().toDto();
            SaleItemDto saleItemDto = saleItemKeywordList.get(i).toDto(itemDto, sale.toDto(store.toDto()));
            saleItemDtoList.add(saleItemDto);
        }

        return new ResponseEntity<>(saleItemDtoList, HttpStatus.OK);
    }
}
