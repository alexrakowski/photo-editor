Feature: Edit the Image

  Scenario: I can load a default picture, and add some basic content
    Given I press view with id "selectImageImageView"
    Then I tap the text "Use the default image"
    Then I press view with id "convertButton"
    Then I wait for the view with id "moustachesLayout" to show up
    Then I tap the 1 view in layout with id "moustachesLayout"
    Then I tap the 1 view in layout with id "glassesLayout"
    Then I tap the 1 view in layout with id "hatsLayout"
    Then I take a picture named "after_content_selected.png"
    Then I press view with id "confirmContentButton"
    Then I wait for the view with id "moveContentImageView" to show up
    Then I take a picture named "after_content_added.png"
    Then I tap the text "Finish"
    Then I wait for the view with id "editedImageImageView" to show up
    Then I take a picture named "after_content_moved.png"