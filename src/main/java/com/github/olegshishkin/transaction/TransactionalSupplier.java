package com.github.olegshishkin.transaction;

import java.util.function.Supplier;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionalSupplier<T> implements Supplier<T> {

    private final Supplier<T> actual;
    private final TransactionalDelegate delegate;

    @Override
    public T get() {
        return delegate.doInTransaction(actual);
    }
}
