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
#    Given the address is filled correctly
    Then he gets into shipping method
    And he chooses pick up in store
    And he confirms
    And he chooses to pay by check
#    And he confirms payment
    Given he accepts to terms of service
    And he confirms order
    Then he logs out

    Examples:
      | login              | password        |alias   |address  |company          |city     |phone       |postcode|
      | danken00@yahoo.com | 28FKGPsSGkdzwFU |Daniel  |Boczna 3 |Q-LOC S.A.       |Warszawa |+48515531078|42-420         |
      | danken00@wp.pl     | yoloyolo10      |Adam    |Ottona 6 |Adamex sp. z o.o.|Londyn   |+48666121321|45-666         |

    #Napisz skrypt, który:
#+zaloguje się na tego samego użytkownika z zadania 1,
#+wybierze do zakupu Hummingbird Printed Sweater (opcja dodatkowa: sprawdzi czy rabat na niego wynosi 20%),
#+wybierze rozmiar M (opcja dodatkowa: zrób tak żeby można było sparametryzować rozmiar i wybrać S,M,L,XL),
#+wybierze 5 sztuk według parametru podanego w teście (opcja dodatkowa: zrób tak żeby można było sparametryzować liczbę sztuk),
#+dodaj produkt do koszyka,
#+przejdzie do opcji - checkout,
#+potwierdzi address (możesz go dodać wcześniej ręcznie),
#+wybierze metodę odbioru - PrestaShop "pick up in store",
#+wybierze opcję płatności - Pay by Check,
#+kliknie na "order with an obligation to pay",
#+zrobi screenshot z potwierdzeniem zamówienia i kwotą.
