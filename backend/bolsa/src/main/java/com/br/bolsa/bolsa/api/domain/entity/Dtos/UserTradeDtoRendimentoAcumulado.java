package com.br.bolsa.bolsa.api.domain.entity.Dtos;

import com.br.bolsa.bolsa.api.domain.Util.FormatMoedaBrasileira;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Data
public class UserTradeDtoRendimentoAcumulado {
    private Long id;
    private LocalDate data;
    private String tipoOperacao;
    private String mercado;
    private String prazo;
    private String instrument;
    private String especificacao;
    private Integer quantidade;
    private String preco;
    private String valorTotal;
    private String valorTotalAcumulado;

    public UserTradeDtoRendimentoAcumulado(Long id, LocalDate data, String tipoOperacao, String mercado, String prazo, String instrument, String especificacao,
                                           Integer quantidade, BigDecimal preco, BigDecimal valorTotal, BigDecimal valorTotalAcumulado) {
        this.id = id;
        this.data = data;
        this.tipoOperacao = tipoOperacao;
        this.mercado = mercado;
        this.prazo = prazo;
        this.instrument = instrument;
        this.especificacao = especificacao;
        this.quantidade = quantidade;
        this.preco = FormatMoedaBrasileira.formatarMoedaBrasileira(preco.setScale(2, RoundingMode.HALF_UP));
        this.valorTotal = FormatMoedaBrasileira.formatarMoedaBrasileira(valorTotal.setScale(2, RoundingMode.HALF_UP));
        this.valorTotalAcumulado = FormatMoedaBrasileira.formatarMoedaBrasileira(valorTotalAcumulado.setScale(2, RoundingMode.HALF_UP));
    }
    public UserTradeDtoRendimentoAcumulado(){

    }
}
