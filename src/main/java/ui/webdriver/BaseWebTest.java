package ui.webdriver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.pl.allegro.utils.WebdriverUtils;

@ExtendWith(TestResultExtension.class)
public class BaseWebTest {

    @BeforeEach()
    public void startBrowser() {
        WebdriverUtils.createNewDriver();
    }

    @AfterAll
    public static void stopBrowser() {
        WebdriverUtils.quitDriver();
    }

}
