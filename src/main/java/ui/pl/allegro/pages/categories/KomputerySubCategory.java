package ui.pl.allegro.pages.categories;

import lombok.Getter;
import ui.pl.allegro.interfaces.CategoryNavigator;

@Getter
public enum KomputerySubCategory implements CategoryNavigator {

    DYSKI_I_PAMIECI_PRZENOSNE("Dyski i pamięci przenośne"),
    AKCESORIA_LAPTOP_PC("Akcesoria (Laptop, PC)"),
    CZESCI_DO_LAPTOPOW("Części do laptopów"),
    DRUKARKI_I_SKANERY("Drukarki i skanery");
    //TODO: the list is not full and should be extended for real test project

    private final String title;

    KomputerySubCategory(String title) {
        this.title = title;
    }

    @Override
    public <T extends Enum<T>> T getParentCategory() {
        return (T) ElektronikaSubCategory.KOMPUTERY;
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
