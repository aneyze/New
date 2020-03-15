@TS_Web2
Feature: Web

  Verify validation of the feature/functionality "Web" on the website "https://www.discourse.org/"

  @automatizado
  Scenario: Cenario01 - Web - Printing Data Reservation On Discourse Website
    Given a web browser is opened - discourse
    When the user clicks on Demo
    And the user opens the right windown
    And the user scrolls the page for the first time
    And the user scrolls the page for the second time
    Then the system should display closed items
    And the system should display the total amount of items that has categories
    And the system should display the total amount of items thtat doesn't have categories
    And the system should display the highest number of views