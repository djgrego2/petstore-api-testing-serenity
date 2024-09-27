Feature: Pet Store API - User Management

  Scenario: Create a new user
    Given a user with username "gregorio_perez" and email "gregorio.perez@example.com"
    When I create the user
    Then the user is successfully created

  Scenario: Retrieve a user by username
    Given a user with username "gregorio_perez2" exists
    When I request the user with username "gregorio_perez2"
    Then the user details are correctly returned

  Scenario: Update an existing user
    Given a user with username "gregorio_perez3" exists
    When I update the user's email to "gregorio.perez3.new@example.com"
    Then the user is successfully updated

  Scenario: Delete a user
    Given a user with username "gregorio_perez4" exists
    When I delete the user with username "gregorio_perez4"
    Then the user is successfully deleted

  Scenario: User login
    Given a user with username "gregorio_perez5" and email "gregorio.perez5@example.com"
    And I create the user
    When I login with username "gregorio_perez5" and password "securepass123"
    Then the login is successful

  Scenario: User logout
    Given a user with username "gregorio_perez6" and email "gregorio.perez6@example.com"
    And I create the user
    And I login with username "gregorio_perez6" and password "securepass123"
    When I logout the user
    Then the logout is successful

  Scenario: Create users with list
    Given a list of users
    When I create the users with list
    Then the users are successfully created

  Scenario: Create multiple users
    Given a user with username "gregorio_perez7" and email "gregorio.perez7@example.com"
    And I create the user
    And a user with username "gregorio_perez8" and email "gregorio.perez8@example.com"
    And I create the user
    When I request the user with username "gregorio_perez7"
    Then the user details are correctly returned
    When I request the user with username "gregorio_perez8"
    Then the user details are correctly returned

  Scenario: Update user phone number
    Given a user with username "gregorio_perez9" exists
    When I update the user's phone to "9988776655"
    Then the user is successfully updated

  Scenario: Attempt to create duplicate user
    Given a user with username "gregorio_perez10" and email "gregorio.perez10@example.com"
    And I create the user
    When I create the user
    Then the creation fails due to duplicate username