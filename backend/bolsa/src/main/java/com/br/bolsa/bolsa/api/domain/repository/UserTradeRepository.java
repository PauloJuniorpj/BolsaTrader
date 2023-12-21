package com.br.bolsa.bolsa.api.domain.repository;


import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeDto;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeDtoRendimentoAcumulado;
import com.br.bolsa.bolsa.api.domain.entity.UserTrade;
import com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL.UserTradeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserTradeRepository extends JpaRepository<UserTrade, Long> {
    @Query(value = "SELECT\n" +
            " id, " +
            "  data, " +
            "  tipo_operacao, " +
            "  mercado, " +
            "  prazo, " +
            "  instrument, " +
            "  especificacao, " +
            "  quantidade, " +
            "  preco, " +
            "  valor_total " +
            "FROM user_trade " +
            "where user_trade.data between ?1 and ?2 ", nativeQuery = true)
    List<UserTrade> rendimentoTotalAcomulado(LocalDate dataInicial, LocalDate dataFinal);
}
