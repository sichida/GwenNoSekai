Feature: Find uploaded picture
  I need to find uploaded picture
  So I can use them within my articles

  Scenario: I should find a picture
    Given the pictures exist:
      | id              | file content                              | filename                                  | location                                            |
      | my-generated-id | gwennosekai-diy-cousette-bianca-cover.jpg | gwennosekai-diy-cousette-bianca-cover.jpg | /pictures/gwennosekai-diy-cousette-bianca-cover.jpg |
    When I request the picture "my-generated-id"
    Then I should have a non null picture

  Scenario: I should find a picture from it location
    Given the pictures exist:
      | id              | file content                              | filename                                  | location                                            |
      | my-generated-id | gwennosekai-diy-cousette-bianca-cover.jpg | gwennosekai-diy-cousette-bianca-cover.jpg | /pictures/gwennosekai-diy-cousette-bianca-cover.jpg |
    When I request the picture from location "gwennosekai-diy-cousette-bianca-cover.jpg"
    Then I should have a non null picture
