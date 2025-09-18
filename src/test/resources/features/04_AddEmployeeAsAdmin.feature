@Epic(Employee_Management) @Feature(Admin_Assignment)
Feature: Add created employee as admin

  Background: 
    Given User navigates to PIM after login

  @AddEmployeeAsAdmin
  Scenario: Verify if adding an employee as admin is successful
    Given User navigates to Admin module
    When User assigns admin role by entering employee name, user role, status, username, and password
    Then User saves the entry and should see the message "Successfully Saved"
