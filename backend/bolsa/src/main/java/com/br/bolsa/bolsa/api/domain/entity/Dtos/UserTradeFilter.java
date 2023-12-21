package com.br.bolsa.bolsa.api.domain.entity.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTradeFilter {
    private Long id;
    private LocalDate dataPassadaFilter;
    private String instrument;
    private String especificacao;
    private String tipoOperacao;

}
