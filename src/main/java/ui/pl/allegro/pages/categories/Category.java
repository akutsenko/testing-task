package ui.pl.allegro.pages.categories;

import lombok.Getter;
import ui.pl.allegro.interfaces.CategoryNavigator;

@Getter
public enum Category implements CategoryNavigator {

    ELEKTRONIKA("Elektronika"),
    MODA("Moda"),
    DOM_I_OGROD("Dom i ogród"),
    SUMERMARKET("Supermarket"),
    DZIECKO("Dziecko"),
    URODA("Uroda"),
    ZDROWIE("Zdrowie"),
    KULTURA_I_ROZRYWKA("Kultura i rozrywka"),
    SPORT_I_TURYSTYKA("Sport i turystyka"),
    MOTORYZACJA("Motoryzacja"),
    NIERUCHOMOSCI("Nieruchomości"),
    KOLEKCJE_I_SZTUKA("Kolekcje i sztuka"),
    FIRMA_I_USLUGI("Firma i usługi");

    private final String title;
    private static String CATEGORY_LINK_TEMPLATE = "//div[@data-group-id='departments_%s']";

    Category(String title) {
        this.title = title;
    }

    @Override
    public String getCategoryTitle() {
        return title;
    }

    @Override
    public <T extends Enum<T>> T getParentCategory() {
        return null;
    }

    @Override
    public String toString() {
        return title;
    }

}
