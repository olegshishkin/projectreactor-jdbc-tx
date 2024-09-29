package com.github.olegshishkin.application.service.reactive;

import com.github.olegshishkin.application.entity.Item;
import reactor.core.publisher.Mono;

public interface ReactiveSomeService {

    Mono<Item> addItem(String shopName, String itemName);
}
