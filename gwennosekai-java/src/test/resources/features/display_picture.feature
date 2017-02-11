Feature: Display uploaded picture
  I need to display uploaded picture
  So I can use them within my articles

  Scenario: I should display picture
    Given the pictures exist:
      | id              | file content                              | filename                                  | location                                            |
      | my-generated-id | gwennosekai-diy-cousette-bianca-cover.jpg | gwennosekai-diy-cousette-bianca-cover.jpg | /pictures/gwennosekai-diy-cousette-bianca-cover.jpg |
    When I display the picture "my-generated-id"
    Then the picture "my-generated-id" should have same content that "gwennosekai-diy-cousette-bianca-cover.jpg" file