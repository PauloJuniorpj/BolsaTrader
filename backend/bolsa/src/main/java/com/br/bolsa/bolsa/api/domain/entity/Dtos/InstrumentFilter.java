package com.br.bolsa.bolsa.api.domain.entity.Dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class InstrumentFilter {
    private Long id;
    private String simbol;
    private String  price;
    private LocalDate data;

    public InstrumentFilter() {

    }
}
