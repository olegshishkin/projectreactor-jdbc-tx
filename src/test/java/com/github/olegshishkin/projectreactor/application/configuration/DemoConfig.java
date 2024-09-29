package com.github.olegshishkin.projectreactor.application.configuration;

import com.github.olegshishkin.projectreactor.projectreactor.transaction.TransactionalDelegate;
import com.github.olegshishkin.projectreactor.projectreactor.transaction.TxMono;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfig {

    @Bean
    public TransactionalDelegate transactionalDelegate() {
        return new TransactionalDelegate();
    }

    @Bean
    public TxMono txMono(TransactionalDelegate delegate) {
        return new TxMono(delegate);
    }
}
