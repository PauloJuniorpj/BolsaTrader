package com.br.bolsa.bolsa.api.domain.entity.Dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTradeDto {
    private Long id;
    private LocalDate dataOperacao;
    private String tipoOperacao;
    private String mercado;
    private String praso;
    private String instrument;
    private String especificacao;
    private Integer quantidade;
    private String preco;
    private String valorTotal;
    private String valorRendimento;
    private String valorRendimentoPorcento;
}
