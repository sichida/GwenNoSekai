Feature: Find articles
  As a user of the blog
  I want to find articles
  So I can read them

  Scenario: it should display 1 article on page 1
    Given the following articles exist:
      | title                         | content               | permalink                   | author |
      | COUSETTE ❥ POLKA-DOTTY BIANCA | This is my first post | cousette-polka-dotty-bianca | gwen   |
    When I display 1 article from page 1
    Then I should display 1 article
    And we should find the article
      | title                         | content               | permalink                   | author |
      | COUSETTE ❥ POLKA-DOTTY BIANCA | This is my first post | cousette-polka-dotty-bianca | gwen   |
