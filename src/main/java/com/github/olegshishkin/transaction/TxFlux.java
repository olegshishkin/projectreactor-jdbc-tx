package com.github.olegshishkin.transaction;

import java.util.function.Supplier;
import java.util.stream.Stream;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

public class TxFlux {

    private static TransactionalDelegate delegate;

    public TxFlux(TransactionalDelegate delegate) {
        TxFlux.delegate = delegate;
    }

    public static <T> Flux<T> fromStream(Supplier<Stream<? extends T>> supplier) {
        return Flux.fromStream(new TransactionalSupplier<>(supplier, delegate))
                .subscribeOn(Schedulers.boundedElastic());
    }
}
