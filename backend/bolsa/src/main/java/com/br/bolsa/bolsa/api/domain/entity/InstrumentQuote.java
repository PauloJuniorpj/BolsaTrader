package com.br.bolsa.bolsa.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "instrument_quote")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstrumentQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "simbol")
    private String simbol;
    @Column(name = "price")
    private Double price;
    @Column(name = "date")
    private LocalDate data;
}
