package com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL;

import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeDto;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.QUserTrade;
import com.br.bolsa.bolsa.api.domain.entity.UserTrade;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public class UserTradeCustom  extends JPAQueryFactory implements UserTradeRepositoryCustom {

    public UserTradeCustom(EntityManager em) {
    super(em);
    }

    @Override
    public Page<UserTrade> buscaFiltradaPaginada(UserTradeFilter filter, Pageable pageable) {
        var query = createFiltros(filter);
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());
        // Execute a consulta e crie a lista de resultados
        var resultList = query.fetch();
        // Crie e retorne uma instância de Page com os resultados e informações de paginação
        return new PageImpl<>(resultList, pageable, query.fetchCount());
    }
    private JPQLQuery<UserTrade> createFiltros(UserTradeFilter filter) {
        QUserTrade qUserTrade = QUserTrade.userTrade;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (filter.getDataPassadaFilter() != null) {
            booleanBuilder.and(qUserTrade.dataOperacao.eq(filter.getDataPassadaFilter()));
        }
        if(filter.getInstrument() != null){
            booleanBuilder.and(qUserTrade.instrument.equalsIgnoreCase(filter.getInstrument()));
        }
        if (filter.getId() != null){
            booleanBuilder.and(qUserTrade.id.eq(filter.getId()));
        }
        if(filter.getEspecificacao() != null){
            booleanBuilder.and(qUserTrade.especificacao.equalsIgnoreCase(filter.getEspecificacao()));
        }
        if(filter.getTipoOperacao() != null){
            booleanBuilder.and(qUserTrade.tipoOperacao.equalsIgnoreCase(filter.getTipoOperacao()));
        }

        JPQLQuery<UserTrade> query =  select(qUserTrade).from(qUserTrade).where(booleanBuilder);
        return query;
    }

}

