package ui.pl.allegro.pages;


import ui.pl.allegro.interfaces.CategoryNavigator;

import static java.lang.String.format;
import static ui.pl.allegro.utils.WaitUtils.SHORT_TIMEOUT_IN_SECONDS;
import static ui.pl.allegro.utils.WaitUtils.waitForVisibilityOfElement;
import static ui.pl.allegro.utils.WebdriverUtils.*;
import static ui.webdriver.LocalDriverManager.getDriver;

public class CategoryPage {

    private static String SUB_CATEGORY_LINK_TEMPLATE = "//ul/li/a[.='%s']";
    private static String BREADCRUMB_ITEM = "//div[@data-role='breadcrumb-item']/a/span[.='%s']";

    public void selectSubCategory(CategoryNavigator categoryNavigator) {
        int initialNumberOfOpenedTabs = getDriver().getWindowHandles().size();
        String locator = format(SUB_CATEGORY_LINK_TEMPLATE, categoryNavigator.getCategoryTitle());
        findElement(locator).click();
        switchToOpenedTab(initialNumberOfOpenedTabs);
        closeOtherTabsExceptCurrent();
    }

    public void waitForSubCategoryToBeSelected(CategoryNavigator categoryNavigator) {
            waitForVisibilityOfElement(format(BREADCRUMB_ITEM, categoryNavigator.getCategoryTitle()), SHORT_TIMEOUT_IN_SECONDS);
    }

}
