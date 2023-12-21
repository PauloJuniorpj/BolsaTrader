package com.br.bolsa.bolsa.api.controller;


import com.br.bolsa.bolsa.api.domain.entity.Dtos.InstrumentFilter;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.InstrumentQuote;
import com.br.bolsa.bolsa.api.domain.entity.UserTrade;
import com.br.bolsa.bolsa.api.domain.repository.InstrumentRepository;
import com.br.bolsa.bolsa.api.domain.service.InstrumentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/acoes")
public class InstrumentController {

    private final InstrumentRepository instrumentRepository;
    private final InstrumentService service;

    public InstrumentController(InstrumentRepository instrumentRepository, InstrumentService service) {
        this.instrumentRepository = instrumentRepository;
        this.service = service;
    }

    @Operation(summary = "Listar", description = "Retornar as informaçoes Trade")
    @GetMapping
    public List<InstrumentQuote> listar(){
        return instrumentRepository.findAll();
    }

    @GetMapping("/{acaoId}")
    public Optional<InstrumentQuote> buscarPorId(@PathVariable("acaoId") Long id){
        return instrumentRepository.findById(id);
    }

    //Query filtrada e com paginação
    @GetMapping("/acaoFilter")
    public ResponseEntity<Page<InstrumentQuote>> buscarFiltrada(@RequestParam(required = false) Long id,
                                                                @RequestParam(required = false) String simbol,
                                                                @RequestParam(required = false) String data, Pageable pageable){
        InstrumentFilter filter = builderFilter(id, simbol, data);
        var instrument = service.buscarInstrumentPaginadoFiltrado(filter, pageable);
        return  ResponseEntity.status(HttpStatus.CREATED).body(instrument);
    }
    private InstrumentFilter builderFilter(Long id, String simbol, String data ){
        InstrumentFilter filter = new InstrumentFilter();
        filter.setId(id);
        filter.setSimbol(simbol);
        filter.setData(LocalDate.parse(data));
        return filter;
    }
}
