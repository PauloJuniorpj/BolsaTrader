package com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL;

import com.br.bolsa.bolsa.api.domain.entity.Dtos.InstrumentFilter;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.InstrumentQuote;
import com.br.bolsa.bolsa.api.domain.entity.QInstrumentQuote;
import com.br.bolsa.bolsa.api.domain.entity.QUserTrade;
import com.br.bolsa.bolsa.api.domain.entity.UserTrade;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public class InstrumentCustomImpl extends JPAQueryFactory implements InstrumentRepositoryCustom{

    public InstrumentCustomImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Page<InstrumentQuote> buscaFiltradaPaginada(InstrumentFilter filter, Pageable pageable)   {
        var query = createFiltros(filter);
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        // Execute a consulta e crie a lista de resultados
        var resultList = query.fetch();
        // Crie e retorne uma instância de Page com os resultados e informações de paginação
        return new PageImpl<>(resultList, pageable, query.fetchCount());
}
    private JPQLQuery<InstrumentQuote> createFiltros(InstrumentFilter filter) {
        QInstrumentQuote instrumentQuote = QInstrumentQuote.instrumentQuote;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if(filter.getId() != null){
            booleanBuilder.and(instrumentQuote.id.eq(filter.getId()));
        }
        if (filter.getData() != null) {
            booleanBuilder.and(instrumentQuote.data.eq(filter.getData()));
        }
        if(filter.getSimbol() != null){
            booleanBuilder.and(instrumentQuote.simbol.contains(filter.getSimbol()));
        }
        JPQLQuery<InstrumentQuote> query =  select(instrumentQuote).from(instrumentQuote).where(booleanBuilder);
        return query;
    }
}
