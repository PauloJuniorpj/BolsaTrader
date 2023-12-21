package com.br.bolsa.bolsa.api.domain.entity;

import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeDto;
import com.querydsl.core.annotations.QueryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@QueryEntity
@Table(name = "user_trade")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data")
    private LocalDate dataOperacao;
    @Column(name = "tipo_operacao")
    private String tipoOperacao;
    @Column(name="mercado")
    private String mercado;
    @Column(name = "prazo")
    private String praso;
    @Column(name = "instrument")
    private String instrument;
    @Column(name = "especificacao")
    private String especificacao;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Column(name = "preco")
    private BigDecimal preco;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    public UserTrade(UserTradeDto tradeDto) {
        this.id = tradeDto.getId();
        this.dataOperacao = tradeDto.getDataOperacao();
        this.tipoOperacao = tradeDto.getTipoOperacao();
        this.mercado = tradeDto.getMercado();
        this.praso = tradeDto.getPraso();
        this.instrument = tradeDto.getInstrument();
        this.especificacao = tradeDto.getEspecificacao();
        this.quantidade = tradeDto.getQuantidade();
        this.preco = BigDecimal.valueOf(Long.parseLong(tradeDto.getPreco()));
        this.valorTotal = BigDecimal.valueOf(Long.parseLong(tradeDto.getValorTotal()));
    }
}
