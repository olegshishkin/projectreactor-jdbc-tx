package com.github.olegshishkin.application.service.reactive;

import com.github.olegshishkin.application.entity.Item;
import com.github.olegshishkin.application.repository.ItemRepository;
import com.github.olegshishkin.application.repository.ShopRepository;
import com.github.olegshishkin.transaction.TxMono;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReactiveSomeServiceImpl implements ReactiveSomeService {

    private final ShopRepository shopRepository;
    private final ItemRepository itemRepository;

    @Override
    public Mono<Item> addItem(String shopName, String itemName) {
        return TxMono.fromSupplier(() -> {
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
                })
                .doOnError(e -> log.error("Error on item adding: ", e))
                .doOnSuccess(i -> log.info("Successfully added item: {}", i));
    }
}
