package ui.pl.allegro.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;
import ui.webdriver.ElementState;
import ui.webdriver.LocalDriverManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static common.config.ConfigUtil.getProperties;
import static ui.pl.allegro.utils.WaitUtils.DEFAULT_TIMEOUT_IN_SECONDS;
import static ui.pl.allegro.utils.WaitUtils.waitForNumberOfWindowsToBe;
import static ui.webdriver.LocalDriverManager.getDriver;


public class WebdriverUtils {

    public static By getByFromString(String stringLocator) {
        if (stringLocator.startsWith("//")
                || stringLocator.startsWith(".//")
                || stringLocator.startsWith("(//")
                || stringLocator.startsWith("(.//")
                || stringLocator.startsWith("/*//")
                || stringLocator.startsWith("(./*")
                || stringLocator.startsWith("((//")) {
            return By.xpath(stringLocator);
        } else {
            return By.cssSelector(stringLocator);
        }
    }

    public static WebElement findElement(String locator) {
        return findElement(locator, DEFAULT_TIMEOUT_IN_SECONDS);
    }

    public static WebElement findElement(String locator, int timeOutInSeconds) {
        return findElement(getByFromString(locator), ElementState.PRESENT, timeOutInSeconds);
    }

    public static WebElement findElement(String locator, ElementState elementState) {
        return findElement(getByFromString(locator), elementState, DEFAULT_TIMEOUT_IN_SECONDS);
    }

    public static WebElement findElement(By locator, ElementState elementState, Integer timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), DEFAULT_TIMEOUT_IN_SECONDS);
        return wait.withTimeout(Duration.ofSeconds(timeout))
                .ignoring(WebDriverException.class)
                .until(elementState.getCondition().apply(locator));
    }

    public static List<WebElement> findElements(String locator) {
        return getDriver().findElements(getByFromString(locator));
    }

    public static void switchToOpenedTab(int numberOfInitiallyOpenedWindows) {
        WebDriver driver = getDriver();
        waitForNumberOfWindowsToBe(numberOfInitiallyOpenedWindows + 1);
        String oldTab = driver.getWindowHandle();
        ArrayList<String> allTabs = new ArrayList<>(driver.getWindowHandles());
        allTabs.remove(oldTab);
        driver.switchTo().window(allTabs.get(allTabs.size() - 1));
    }

    public static void closeOtherTabsExceptCurrent() {
        String currentTab = getDriver().getWindowHandle();
        for(String handle : getDriver().getWindowHandles()) {
            if (!handle.equals(currentTab)) {
                getDriver().switchTo().window(handle);
                getDriver().close();
            }
        }
        getDriver().switchTo().window(currentTab);
    }

    public static void selectDropDownByVisibleText(WebElement element, String text) {
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(text);
    }

    public static void createNewDriver() {
        WebDriver driver;
        if (getProperties().isRemote()) {
            driver = WebDriverPool.DEFAULT.getDriver(getHubUrl(), getBrowserCapabilities());
        } else {
            setUpLocalDriver();
            driver = WebDriverPool.DEFAULT.getDriver(getBrowserCapabilities());
        }
        LocalDriverManager.setWebDriver(driver);
    }

    public static void quitDriver() {
        if (getDriver() != null) {
            WebDriverPool.DEFAULT.dismissDriver(getDriver());
        }
    }

    public static Capabilities getBrowserCapabilities() {
        switch (getProperties().browser()) {
            case "chrome": {
                return new ChromeOptions();
            }
            case "firefox": {
                return new FirefoxOptions();
            }
            default:
                throw new RuntimeException(String.format("Cannot initiate %s browser. " +
                        "Supported browsers are: firefox, chrome", getProperties().browser()));
        }
    }

    public static void setUpLocalDriver() {
        switch (getProperties().browser()) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                break;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                break;
            }
            default:
                throw new RuntimeException(String.format("Cannot set up local browser: %s. " +
                        "Supported browsers are: firefox, chrome, edge", getProperties().browser()));
        }
    }

    public static URL getHubUrl() {
        try {
            return new URL(getProperties().hubUrl());
        } catch (MalformedURLException exception) {
            throw new RuntimeException("Wrong url for remote server hub: " + getProperties().hubUrl(), exception);
        }
    }

    @Attachment(value = "{name} - screenshot", type = "image/png")
    public static byte[] makeScreenshotOnFailure(String name) {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

}
