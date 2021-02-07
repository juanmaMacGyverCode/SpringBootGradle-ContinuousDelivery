Feature: calculator
  Scenario: Sum two numbers
    Given I have two numbers: 1 and 2
    When the calculator sums them
    And with request params
    And with path variables
    Then I receive 3 as a result
    And the result 3 with request params
    And the result 3 with path variables