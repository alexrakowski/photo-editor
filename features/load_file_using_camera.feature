Feature: Load Image to Edit from Camera

  Scenario: I can load a picture using Camera in the Main Menu
    Given I press view with id "selectImageImageView"
    Then I take a picture named "source_for_the_image_alert_dialog.png"
    Then I tap the text "Use the Camera"
    Then I wait for 15 seconds
    Then I take a picture named "after_camera_usage.png"
    