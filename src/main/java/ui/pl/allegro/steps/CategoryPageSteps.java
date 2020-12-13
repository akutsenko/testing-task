package ui.pl.allegro.steps;

import io.qameta.allure.Step;
import ui.pl.allegro.interfaces.CategoryNavigator;
import ui.pl.allegro.pages.CategoryPage;

public class CategoryPageSteps {

    private CategoryPage categoryPage = new CategoryPage();

    @Step("Selecting sub category: \"{category}\"")
    public void selectSubCategory(CategoryNavigator category) {
        categoryPage.selectSubCategory(category);
        categoryPage.waitForSubCategoryToBeSelected(category);
    }

}
