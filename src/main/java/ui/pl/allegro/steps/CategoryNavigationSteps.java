package ui.pl.allegro.steps;

import io.qameta.allure.Step;
import ui.pl.allegro.interfaces.CategoryNavigator;

import static ui.pl.allegro.interfaces.CategoryNavigator.*;

public class CategoryNavigationSteps {

    private MainPageSteps mainPageSteps = new MainPageSteps();
    private CategoryPageSteps categoryPageSteps = new CategoryPageSteps();
    private SubCategoryPageSteps subCategoryPageSteps = new SubCategoryPageSteps();

    @Step("Navigating to category: \"{targetCategory}\"")
    public void navigateToCategory(CategoryNavigator targetCategory) {
        mainPageSteps.open();
        mainPageSteps.selectCategory(getTopLevelCategory(targetCategory));
        categoryPageSteps.selectSubCategory(getTopLevelSubCategory(targetCategory));
        getSubCategoriesChain(targetCategory)
                .forEach(subCategory -> subCategoryPageSteps.selectSubCategory(subCategory));
    }
}
