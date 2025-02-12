@api
Feature: Competition API Tests

  Background:
    Given a GET request is sent to the endpoint

    @TC01
  Scenario: TC01_Verify API status code and response time
    Then the HTTP status code should be 200
    And the response time should be less than 1000 milliseconds

  @TC02
  Scenario: TC02_Verify the "id" field and participants array
    Then the id field in the response should never be null or empty
    And the participants array should contain exactly 6 items

  @TC03
  Scenario: TC03_Verify API response for a different competition name
    Given a GET request is sent to the endpoint with a different competition name
    Then the returned data should contain information for that different competition name "World Cup"

  @TC04
  Scenario: TC04_Verify API response for an invalid competition name
    Given a GET request is sent to the endpoint with an invalid competition name "BBC-Cup"
    Then the HTTP status code should be 404
    And the response body should contain an error message

  @TC05
  Scenario: TC05_Verify x-test-harness header
    Given a GET request is sent to the endpoint with header x-test-harness set to true
    Then the response header "x-test-harness" should be set correctly




