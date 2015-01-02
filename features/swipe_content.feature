Feature: Swipe content layouts

  Scenario: I can swipe the layouts with content
    Given I press view with id "selectImageImageView"
    Then I tap the text "Use the default image"
    Then I press view with id "convertButton"
    Then I wait for the view with id "moustachesLayout" to show up
    Then I take a picture named "before_moustaches_swipe.png"
    Then I swipe the view with id "moustachesLayout" to the right
    Then I take a picture named "after_moustaches_swipe_to_the_right.png"
    Then I swipe the view with id "moustachesLayout" to the left
    Then I take a picture named "after_moustaches_swipe_to_the_left.png"
    Then I swipe the view with id "hatsLayout" to the right
    Then I swipe the view with id "glassesLayout" to the right
    Then I take a picture named "after_glasses_and_hats_swipe_to_the_right.png"
    Then I swipe the view with id "hatsLayout" to the left
    Then I swipe the view with id "glassesLayout" to the left
    Then I take a picture named "after_glasses_and_hats_swipe_to_the_left.png"