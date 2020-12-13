package ui.pl.allegro.pages;

import org.openqa.selenium.WebElement;
import ui.pl.allegro.interfaces.CategoryNavigator;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;
import static ui.pl.allegro.utils.NumberFormatUtils.convertStringToBigDecimal;
import static ui.pl.allegro.utils.WaitUtils.*;
import static ui.pl.allegro.utils.WebdriverUtils.*;

public class SubCategoryPage {

    private static String SUB_CATEGORY_LINK_TEMPLATE = "//ul/li/div/a[contains(.,'%s')]";
    private static String BREADCRUMB_ITEM = "//div[@data-role='breadcrumb-item']/a/span[.='%s']";
    private static String SORTING_DROP_DOWN = "//select[@id='allegro.listing.sort']";
    private static String NON_SPONSORED_ITEMS = "//div[@class='opbox-listing']//article[not(@data-analytics-view-label='showSponsoredItems')]";
    private static String NON_SPONSORED_ITEM_BY_INDEX = "(" + NON_SPONSORED_ITEMS + ")[%d]";
    private static String ITEM_PRICE_LOCATOR_SUFFIX = "/div/div/div/div/div/span[contains(.,'zł')]";

    public void selectSubCategory(CategoryNavigator categoryNavigator) {
        String locator = format(SUB_CATEGORY_LINK_TEMPLATE, categoryNavigator.getCategoryTitle());
        findElement(locator).click();
    }

    public void waitForSubCategoryToBeSelected(CategoryNavigator categoryNavigator) {
        String locator = format(BREADCRUMB_ITEM, categoryNavigator.getCategoryTitle());
            waitForVisibilityOfElement(locator, SHORT_TIMEOUT_IN_SECONDS);
    }

    public void sortItemsBy(SortingOption sortingOption) {
        WebElement nonSponsoredItem = findElement(format(NON_SPONSORED_ITEM_BY_INDEX, 1));
        selectDropDownByVisibleText(findElement(SORTING_DROP_DOWN), sortingOption.getLabel());
        waitForStalenessOfElement(nonSponsoredItem, SHORT_TIMEOUT_IN_SECONDS);
    }

    public List<BigDecimal> getNonSponsoredItemsPrices() {
        List<BigDecimal> prices = new LinkedList<>();
        int nonSponsoredItemsNumber = findElements(NON_SPONSORED_ITEMS).size();
        for (int i = 1; i <= nonSponsoredItemsNumber; i++) {
            String textPrice = findElement(format(NON_SPONSORED_ITEM_BY_INDEX, i) + ITEM_PRICE_LOCATOR_SUFFIX)
                    .getText().replace(" zł", "");
            prices.add(convertStringToBigDecimal(textPrice));
        }
        return prices;
    }

    public List<BigDecimal> getSortingIndicatorValues(SortingOption sortingOption) {
        switch (sortingOption) {
            case CENA_OD_NAJNIZSEJ:
            case CENA_OD_NAJWYZSZEJ:
            case CENA_Z_DOSTAWA_OD_NAJNIZSEJ:
            case CENA_Z_DOSTAWA_OD_NAJWYZSZEJ: {
                return getNonSponsoredItemsPrices();
            }
            default: {
                //TODO: replace RuntimeException with valid methods for other sorting types
                throw new RuntimeException("Sorted data retrieval method is not yet implemented for: " + sortingOption);
            }
        }
    }

}
