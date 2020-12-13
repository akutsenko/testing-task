package ui.pl.allegro.pages;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Comparator;

@Getter
public enum SortingOption {

    TRAFNOSC_NAJWIEKSZA("trafność: największa", Comparator.reverseOrder()),
    CENA_OD_NAJNIZSEJ("cena: od najniższej", Comparator.naturalOrder()),
    CENA_OD_NAJWYZSZEJ("cena: od najwyższej", Comparator.reverseOrder()),
    CENA_Z_DOSTAWA_OD_NAJNIZSEJ("cena z dostawą: od najniższej", Comparator.naturalOrder()),
    CENA_Z_DOSTAWA_OD_NAJWYZSZEJ("cena z dostawą: od najwyższej", Comparator.reverseOrder()),
    POPULARNOSC_NAJWIEKSZA("popularność: największa", Comparator.reverseOrder()),
    CZAS_DO_KONCA_NAJMNIEJ("czas do końca: najmniej", Comparator.naturalOrder()),
    CZAS_DODANIA_NAJNOWSZE("czas dodania: najnowsze", Comparator.naturalOrder());

    private final String label;
    private final Comparator<BigDecimal> comparator;

    SortingOption(String label, Comparator<BigDecimal> comparator) {
        this.label = label;
        this.comparator = comparator;
    }

    @Override
    public String toString() {
        return label;
    }

}
