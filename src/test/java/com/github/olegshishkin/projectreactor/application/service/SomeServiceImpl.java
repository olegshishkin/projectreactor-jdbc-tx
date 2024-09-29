package com.github.olegshishkin.projectreactor.application.service;

import com.github.olegshishkin.projectreactor.application.entity.Item;
import com.github.olegshishkin.projectreactor.application.repository.ItemRepository;
import com.github.olegshishkin.projectreactor.application.repository.ShopRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class SomeServiceImpl implements SomeService {

    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;

    @Override
    public Item addItem(String shopName, String itemName) {
        var shopOpt = shopRepository.findByName(shopName);
        if (shopOpt.isEmpty()) {
            throw new IllegalArgumentException("Shop not found");
        }
        var shop = shopOpt.get();

        var item = itemRepository.findByName(itemName);
        if (item.isPresent()) {
            throw new RuntimeException("Item already exists");
        }

        shop.setItemCounter(shop.getItemCounter() + 1);
        return itemRepository.save(new Item(itemName, shop));
    }
}
