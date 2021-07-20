Feature: Shopping Automation
  Scenario: Testing the authentication
    Given I go to the Website
    When  I click on Signin button
    And   I specify my credential and click login
    Then  I can Login into the system


  Scenario: Testing purchase of two items
    Given I got to the Website
    When I add two elements to the cart
    And I proceed to checkout
    And I confirm address, shipping, payment and final order
    Then The items are bought