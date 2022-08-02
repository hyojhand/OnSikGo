package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.*;
import com.ssafy.onsikgo.entity.Item;
import com.ssafy.onsikgo.entity.Sale;
import com.ssafy.onsikgo.entity.SaleItem;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.repository.ItemRepository;
import com.ssafy.onsikgo.repository.SaleItemRepository;
import com.ssafy.onsikgo.repository.SaleRepository;
import com.ssafy.onsikgo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ResponseEntity<String> register(SaleItemDto saleItemDto, Long store_id) {

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        String time = now.format(timeFormatter);
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dayFormatter);

        Store findStore = storeRepository.findById(store_id).get();
        List<Sale> saleList = saleRepository.findByStoreOrderByDateDesc(findStore);
        if(saleList.size() == 0 || !saleList.get(0).getDate().equals(date)) {
            Sale sale = Sale.builder()
                    .date(date)
                    .totalPrice(0)
                    .closed(false)
                    .store(findStore)
                    .build();

            saleRepository.save(sale);
        }

        saleItemDto.setTotalStock(saleItemDto.getStock());
        Sale sale = saleRepository.findByStoreOrderByDateDesc(findStore).get(0);
        Item findItem = itemRepository.findById(saleItemDto.getItemId()).get();

        SaleItem saleItem = saleItemDto.toEntity(findItem,sale);
        saleItemRepository.save(saleItem);
        return new ResponseEntity<>("세일상품 등록완료", HttpStatus.OK);
    }


    public ResponseEntity<SaleResultDto> getSaleResult(HashMap<String, String> map, Long store_id) {

        String date = map.get("date");

        Optional<Store> findStore = storeRepository.findById(store_id);
        if(!findStore.isPresent()) {
            return new ResponseEntity<>(new SaleResultDto(), HttpStatus.NO_CONTENT);
        }

        Optional<Sale> findSale = saleRepository.findByStoreAndDate(findStore.get(), date);
        if(!findSale.isPresent()) {
            return new ResponseEntity<>(new SaleResultDto(), HttpStatus.NO_CONTENT);
        }

        List<SaleItem> saleItemList = saleItemRepository.findBySale(findSale.get());

        StoreDto storeDto = findSale.get().getStore().toDto();
        SaleDto saleDto = findSale.get().toDto(storeDto);
        List<SaleItemDto> saleItemDtoList = new ArrayList<>();
        for(int i = 0; i < saleItemList.size(); i++) {
            SaleItem saleItem = saleItemList.get(i);
            ItemDto itemDto = saleItem.getItem().toDto();
            SaleItemDto saleItemDto = saleItemList.get(i).toDto(itemDto, saleDto);
            saleItemDtoList.add(saleItemDto);
        }

        SaleResultDto saleResultDto = new SaleResultDto();
        saleResultDto.setSaleDto(saleDto);
        saleResultDto.setSaleItemDtoList(saleItemDtoList);

        return new ResponseEntity<>(saleResultDto,HttpStatus.OK);
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

        findSaleItem.get().update(map.get("stock"));
        saleItemRepository.save(findSaleItem.get());
        return new ResponseEntity<>("재고 수정완료" ,HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> delete(Long sale_item_id) {

        SaleItem findSaleItem = saleItemRepository.findById(sale_item_id).get();
        saleItemRepository.delete(findSaleItem);
        return new ResponseEntity<>("재고 상품 삭제 완료" ,HttpStatus.OK);
    }

    public ResponseEntity<SaleItemDto> getSaleItemInfo(Long item_id) {
        Optional<Item> findItem = itemRepository.findById(item_id);

        Optional<SaleItem> findSaleItem = saleItemRepository.findByItem(findItem.get());
        if(!findSaleItem.isPresent()) {
            Sale findSale = findSaleItem.get().getSale();
            Store findStore = findSale.getStore();
            SaleItemDto saleItemDto = new SaleItemDto();
            saleItemDto.setItemDto(findItem.get().toDto());
            saleItemDto.setSaleDto(findSale.toDto(findStore.toDto()));

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
        if(Integer.parseInt(time) < 6) {
            now = now.minusDays(1);
        }

        DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String date = now.format(dayFormatter);

        List<SaleItem> saleItemList = saleItemRepository.findByDateAndItemNameContainingAndClosedFalse(date, selectDto.getKeyword());

        List<SaleItemDto> saleItemDtoList = new ArrayList<>();
        for(int i = 0; i < saleItemList.size(); i++) {
            SaleItem saleItem = saleItemList.get(i);
            Sale sale = saleItem.getSale();
            Store store = sale.getStore();
            ItemDto itemDto = saleItem.getItem().toDto();
            SaleItemDto saleItemDto = saleItemList.get(i).toDto(itemDto,sale.toDto(store.toDto()));
            saleItemDtoList.add(saleItemDto);
        }

        return new ResponseEntity<>(saleItemDtoList, HttpStatus.OK);
    }
}
