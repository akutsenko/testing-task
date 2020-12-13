package ui.pl.allegro.steps;

import io.qameta.allure.Step;
import ui.pl.allegro.interfaces.CategoryNavigator;
import ui.pl.allegro.pages.MainPage;

public class MainPageSteps {

    private MainPage mainPage = new MainPage();

    @Step("Opening Allegro Main page")
    public void open() {
        mainPage.open();
        mainPage.closeAcceptCookiesWindow();
    }

    @Step("Selecting category: \"{category}\"")
    public void selectCategory(CategoryNavigator category) {
        mainPage.selectCategory(category);
    }

}
