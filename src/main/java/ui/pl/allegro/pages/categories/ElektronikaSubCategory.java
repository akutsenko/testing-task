package ui.pl.allegro.pages.categories;

import lombok.Getter;
import ui.pl.allegro.interfaces.CategoryNavigator;

@Getter
public enum ElektronikaSubCategory implements CategoryNavigator {

    KOMPUTERY("Komputery"),
    FOTOGRAFIA("Fotografia"),
    KONSOLE_I_AUTOMATY("Konsole i automaty"),
    RTV_I_AGD("RTV i AGD"),
    SPRZET_ESTRADOWY_STUDYJNY_I_DJ_SKI("Sprzęt estradowy, studyjny i DJ-ski"),
    TELEFONY_I_AKSESORIA("Telefony i Akcesoria");

    private final String title;

    ElektronikaSubCategory(String title) {
        this.title = title;
    }

    @Override
    public <T extends Enum<T>> T getParentCategory() {
        return (T) Category.ELEKTRONIKA;
    }

    @Override
    public String getCategoryTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }

}
