Feature: Amazon search functionality

  Scenario: Search for a product by ID
    Given I open Amazon
    When I search for product ID "Laptops"
    Then I should see search results
