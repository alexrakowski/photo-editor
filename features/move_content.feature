Feature: Move the photo content

  Scenario: I can move the content added to a photo
    Given I press view with id "selectImageImageView"
    Then I tap the text "Use the default image"
    Then I press view with id "convertButton"
    Then I wait for the view with id "moustachesLayout" to show up
    Then I tap the 1 view in layout with id "moustachesLayout"
    Then I tap the 1 view in layout with id "glassesLayout"
    Then I tap the 1 view in layout with id "hatsLayout"
    Then I press view with id "confirmContentButton"
    Then I wait for the view with id "moveContentImageView" to show up
    Then I take a picture named "before_content_move.png"
    Then I drag the 1 movable view to the right
    Then I drag the 3 movable view to the bottom
    Then I drag the 2 movable view to the left
    Then I drag the 2 movable view to the top
    Then I take a picture named "after_content_move.png"
    Then I tap the text "Finish"
    Then I wait for the view with id "editedImageImageView" to show up
    Then I take a picture named "image_confirmal.png"