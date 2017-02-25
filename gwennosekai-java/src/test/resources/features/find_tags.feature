@mongo-txn
Feature: Find existing tags
  I need to find tags
  So I can reuse them in articles

  Scenario: I should find a tag
    Given the following articles exist:
      | id             | title                         | content               | permalink                   | author |
      | some_random_id | COUSETTE ❥ POLKA-DOTTY BIANCA | This is my first post | cousette-polka-dotty-bianca | gwen   |
    And the article "some_random_id" has following tags:
      | tag   |
      | tag 1 |
    When I request existing tags with query "tag"
    Then I should have the following tags:
      | tag   |
      | tag 1 |

  Scenario: I should find multiple tag with same pattern
    Given the following articles exist:
      | id             | title                         | content               | permalink                   | author |
      | some_random_id | COUSETTE ❥ POLKA-DOTTY BIANCA | This is my first post | cousette-polka-dotty-bianca | gwen   |
    And the article "some_random_id" has following tags:
      | tag   |
      | tag 1 |
      | tag 2 |
      | tag 3 |
    When I request existing tags with query "tag"
    Then I should have the following tags:
      | tag   |
      | tag 1 |
      | tag 2 |
      | tag 3 |
