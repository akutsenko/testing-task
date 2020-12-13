package ui.pl.allegro.utils;

import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class NumberFormatUtils {

    public static final String NUMBER_PATTERN = "#,###,##0.00";
    public static final char NUMBER_GROUPING_SEPARATOR = ' ';
    public static final char NUMBER_DECIMAL_SEPARATOR = ',';

    @SneakyThrows
    public static BigDecimal convertStringToBigDecimal(String value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(NUMBER_GROUPING_SEPARATOR);
        symbols.setDecimalSeparator(NUMBER_DECIMAL_SEPARATOR);
        DecimalFormat decimalFormat = new DecimalFormat(NUMBER_PATTERN, symbols);
        decimalFormat.setParseBigDecimal(true);
        return (BigDecimal) decimalFormat.parse(value);
    }

}
