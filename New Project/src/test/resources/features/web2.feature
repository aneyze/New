@TS_Web2
Feature: Web2

  Verify validation of the feature/functionality "Web2" on the website https://trivago.com.br


  @automatizado
  Scenario: Cenario01 - Web2 - Printing Data Reservation On Trivago Website
    Given a web browser is opened
    When the user enters "Natal" on Search Field
    And the user selects option Individual Room
    And the user clicks on Search
    And the user selects option Only Distance
    Then the system should display "Natal Palace" as Hotel's Name
    And the system should display "Hotel" as Accommodation Room Type because the hotel doesn't have starts
    And the system should display the site responsible by the offer
    And the system should display the price for the selected room
    And the system should display Room's comodations



