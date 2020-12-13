package ui.webdriver;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import ui.pl.allegro.utils.WebdriverUtils;

import java.lang.reflect.Method;

public class TestResultExtension implements TestWatcher {

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
            Method method = context.getRequiredTestMethod();
            if (context.getExecutionException().isPresent()) {
                WebdriverUtils.makeScreenshotOnFailure(method.getName());
            }
    }
}
