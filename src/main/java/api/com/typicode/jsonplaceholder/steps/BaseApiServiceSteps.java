package api.com.typicode.jsonplaceholder.steps;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static common.config.ConfigUtil.getProperties;

public class BaseApiServiceSteps {

    private static final RequestSpecification jsonSpec = new RequestSpecBuilder()
            .setBaseUri(getProperties().baseApiUrl())
            .addFilters(getFilters())
            .setContentType(ContentType.JSON)
            .build();

    public RequestSpecification jsonSetup() {
        return RestAssured.given().spec(jsonSpec);
    }

    private static List<Filter> getFilters() {
        if (getProperties().restAssuredLoggingEnabled()) {
            return Arrays.asList(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured());
        } else {
            return Collections.singletonList(new AllureRestAssured());
        }
    }
}
