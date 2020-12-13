package ui.pl.allegro.pages.categories;

import lombok.Getter;
import ui.pl.allegro.interfaces.CategoryNavigator;

@Getter
public enum DyskiIPamieciPrzenosneSubCategory implements CategoryNavigator {

    DYSKI_HDD("Dyski HDD"),
    DYSKI_SDD("Dyski SDD"),
    DYSKI_ZEWNETRZNE_I_PRZENOSNE("Dyski zewnętrzne i przenośne"),
    PAMIECI_PRZENOSNE("Pamięci przenośne"),
    OBUDOWY_I_KIESZENIE("Obudowy i kieszenie"),
    POZOSTALE("Pozostałe");

    private final String title;

    DyskiIPamieciPrzenosneSubCategory(String title) {
        this.title = title;
    }

    @Override
    public <T extends Enum<T>> T getParentCategory() {
        return (T) KomputerySubCategory.DYSKI_I_PAMIECI_PRZENOSNE;
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
