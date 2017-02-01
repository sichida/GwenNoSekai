@mongo-txn
Feature: Create an article
  As contributor
  I want to create articles

  Scenario: I should be able to create an article
    Given "shoun" is logged in
    When he creates the article
      | title    | content               | permalink     |
      | My title | This is my first post | my-first-post |
    Then it should be 1 article
    And we should find the article
      | title    | content               | permalink     | author |
      | My title | This is my first post | my-first-post | shoun  |
    And it creation date should be today