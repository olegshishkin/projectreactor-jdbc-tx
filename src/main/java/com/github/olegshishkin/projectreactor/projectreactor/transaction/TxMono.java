package com.github.olegshishkin.projectreactor.projectreactor.transaction;

import java.util.function.Supplier;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class TxMono {

    private static TransactionalDelegate delegate;

    public TxMono(TransactionalDelegate delegate) {
        TxMono.delegate = delegate;
    }

    public static <T> Mono<T> fromSupplier(Supplier<T> supplier) {
        return Mono.fromSupplier(new TransactionalSupplier<>(supplier, delegate))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
