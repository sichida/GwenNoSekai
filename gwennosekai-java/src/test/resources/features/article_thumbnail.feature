@mongo-txn
Feature: Articles thumbnail
  As contributor
  I should be able to set an article thumbnail
  So it can be displayed on the front page

  Scenario: I should be able to add a thumbnail to an article
    Given "shoun" is logged in
    And the following articles exist:
      | id             | title                         | content               | permalink                   | author |
      | some_random_id | COUSETTE ❥ POLKA-DOTTY BIANCA | This is my first post | cousette-polka-dotty-bianca | shoun  |
    When I upload the picture "gwennosekai-diy-cousette-bianca-cover.jpg" for the article described by "some_random_id"
    Then I should find a thumbnail picture for the article "some_random_id"

  Scenario: I should be able to upload a picture
    Given "shoun" is logged in
    When I upload the picture "gwennosekai-diy-cousette-bianca-cover.jpg"
    Then I should find a have the picture with content
    And it location should match "/pictures/gwennosekai-diy-cousette-bianca-cover-.*.jpg"
