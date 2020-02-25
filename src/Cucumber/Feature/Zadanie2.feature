Feature: Zadanie2

  Scenario Outline: Order a sweater

    Given the user is on landing page
    When he wants to log in
    And he enter the "<login>","<password>"
    And he confirms login
    Then he should be logged in correctly
    Then he navigates trough clothes into Hummingbird Printed Sweater
    Then he adds 5 pieces of M size to cart
    Then he goes to checkout
    Then he confirms address
    Given the address is filled correctly
    Then he gets into shipping method
    And he chooses pick up in store
    And he confirms
    And he chooses to pay by check
    And he confirms payment
    Given he accepts to terms of service
    And he confirms order
    Then he should be redirected back with new address saved on his account
    Then he logs out

    Examples:
      | login              | password        |alias   |address  |company          |city     |phone       |postcode|
      | danken00@yahoo.com | 28FKGPsSGkdzwFU |Daniel  |Boczna 3 |Q-LOC S.A.       |Warszawa |+48515531078|42-420         |
      | danken00@wp.pl     | yoloyolo10      |Adam    |Ottona 6 |Adamex sp. z o.o.|Londyn   |+48666121321|45-666         |
