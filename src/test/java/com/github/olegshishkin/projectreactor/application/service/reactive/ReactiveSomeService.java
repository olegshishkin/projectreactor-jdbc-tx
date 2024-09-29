package com.github.olegshishkin.projectreactor.application.service.reactive;

import com.github.olegshishkin.projectreactor.application.entity.Item;
import reactor.core.publisher.Mono;

public interface ReactiveSomeService {

    Mono<Item> addItem(String shopName, String itemName);
}
