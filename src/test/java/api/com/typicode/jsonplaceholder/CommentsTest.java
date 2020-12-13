package api.com.typicode.jsonplaceholder;

import api.assertions.AssertableResponse;
import api.com.typicode.jsonplaceholder.pojo.payloads.Comments;
import api.com.typicode.jsonplaceholder.steps.CommentsServiceSteps;
import api.conditions.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static api.conditions.Conditions.body;
import static api.conditions.Conditions.statusCode;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;

public class CommentsTest {

    private final CommentsServiceSteps commentsServiceSteps = new CommentsServiceSteps();

    @ParameterizedTest
    @Feature("Comments")
    @Description("Checking possibility to retrieve comments")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("provideDataFor_verifyThatCommentsCanBeRetrieved")
    public void verifyThatCommentsCanBeRetrieved(List<Condition> conditions) {
        AssertableResponse response = commentsServiceSteps.getComments();
        conditions.forEach(condition -> response.shouldHave(condition));
    }

    @ParameterizedTest
    @Feature("Comments")
    @Description("Checking possibility to retrieve comments as POJOs collection and filter them")
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("provideDataFor_verifyThatCommentsCanBeRetrievedAndDeserializeResponse")
    public void verifyThatCommentsCanBeRetrievedAndDeserializeResponse(int postId, String bodyPart) {
        Comments[] comments = commentsServiceSteps.getComments().asPojo(Comments[].class);
        Stream.of(comments)
                .filter(comment -> comment.postId() == postId)
                .filter(comment -> comment.body().contains(bodyPart))
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> provideDataFor_verifyThatCommentsCanBeRetrieved() {
        return Stream.of(
                Arguments.of(asList(statusCode(200),
                        body("size()", greaterThan(0)),
                        body("email", hasItem("Jayne_Kuhic@sydney.com"))))
        );
    }

    private static Stream<Arguments> provideDataFor_verifyThatCommentsCanBeRetrievedAndDeserializeResponse() {
        return Stream.of(
                Arguments.of(1, "non")
        );
    }

}
