package common.reporting;

import io.qameta.allure.Step;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;

public class AssertWrapper {

    @Step("Assert that \"{actual}\" is {matcher}")
    public static <T> void assertThat(T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(actual, matcher);
    }

    @Step("Checking that {reason}. Assert that \"{actual}\" is {matcher}")
    public static <T> void assertThat(String reason, T actual, Matcher<? super T> matcher) {
        MatcherAssert.assertThat(reason, actual, matcher);
    }

}
