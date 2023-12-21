package com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL;

import com.br.bolsa.bolsa.api.domain.entity.Dtos.InstrumentFilter;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.InstrumentQuote;
import com.br.bolsa.bolsa.api.domain.entity.UserTrade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface InstrumentRepositoryCustom {
    public Page<InstrumentQuote> buscaFiltradaPaginada(InstrumentFilter filter, Pageable pageable);

}
