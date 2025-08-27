package steps;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.rest;

public class BaseApiSteps extends ScenarioSteps {

    @Step("Send POST request")
    public Response sendPostRequest(Object requestBody, String endpoint) {
        return rest().given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().log().all()
                .post(endpoint)
                .then().log().all()
                .extract().response();
    }

    @Step("Send DELETE request")
    public Response sendDeleteRequest(String url) {
        return rest().given()
                .contentType(ContentType.JSON)
                .when().log().all()
                .delete(url)
                .then().log().all()
                .extract().response();
    }

    @Step
    public Response sendGetRequest(String url) {
        return rest().given()
                .contentType(ContentType.JSON)
                .when().log().all()
                .get(url)
                .then().log().all()
                .and().extract().response();
    }

    @Step
    public Response sendGetRequest(String url, Map<String, Object> params) {
        return rest().given()
                .contentType(ContentType.JSON)
                .params(params)
                .when().log().all()
                .get(url)
                .then().log().all()
                .and().extract().response();
    }
}
