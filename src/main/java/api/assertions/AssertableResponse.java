package api.assertions;

import api.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Getter
@Slf4j
public class AssertableResponse {
    private final Response response;

    @Step("Assert that: \"{condition}\"")
    public AssertableResponse shouldHave(Condition condition) {
        condition.check(response);
        return this;
    }

    @Step("Converting api response to object of: {clazz}")
    public <T> T asPojo(Class<T> clazz) {
        return response.as(clazz);
    }

    @Step("Extracting headers from api response")
    public Headers headers() {
        return response.getHeaders();
    }

}
