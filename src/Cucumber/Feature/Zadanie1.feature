Feature: Zadanie1

  Scenario Outline: Add up a new address


    Given open website https://prod-kurs.coderslab.pl/index.php
    When he chooses to log in
    And he provides the "<login>","<password>"
    And he clicks login button
    Then he should be logged in
    When he chooses to add address
    And he provides the "<alias>", "<address>", "<city>", "<postcode>", country, "<phone>" and save them
    Then he should be redirected back with new address saved on his account

    Examples:
      | login              | password        |alias   |address  |company          |city     |phone       |postcode|
      | danken00@yahoo.com | 28FKGPsSGkdzwFU |Daniel  |Boczna 3 |Q-LOC S.A.       |Warszawa |+48515531078|42-420         |
      | danken00@wp.pl     | yoloyolo10      |Adam    |Ottona 6 |Adamex sp. z o.o.|Londyn   |+48666121321|45-666         |