Feature: Show saved Images in Gallery

  Scenario: I can edit the default photo, save it, and view it in the Gallery
    Given I press view with id "selectImageImageView"
    Then I tap the text "Use the default image"
    Then I press view with id "convertButton"
    Then I wait for the view with id "moustachesLayout" to show up
    Then I tap the 1 view in layout with id "moustachesLayout"
    Then I tap the 1 view in layout with id "glassesLayout"
    Then I tap the 1 view in layout with id "hatsLayout"
    Then I press view with id "confirmContentButton"
    Then I wait for the view with id "moveContentImageView" to show up
    Then I tap the text "Finish"
    Then I wait for the view with id "editedImageImageView" to show up
    Then I take a picture named "first_edited_image.png"
    Then I tap the text "Save this Photo" and I should see text containing "Successfully saved the photo"
    Then I tap the text "Return to Main Menu"
    
    Then I should see text containing "Tap to select picture"
    Given I press view with id "selectImageImageView"
    Then I tap the text "Use the default image"
    Then I press view with id "convertButton"
    Then I wait for the view with id "moustachesLayout" to show up
    Then I tap the 2 view in layout with id "moustachesLayout"
    Then I tap the 2 view in layout with id "glassesLayout"
    Then I tap the 2 view in layout with id "hatsLayout"
    Then I press view with id "confirmContentButton"
    Then I wait for the view with id "moveContentImageView" to show up
    Then I tap the text "Finish"
    Then I wait for the view with id "editedImageImageView" to show up
    Then I take a picture named "second_edited_image.png"
    Then I tap the text "Save this Photo" and I should see text containing "Successfully saved the photo"
    Then I tap the text "Return to Main Menu"
    
    Then I should see text containing "Tap to select picture"
    Given I press view with id "selectImageImageView"
    Then I tap the text "Use the default image"
    Then I press view with id "convertButton"
    Then I wait for the view with id "moustachesLayout" to show up
    Then I swipe the view with id "moustachesLayout" to the right
    Then I tap the 2 view in layout with id "moustachesLayout"
    Then I swipe the view with id "glassesLayout" to the right
    Then I tap the 2 view in layout with id "glassesLayout"
    Then I tap the 2 view in layout with id "hatsLayout"
    Then I press view with id "confirmContentButton"
    Then I wait for the view with id "moveContentImageView" to show up
    Then I tap the text "Finish"
    Then I wait for the view with id "editedImageImageView" to show up
    Then I take a picture named "third_edited_image.png"
    Then I tap the text "Save this Photo" and I should see text containing "Successfully saved the photo"
    Then I tap the text "Return to Main Menu"
    
    Then I should see text containing "Tap to select picture"
    Given I press view with id "selectImageImageView"
    Then I tap the text "Use the default image"
    Then I press view with id "convertButton"
    Then I wait for the view with id "moustachesLayout" to show up
    Then I tap the 1 view in layout with id "moustachesLayout"
    Then I tap the 2 view in layout with id "glassesLayout"
    Then I tap the 2 view in layout with id "hatsLayout"
    Then I press view with id "confirmContentButton"
    Then I wait for the view with id "moveContentImageView" to show up
    Then I tap the text "Finish"
    Then I wait for the view with id "editedImageImageView" to show up
    Then I take a picture named "fourth_edited_image.png"
    Then I tap the text "Save this Photo" and I should see text containing "Successfully saved the photo"
    Then I tap the text "Return to Main Menu"
    
    Then I should see text containing "Tap to select picture"
    Then I press "Show Gallery"
    Then I see element with id "galleryGridView"
    Then I should see at least 4 "ImageView" elements in view with id "galleryGridView"
    Then I take a picture named "images_in_gallery.png"
    
    