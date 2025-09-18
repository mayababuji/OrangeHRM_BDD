Feature: Login as new employee

  @Epic(Employee_Management) @Feature(Employee_Login) @LoginNewEmployee
  Scenario: Verify if the employee can login with new credentials
    Given User enters valid credentials from excel sheet "employee"
    When User logs on with valid credentials
    Then User should land in dashboard page
