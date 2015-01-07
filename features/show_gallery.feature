Feature: Show the Gallery

  Scenario: I can open the gallery, and see the galleryGridView
    When I press "Show Gallery"
    Then I see element with id "galleryGridView"
    Then I take a picture
    