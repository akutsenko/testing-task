package ui.pl.allegro.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import ui.pl.allegro.interfaces.CategoryNavigator;

import static common.config.ConfigUtil.getProperties;
import static java.lang.String.format;
import static ui.pl.allegro.utils.WaitUtils.waitForInvisibilityOfElement;
import static ui.pl.allegro.utils.WebdriverUtils.findElement;
import static ui.webdriver.LocalDriverManager.getDriver;

public class MainPage {

    private static String CATEGORY_LINK_TEMPLATE = "//div[@data-group-id='departments_%s']";
    private static String ACCEPT_COOKIES_WINDOW = "//div[@aria-labelledby='dialog-title']";
    private static String CLOSE_ACCEPT_COOKIES_WINDOW_BUTTON = "//button[@data-role='close-and-accept-consent']";

    public void open() {
        getDriver().get(getProperties().baseWebUrl());
    }

    public void selectCategory(CategoryNavigator categoryNavigator) {
        String locator = format(CATEGORY_LINK_TEMPLATE, categoryNavigator.getCategoryTitle());
        findElement(locator).click();
    }

    public void closeAcceptCookiesWindow() {
        try {
            WebElement acceptCookiesWindow = findElement(ACCEPT_COOKIES_WINDOW, getProperties().waitAcceptCookiesTimeout());
            findElement(CLOSE_ACCEPT_COOKIES_WINDOW_BUTTON).click();
            waitForInvisibilityOfElement(acceptCookiesWindow);
        } catch (TimeoutException e) {
            // do nothing, window has not appeared
        }

    }

}
