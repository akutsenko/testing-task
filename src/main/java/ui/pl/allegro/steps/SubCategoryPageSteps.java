package ui.pl.allegro.steps;

import io.qameta.allure.Step;
import ui.pl.allegro.interfaces.CategoryNavigator;
import ui.pl.allegro.pages.SortingOption;
import ui.pl.allegro.pages.SubCategoryPage;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static common.reporting.AssertWrapper.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SubCategoryPageSteps {

    private SubCategoryPage subCategoryPage = new SubCategoryPage();

    @Step("Selecting sub category: \"{category}\"")
    public void selectSubCategory(CategoryNavigator category) {
        subCategoryPage.selectSubCategory(category);
        subCategoryPage.waitForSubCategoryToBeSelected(category);
    }

    @Step("Sorting items by: \"{sortingOption.label}\"")
    public void sortItemsBy(SortingOption sortingOption) {
        subCategoryPage.sortItemsBy(sortingOption);
    }

    @Step("Checking that items are sorted by: \"{sortingOption.label}\"")
    public void checkSortingIsCorrect(SortingOption sortingOption) {
        List<BigDecimal> prices = subCategoryPage.getSortingIndicatorValues(sortingOption);
        List expectedPricesList = prices.stream().sorted(sortingOption.getComparator()).collect(Collectors.toList());
        assertThat("items are sorted correctly", prices, equalTo(expectedPricesList));
    }

}
