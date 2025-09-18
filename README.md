
---

# OrangeHRMBDD Automation Framework

[![Maven Build](https://img.shields.io/badge/Maven-Build-brightgreen)](https://maven.apache.org/)
[![Allure Report](https://img.shields.io/badge/Allure-Report-blue)](link_to_allure_report)
[![Extent Report](https://img.shields.io/badge/Extent-Report-orange)](link_to_extent_report)
[![ChainTest Report](https://img.shields.io/badge/ChainTest-Report-purple)](link_to_chaintest_report)

Automation framework for **OrangeHRM** using **Selenium WebDriver**, **Cucumber BDD**, and **TestNG**, with **Allure**, **ExtentReports**, and **ChainTest** integration for detailed reporting.
The Orange HRM has been hosted locally 

---

## 🔹 Table of Contents

* [Overview](#overview)
* [Folder Structure](#folder-structure)
* [Automated Scenarios](#automated-scenarios)
* [Installation](#installation)
* [Usage](#usage)
* [Getting Started](#getting-started)
* [Reports](#reports)
* [Technologies](#technologies)
* [Contributing](#contributing)
* [License](#license)
* [Local OrangeHRM Setup](#Local_Orange_HRM_Setup)

---

## 🔹 Overview

This repository contains BDD-style automated tests for OrangeHRM. It includes:

* Login scenarios with valid and invalid credentials
* Employee management: add, search, assign admin roles, delete
* Excel-driven test data
* Parallel and cross-browser execution
* Detailed reports using **Allure**, **ExtentReports**, and **ChainTest**

---

## 🔹 Folder Structure

```
OrangeHRMBDD/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── utilities/               # Utility classes, Excel readers, config
│   │
│   ├── test/
│       ├── java/
│       │   ├── stepDefinitions/         # Step definitions
│       │   ├── runners/                 # TestNG/Cucumber runners
│       │   └── pageObjects/             # Page Object Model classes
│       │
│       └── resources/
│           ├── features/                # All feature files
│           └── testData/                # Excel or CSV data files
│
├── pom.xml                               # Maven configuration
├── target/OrangeHRMHtmlReports/Index.html   # ExtentReports output
├── allure-results/                        # Allure results
└── target/chaintest/                     # ChainTest HTML report
```

---

## 🔹 Automated Scenarios

| **Epic**             | **Feature**           | **Scenario**                                              | **Tags**                |
| -------------------- | --------------------- | --------------------------------------------------------- | ----------------------- |
| LoginModule          | ValidLogin            | Verify OrangeHRM login with Excel data                    | `@LoginTest`            |
| LoginModule          | ValidLogin            | Invalid login with multiple credentials                   | Scenario Outline        |
| Employee\_Creation   | Add\_New\_Employee    | Verify adding a new employee after logging into OrangeHRM | `@AddNewEmployeeTest`   |
| Search\_Employee     | Search\_New\_Employee | Verify searching employee by first name after login       | `@SearchEmployeeByName` |
| Employee\_Management | Admin\_Assignment     | Verify adding an employee as admin                        | `@AddEmployeeAsAdmin`   |
| Employee\_Management | Employee\_Login       | Verify if the employee can login with new credentials     | `@LoginNewEmployee`     |
| Employee\_Management | Employee\_Delete      | Verify if the employee created is deleted                 | `@DeleteEmployee`       |

---

## 🔹 Installation

1. Clone the repository:

```bash
git clone https://github.com/username/OrangeHRMBDD.git
```

2. Navigate to the project folder:

```bash
cd OrangeHRMBDD
```

3. Install dependencies (Maven):

```bash
mvn clean install
```

---

## 🔹 Usage

1. Configure `config.properties` with environment details.
2. Place test data in `src/test/resources/testData/`.
3. Run all tests:

```bash
mvn test
```

4. Run a specific feature file:

```bash
mvn test -Dcucumber.options="--glue stepDefinitions src/test/resources/features/04_AddEmployeeAsAdmin.feature"
```

---

## 🔹 Getting Started

After running the tests, you can view the reports:

### **Allure Report**

<img width="1465" height="837" alt="image" src="https://github.com/user-attachments/assets/b9da1d90-e2fe-47f5-b7b4-77199787d7ac" />




### **Extent Reports**

<img width="1465" height="846" alt="image" src="https://github.com/user-attachments/assets/e8e85591-875f-46a4-a4f9-544f7bbe86aa" />





### **ChainTest Reports**

<img width="1469" height="849" alt="image" src="https://github.com/user-attachments/assets/035f0b38-9613-432b-8df2-b2bca1811bde" />




---

## 🔹 Technologies

* Java
* Selenium WebDriver
* Cucumber BDD
* TestNG
* Maven
* Excel for test data
* Allure, ExtentReports & ChainTest for reporting

---

---



# OrangeHRM Local Setup for Selenium Automation

## Prerequisites

* **Web server:** XAMPP/WAMP/LAMP
* **PHP:** 7.4 or 8.0
* **MySQL:** 5.7+
* **Composer** (optional)
* **Java JDK** (for Selenium)
* **Browser drivers:** ChromeDriver, GeckoDriver, etc.

---

## 🔹 Download OrangeHRM

1. GitHub: [https://github.com/orangehrm/orangehrm](https://github.com/orangehrm/orangehrm)
2. Download or clone the repo
3. Extract to web server root (`htdocs` for XAMPP)

---

## 🔹 Set Up Database

```sql
CREATE DATABASE orangehrm CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

* **Host:** localhost
* **Port:** 3306
* **DB Name:** orangehrm
* **DB User:** root
* **DB Pass:** (your MySQL root password)

---

## 🔹Install OrangeHRM

1. Open: [http://localhost/orangehrm](http://localhost/orangehrm)
2. Follow the wizard:

   * Select language
   * Accept license
   * Enter DB credentials
   * Set admin credentials
   * Complete installation

---

## Tips for Selenium Automation

* Disable CAPTCHA (Admin → Config → Authentication)
* Use explicit waits instead of `sleep()`
* Implement Page Object Model for maintainability
* Consider headless mode for faster execution
* Use a separate test database

---


## 🔹Links

* [OrangeHRM GitHub](https://github.com/orangehrm/orangehrm)
* [Session fix discussion](https://github.com/orangehrm/orangehrm/issues/1750#issuecomment-1740291114)

---




