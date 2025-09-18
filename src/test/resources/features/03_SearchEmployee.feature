Feature: Search an Employee by Frist Name

  @Epic(Search_Employee) @Feature(Search_New_Employee) @SearchEmployeeByName
  Scenario: Verify searching employee by employee first name after login
    Given User navigates to PIM after login
    When User  enter the new employee details from sheet "employee"  proceeding to search
    Then User retrives "Record Found" message
