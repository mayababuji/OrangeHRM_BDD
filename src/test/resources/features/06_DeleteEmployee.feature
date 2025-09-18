Feature: Delete the employee that is created

  @Epic(Employee_Management) @Feature(Employee_Delete) @DeleteEmployee
  Scenario: Verify if the employee created is deleted
    Given User navigates to PIM after login
    When User selects the employee to be deleted and deletes
    Then User should get a "Successfully Deleted" message
