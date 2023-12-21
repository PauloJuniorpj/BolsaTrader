package com.br.bolsa.bolsa.api.domain.Util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class FormatMoedaBrasileira {

    public static String formatarMoedaBrasileira(BigDecimal valor) {
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatoMoeda.format(valor);
    }
}
