Feature: Pet Store API - Pet Management

  Scenario: Add a new pet to the store
    Given a pet with name "Fluffy" and status "available"
    When I add the pet to the store
    Then the pet is successfully added

  Scenario: Retrieve a pet by ID
    Given a pet with ID 1 exists in the store
    When I request the pet with ID 1
    Then the pet details are correctly returned

  Scenario: Update an existing pet
    Given a pet with ID 2 exists in the store
    When I update the pet's name to "Buddy" and status to "sold"
    Then the pet is successfully updated

  Scenario: Delete a pet
    Given a pet with ID 3 exists in the store
    When I delete the pet with ID 3
    Then the pet is successfully deleted

  Scenario: Find pets by status
    Given a pet with name "Rex" and status "pending"
    And I add the pet to the store
    When I find pets by status "pending"
    Then I receive a list of pets with status "pending"

  Scenario: Update a pet with form data
    Given a pet with ID 4 exists in the store
    When I update the pet with ID 4 with name "Max" and status "sold"
    Then the pet is successfully updated

  Scenario: Upload an image for a pet
    Given a pet with ID 5 exists in the store
    When I upload an image for the pet with ID 5
    Then the image is successfully uploaded