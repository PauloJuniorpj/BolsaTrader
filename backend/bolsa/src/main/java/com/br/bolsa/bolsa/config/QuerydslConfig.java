package com.br.bolsa.bolsa.config;

import com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL.InstrumentCustomImpl;
import com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL.InstrumentRepositoryCustom;
import com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL.UserTradeCustom;
import com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL.UserTradeRepositoryCustom;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

    @Bean
    public UserTradeRepositoryCustom userTradeRepositoryCustom(EntityManager entityManager) {
        return new UserTradeCustom(entityManager);
    }

    @Bean
    public InstrumentRepositoryCustom instrumentRepositoryCustom(EntityManager entityManager) {
        return new InstrumentCustomImpl(entityManager) {
        };
    }
}
