package com.github.olegshishkin.application.configuration;

import com.github.olegshishkin.transaction.TransactionalDelegate;
import com.github.olegshishkin.transaction.TxMono;
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
