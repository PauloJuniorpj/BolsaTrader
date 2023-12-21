package com.br.bolsa.bolsa.api.domain.service;

import com.br.bolsa.bolsa.api.domain.Util.FormatMoedaBrasileira;
import com.br.bolsa.bolsa.api.domain.Util.NumeroPorcentual;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeDto;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeDtoRendimentoAcumulado;
import com.br.bolsa.bolsa.api.domain.entity.Dtos.UserTradeFilter;
import com.br.bolsa.bolsa.api.domain.entity.UserTrade;
import com.br.bolsa.bolsa.api.domain.repository.InstrumentRepository;
import com.br.bolsa.bolsa.api.domain.repository.RepositoryIMPL.UserTradeRepositoryCustom;
import com.br.bolsa.bolsa.api.domain.repository.UserTradeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserTradeService {

    private  UserTradeRepository userTradeRepository;
    private UserTradeRepositoryCustom userTradeRepositoryCustom;
    private InstrumentRepository instrumentRepository;

    public UserTradeService(UserTradeRepository userTradeRepository, UserTradeRepositoryCustom userTradeRepositoryCustom,
                            InstrumentRepository instrumentRepository) {
        this.userTradeRepository = userTradeRepository;
        this.userTradeRepositoryCustom = userTradeRepositoryCustom;
        this.instrumentRepository = instrumentRepository;
    }

    public Page<UserTrade> buscarTradePaginadoFiltrado(UserTradeFilter filter, Pageable pageable){
        return userTradeRepositoryCustom.buscaFiltradaPaginada(filter, pageable);
    }

    public UserTradeDto buscarRendimentos(LocalDate filter, String nomeSimbol, Long id){
        var trade = userTradeRepository.findById(id);
        var instrumentDaCompra = instrumentRepository.buscarAcoesfiltrado(id, filter, nomeSimbol);
        var tradeRendimento = new UserTradeDto();

        var rendimento = calcularRendimento(instrumentDaCompra.getPrice(), Double.valueOf(trade.get().getQuantidade()),
                Double.parseDouble(String.valueOf(trade.get().getValorTotal())));

        var rendimentoFormat = BigDecimal.valueOf(rendimento).setScale(2, RoundingMode.HALF_UP);

        //Rendimento pode ter tido lucro ou nao
        tradeRendimento.setValorRendimento(FormatMoedaBrasileira.formatarMoedaBrasileira(rendimentoFormat));
        //Rendimento pode ter tido lucro ou nao

        tradeRendimento.setId(trade.get().getId());
        tradeRendimento.setTipoOperacao(trade.get().getTipoOperacao());
        tradeRendimento.setMercado(trade.get().getMercado());
        tradeRendimento.setInstrument(trade.get().getInstrument());
        tradeRendimento.setDataOperacao(trade.get().getDataOperacao());
        tradeRendimento.setPreco(FormatMoedaBrasileira.formatarMoedaBrasileira(trade.get().getPreco()));
        tradeRendimento.setQuantidade(trade.get().getQuantidade());
        tradeRendimento.setEspecificacao(trade.get().getEspecificacao());
        tradeRendimento.setValorTotal(FormatMoedaBrasileira.formatarMoedaBrasileira(trade.get().getValorTotal()));
        tradeRendimento.setValorRendimentoPorcento(NumeroPorcentual.formatarPercentual(rendimentoFormat));
        return tradeRendimento;
    }

    public List<UserTradeDtoRendimentoAcumulado> buscarRendimentosAcumuladosETotal(LocalDate dataInicial, LocalDate datafim){
        List<UserTradeDtoRendimentoAcumulado> tradeRendimento = new ArrayList<>();
        var rendimentoTotalAcumulado = userTradeRepository.rendimentoTotalAcomulado(dataInicial, datafim);
        BigDecimal valorTotalAcumulado = BigDecimal.ZERO;

        for (UserTrade userTrade : rendimentoTotalAcumulado) {
            UserTradeDtoRendimentoAcumulado tradeDto = new UserTradeDtoRendimentoAcumulado();

            tradeDto.setId(userTrade.getId());
            tradeDto.setTipoOperacao(userTrade.getTipoOperacao());
            tradeDto.setMercado(userTrade.getMercado());
            tradeDto.setInstrument(userTrade.getInstrument());
            tradeDto.setData(userTrade.getDataOperacao());
            tradeDto.setPreco(FormatMoedaBrasileira.formatarMoedaBrasileira(userTrade.getPreco()));
            tradeDto.setQuantidade(userTrade.getQuantidade());
            tradeDto.setEspecificacao(userTrade.getEspecificacao());

            BigDecimal valorTotal = userTrade.getValorTotal();
            valorTotalAcumulado = valorTotalAcumulado.add(valorTotal);

            tradeDto.setValorTotal(FormatMoedaBrasileira.formatarMoedaBrasileira(valorTotal.setScale(2, RoundingMode.HALF_UP)));
            tradeDto.setValorTotalAcumulado(FormatMoedaBrasileira
                    .formatarMoedaBrasileira(valorTotalAcumulado.setScale(2, RoundingMode.HALF_UP)));

            tradeRendimento.add(tradeDto);
        }

        return tradeRendimento;
    }

    private Double calcularRendimento (Double precoUnitarioAcao, Double precoQuantidade, double totalAcao ){
        var total = precoUnitarioAcao * precoQuantidade;
        return total- totalAcao;
    }

}
