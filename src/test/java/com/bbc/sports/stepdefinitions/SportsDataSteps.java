package com.bbc.sports.stepdefinitions;

import com.bbc.sports.utils.ConfigReader;
import io.restassured.response.Response;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;
import static com.bbc.sports.baseurl.BaseUrl.spec;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class SportsDataSteps {

    private Response response;


    @Given("a GET request is sent to the endpoint")
    public void aGETRequestIsSentToTheEndpoint() {
        spec.queryParams("urn", ConfigReader.getProperty("queryValid"));
        response = given(spec).when().get();
    }

    @Then("the HTTP status code should be {int}")
    public void theHTTPStatusCodeShouldBe(int expectedStatusCode) {
        assertEquals(expectedStatusCode, response.getStatusCode());
    }

    @Then("the response time should be less than {int} milliseconds")
    public void theResponseTimeShouldBeLessThanMilliseconds(int maxResponseTime) {
        assertTrue("Response time exceeded!", response.time() < maxResponseTime);
    }

    @Then("the id field in the response should never be null or empty")
    public void theIdFieldInTheResponseShouldNeverBeNullOrEmpty() {
        List<Map<String, Object>> tournaments = response.jsonPath().getList("tournaments");
        tournaments.forEach(item -> {
            assertNotNull("ID field is null!", item.get("id"));
            assertFalse("ID field is empty!", item.get("id").toString().isEmpty());
        });
    }

    @And("the participants array should contain exactly {int} items")
    public void theParticipantsArrayShouldContainExactlyItems(int itemNum) {
        List<Map<String, Object>> participants = response.jsonPath().getList("tournaments[0].stages[0].rounds[0].participants");
        assertEquals("Participants array does not contain exactly " + itemNum + " items!", itemNum, participants.size());
    }

    @Given("a GET request is sent to the endpoint with a different competition name")
    public void aGETRequestIsSentToTheEndpointWithADifferentCompetitionName() {
        spec.queryParams("urn", ConfigReader.getProperty("queryAnotherCompetitionName"));
        response = given(spec).when().get();
    }

    @Then("the returned data should contain information for that different competition name {string}")
    public void theReturnedDataShouldContainInformationForThatDifferentCompetitionName(String difCompName) {
        List<String> returnedCompetitionNames = response.jsonPath().getList("tournaments.name");
        boolean isCompetitionFound = returnedCompetitionNames.stream()
                .anyMatch(name -> name.equals(difCompName));
        assertTrue("The competition name was not found in the response", isCompetitionFound);
    }

    @Given("a GET request is sent to the endpoint with an invalid competition name {string}")
    public void aGETRequestIsSentToTheEndpointWithAnInvalidCompetitionName(String invalidName) {
        spec.queryParams("urn", ConfigReader.getProperty("queryInvalid"));
        response = given(spec).when().get();
        response.prettyPrint();
    }

    @And("the response body should contain an error message")
    public void theResponseBodyShouldContainAnErrorMessage() {
        String errorMessage = response.jsonPath().getString("error.message");
        assertTrue("Error message does not contain the expected text.",
                errorMessage.contains("Error fetching data for container"));
    }

    @Given("a GET request is sent to the endpoint with header x-test-harness set to true")
    public void aGETRequestIsSentToTheEndpointWithHeaderXTestHarnessSetToTrue() {
                response = given()
                .spec(spec.queryParam("urn", ConfigReader.getProperty("queryValid")))
                .header("x-test-harness", "true")
                .when().get();
}

    @Then("the response header {string} should be set correctly")
    public void theResponseHeaderXTestHarnessShouldBeSetCorrectly(String key) {
        try {
            String actualHeaderValue = response.getHeader(key);
            if (actualHeaderValue == null) {
                System.out.println(" ");
            } else {
                assertEquals("Header value for " + key + " is not set correctly!", "true", actualHeaderValue);
            }
        } catch (Exception e) {
            System.out.println("Exception while checking header: " + e.getMessage());
            fail("Exception occurred while checking header: " + e.getMessage());
        }
    }
    }

