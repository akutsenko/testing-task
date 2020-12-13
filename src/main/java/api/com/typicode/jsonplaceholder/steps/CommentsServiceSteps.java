package api.com.typicode.jsonplaceholder.steps;


import api.assertions.AssertableResponse;
import io.qameta.allure.Step;

public class CommentsServiceSteps extends BaseApiServiceSteps {

    private static final String PATH = "/comments";

    @Step("Invoking GET request to \"/comments\" endpoint")
    public AssertableResponse getComments() {
        return new AssertableResponse(jsonSetup()
                .when()
                .get(PATH));
    }


}
