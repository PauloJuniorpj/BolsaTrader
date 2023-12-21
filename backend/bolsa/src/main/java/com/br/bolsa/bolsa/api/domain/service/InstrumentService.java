package com.br.bolsa.bolsa.api.domain.service;

import com.br.bolsa.bolsa.api.domain.entity.Dtos.InstrumentFilter;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.InstrumentQuote;
import com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL.InstrumentRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InstrumentService {

    private  InstrumentRepositoryCustom instrumentRepositoryCustom;

    public InstrumentService(InstrumentRepositoryCustom instrumentRepositoryCustom) {
        this.instrumentRepositoryCustom = instrumentRepositoryCustom;
    }



    public Page<InstrumentQuote> buscarInstrumentPaginadoFiltrado(InstrumentFilter filter, Pageable pageable) {
        return instrumentRepositoryCustom.buscaFiltradaPaginada(filter, pageable);
    }


}
