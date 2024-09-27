Feature: Pet Store API - Store Management

  Scenario: Place an order for a pet
    Given an order for pet ID 1 with quantity 2
    When I place the order
    Then the order is successfully placed

  Scenario: Retrieve an order by ID
    Given an order with ID 1 exists in the store
    When I request the order with ID 1
    Then the order details are correctly returned

  Scenario: Delete an order
    Given an order with ID 2 exists in the store
    When I delete the order with ID 2
    Then the order is successfully deleted

  Scenario: Get store inventory
    When I request the store inventory
    Then I receive the inventory status