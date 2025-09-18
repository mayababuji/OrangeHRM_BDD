Feature: Add new Employee

  @Epic(Employee_Creation) @Feature(Add_New_Employee) @AddNewEmployeeTest
  Scenario: Verify adding new employee after logging into OrangeHRM
    Given User navigates to PIM and clicks in add after login
    When User  enter the new employee details from sheet "employee" and  saves the entries
    Then User recieves a new employee added "Successfully Saved" message
