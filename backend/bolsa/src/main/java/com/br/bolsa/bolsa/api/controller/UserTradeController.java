package com.br.bolsa.bolsa.api.controller;


import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeDto;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeDtoRendimentoAcumulado;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.UserTrade;
import com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL.UserTradeRepositoryCustom;
import com.br.bolsa.bolsa.api.domain.repository.UserTradeRepository;
import com.br.bolsa.bolsa.api.domain.service.UserTradeService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/trades")
public class UserTradeController {

    private UserTradeRepository userTradeRepository;
    private UserTradeService service;

    public UserTradeController(UserTradeRepository userTradeRepository, UserTradeService service) {
        this.userTradeRepository = userTradeRepository;
        this.service = service;
    }

    @Operation(summary = "Listar", description = "Retornar as informaçoes Trade")
    @GetMapping
    public List<UserTrade> listar(){
        return userTradeRepository.findAll();
    }

    @GetMapping("/{tradeId}")
    public Optional<UserTrade> buscarPorId(@PathVariable("tradeId") Long id){
        return userTradeRepository.findById(id);
    }

    //Query filtrada e com paginação
    @GetMapping("/tradesFiltrado")
    public ResponseEntity<Page<UserTrade>> buscarFiltrada( @RequestParam(required = false) Long id,
                                                           @RequestParam(required = false) String data,
                                                           @RequestParam(required = false) String instrument,
                                                           @RequestParam(required = false) String especificacao,
                                                           @RequestParam(required = false)String tipoOperacao,
                                                           Pageable pageable){
        UserTradeFilter  filter = buildFilter(id, data, instrument, especificacao, tipoOperacao);
        var trade = service.buscarTradePaginadoFiltrado(filter, pageable);
        return  ResponseEntity.status(HttpStatus.CREATED).body(trade);
    }

    @GetMapping("/buscarRendimento")
    public ResponseEntity<UserTradeDto> buscarRendimento(@RequestParam(required = false) String filter,
                                                         @RequestParam(required = false) String nomeSimbol,
                                                         @RequestParam(required = false) Long id,
                                                         @RequestParam(required = false) String dataInicio,
                                                         @RequestParam(required = false)String dataFinal){
        var filtroTrado = (filter != null) ? LocalDate.parse(filter) : null;
        var trade = service.buscarRendimentos(filtroTrado, nomeSimbol, id);
        return ResponseEntity.status(HttpStatus.OK).body(trade);

    }

    @GetMapping("/rendimentoTotaisFiltrado")
    public  ResponseEntity<List<UserTradeDtoRendimentoAcumulado>>rendimentosAcumuladosTotal(@RequestParam(required = false) String dataInicio,
                                                                                            @RequestParam(required = false) String dataFim){
        var dataInicioF = (dataInicio != null) ? LocalDate.parse(dataInicio) : null;
        var dataFimF = (dataFim != null) ? LocalDate.parse(dataFim) : null;
        var trade = service.buscarRendimentosAcumuladosETotal(dataInicioF, dataFimF);
        return ResponseEntity.status(HttpStatus.OK).body(trade);
    }

    private UserTradeFilter buildFilter(Long id, String data, String instrument, String especificacao, String tipoOperacao) {
        UserTradeFilter filter = new UserTradeFilter();
        filter.setId(id);
        filter.setDataPassadaFilter(LocalDate.parse(data));
        filter.setInstrument(instrument);
        filter.setTipoOperacao(tipoOperacao);
        filter.setEspecificacao(especificacao);
        return filter;
    }
}
