package com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL;

import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.UserTrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTradeRepositoryCustom {
    public Page<UserTrade> buscaFiltradaPaginada(UserTradeFilter filter, Pageable pageable);
}
