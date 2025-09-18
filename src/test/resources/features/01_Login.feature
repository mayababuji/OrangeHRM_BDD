Feature: Login using Excel Test Data

  @Epic(LoginModule) @Feature(ValidLogin) @LoginTest
  Scenario: Verify OrangeHRM login with Excel data
    Given user launches OrangeHRM
    When user logs in with test case "ValidCredentials" from sheet "Login"
    Then dashboard should be displayed

  @Feature(InvalidLogin) @InvalidLoginTest
  Scenario Outline: Invalid login with multiple credentials
    Given user launches OrangeHRM
    When user enters username "<Username>" and password "<Password>"
    Then error message should be displayed "Invalid credentials"

    Examples: 
      | Username   | Password   |
      | Admin      | wrongPass1 |
      | wrongUser2 | wrongPass2 |
      | wrongUser3 | wrongPass3 |
