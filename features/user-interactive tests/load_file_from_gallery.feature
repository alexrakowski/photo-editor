Feature: Load Image to Edit from Memory

  Scenario: I can load a picture from memory in the Main Menu
    Given I press view with id "selectImageImageView"
    Then I take a picture named "source_for_the_image_alert_dialog.png"
    Then I tap the text "Select from Gallery"
    Then I wait for 15 seconds
    Then I take a picture named "after_image_selection.png"
    