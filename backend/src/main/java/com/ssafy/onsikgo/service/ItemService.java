package com.ssafy.onsikgo.service;

import com.ssafy.onsikgo.dto.ItemDto;
import com.ssafy.onsikgo.entity.Item;
import com.ssafy.onsikgo.entity.Store;
import com.ssafy.onsikgo.repository.ItemRepository;
import com.ssafy.onsikgo.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ResponseEntity<String> register(ItemDto itemDto, Long store_id) {

        Item item = itemDto.toEntity();
        Store findStore = storeRepository.findById(store_id).get();
        item.addStore(findStore);

        itemRepository.save(item);
        return new ResponseEntity<>("상품등록이 완료되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity<ItemDto> getInfo(Long item_id) {
        Optional<Item> findItem = itemRepository.findById(item_id);
        ItemDto itemDto = findItem.get().toDto();
        return new ResponseEntity<>(itemDto, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> delete(Long item_id) {
        Optional<Item> findItem = itemRepository.findById(item_id);
        itemRepository.delete(findItem.get());
        return new ResponseEntity<>("상품 삭제가 완료되었습니다." , HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> modify(ItemDto itemDto, Long item_id) {
        Item findItem = itemRepository.findById(item_id).get();
        findItem.update(itemDto);
        itemRepository.save(findItem);
        return new ResponseEntity<>("상품 수정이 완료되었습니다.",HttpStatus.OK);
    }

    public ResponseEntity<List<ItemDto>> getList(Long store_id) {
        Store findStore = storeRepository.findById(store_id).get();
        List<Item> items = findStore.getItems();
        List<ItemDto> itemDtos = new ArrayList<>();
        for(int i = 0; i < items.size(); i++) {
            itemDtos.add(items.get(i).toDto());
        }
        return new ResponseEntity<>(itemDtos, HttpStatus.OK);
    }
}
