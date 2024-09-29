package com.github.olegshishkin.projectreactor.projectreactor.transaction;

import java.util.function.Supplier;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalDelegate {

    @Transactional
    public <T> T doInTransaction(Supplier<T> supplier) {
        return supplier.get();
    }
}
