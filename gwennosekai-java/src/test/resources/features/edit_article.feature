@mongo-txn
Feature: Edit articles
  As contributor
  I want to edit articles

  Scenario: I should be able to edit an article
    Given "shoun" is logged in
    Given the following articles exist:
      | id             | title                         | content               | permalink                   | author |
      | some_random_id | COUSETTE ❥ POLKA-DOTTY BIANCA | This is my first post | cousette-polka-dotty-bianca | shoun  |
    When he edits the article described by "some_random_id" as:
      | id             | title                         | content                        | permalink                   | author |
      | some_random_id | COUSETTE ❥ POLKA-DOTTY BIANCA | This is my first post - edited | cousette-polka-dotty-bianca | shoun  |
    Then we should find the article
      | title                         | content                        | permalink                   | author |
      | COUSETTE ❥ POLKA-DOTTY BIANCA | This is my first post - edited | cousette-polka-dotty-bianca | shoun  |
    And it last updated date should be today