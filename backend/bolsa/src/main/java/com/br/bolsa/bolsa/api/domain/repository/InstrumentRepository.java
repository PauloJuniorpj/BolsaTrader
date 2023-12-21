package com.br.bolsa.bolsa.api.domain.repository;


import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.InstrumentQuote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InstrumentRepository extends JpaRepository<InstrumentQuote, Long> {

    @Query(value = "SELECT DISTINCT " +
            "   user_trade.id," +
            "    instrument_quote.date, " +
            "    instrument_quote.price, " +
            "    instrument_quote.simbol " +
            "FROM user_trade " +
            "INNER JOIN instrument_quote ON instrument_quote.simbol = user_trade.instrument " +
            " and instrument_quote.date = user_trade.data " +
            "WHERE " +
            "user_trade.id = ?1 "+
            "and user_trade.data = ?2 "+
            "and user_trade.instrument = ?3", nativeQuery = true)
        InstrumentQuote buscarAcoesfiltrado(Long id , LocalDate filter, String nomeSimbol);
}
