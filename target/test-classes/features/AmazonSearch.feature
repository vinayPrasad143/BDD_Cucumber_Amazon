@smoke @sanity
Feature: Amazon Product search

  Scenario Outline: User should be able to search for products and view relevant results
    Given user launches the Amazon Website
    When user searches for "<product>" using search bar
    Then the serach results page should display relevant items for "<product>"
    And the page title should reflect the searched "<product>"
    And the browser URL should include the search term "<product>"
    And filtering options should be available on the left side
    And a list of product results should be visible to the user

    Examples:
  | product      |
  | headphones   |
  #| laptop bag   |
  | xyzproductnotexist |  

