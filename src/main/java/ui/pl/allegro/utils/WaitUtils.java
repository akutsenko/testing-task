package ui.pl.allegro.utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.webdriver.LocalDriverManager;

import static common.config.ConfigUtil.getProperties;
import static ui.pl.allegro.utils.WebdriverUtils.getByFromString;

public class WaitUtils {

    public static final int DEFAULT_TIMEOUT_IN_SECONDS = getProperties().defaultTimeout();
    public static final int SHORT_TIMEOUT_IN_SECONDS = DEFAULT_TIMEOUT_IN_SECONDS / 2;

    public static void waitForInvisibilityOfElement(WebElement element) {
        new WebDriverWait(LocalDriverManager.getDriver(), DEFAULT_TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static WebElement waitForVisibilityOfElement(String locator, long timeoutInSeconds) {
        return new WebDriverWait(LocalDriverManager.getDriver(), timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(getByFromString(locator)));
    }

    public static WebElement waitForPresenceOfElement(String locator, long timeoutInSeconds) {
        return new WebDriverWait(LocalDriverManager.getDriver(), timeoutInSeconds)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.presenceOfElementLocated(getByFromString(locator)));
    }

    public static void waitForStalenessOfElement(WebElement webElement, long timeoutInSeconds) {
        new WebDriverWait(LocalDriverManager.getDriver(), timeoutInSeconds)
                .until(ExpectedConditions.stalenessOf(webElement));
    }

    public static void waitForNumberOfWindowsToBe(int expectedNumberOfWindows) {
        Boolean result = new WebDriverWait(LocalDriverManager.getDriver(), DEFAULT_TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
        if(!result) {
            throw new RuntimeException("Wrong Number of Windows. Expected: " + expectedNumberOfWindows + " Found: " +
                    LocalDriverManager.getDriver().getWindowHandles().size());
        }
    }

}
