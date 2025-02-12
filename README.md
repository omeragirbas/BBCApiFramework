# BBC API Framework

## Overview

The **BBC API Framework** is a robust, modular, and scalable test automation framework designed to validate REST APIs. Built with Java and Maven, this framework incorporates industry-standard tools like Cucumber and JUnit to ensure efficient, reliable, and readable test execution. The framework is tailored for comprehensive API testing.

---

## Features

- **Cucumber Integration**: Behavior-driven development (BDD) approach with human-readable test scenarios.
- **Reusable Hooks and Step Definitions**: Minimized redundancy through modular design.
- **Dynamic Configuration**: Centralized management of base URLs and other settings.
- **Customizable Reports**: Detailed execution reports in HTML and JSON formats.

---

## Prerequisites

To run this framework, ensure the following are installed:

- **Java**: JDK 8 or higher
- **Maven**: Version 3.6.3 or higher
- **Git**: Version control system for cloning the repository

Optional:
- **IDE**: IntelliJ IDEA, Eclipse, or any Java-compatible IDE

---

## Setup

### Clone the Repository
```bash
git clone https://github.com/omeragirbas/BBCApiFramework
cd BBCApiFramework
```

### Install Dependencies
Run the following Maven command to download required dependencies:
```bash
mvn clean install
```

### Configure Environment
- Update the `BaseUrl.java` file under `src/test/java/com/bbc/sports/baseurl` to set the appropriate base URL for the environment being tested.
- Modify any other required parameters in the `pom.xml` or configuration files.

---

## Running the Tests

### Execute All Tests
```bash
mvn clean test
```
---

## Assumptions

1. The target API endpoints are accessible and functional.
2. Proper authentication credentials (e.g., API keys) are available and configured.
3. Test data is preloaded into the system under test.
4. The test environment replicates the production environment to a reasonable extent.

---

## External Dependencies

- **Third-Party APIs**: Integration with external APIs may be required during testing.
- **Database Connectivity**: Ensure the test environment database is configured and accessible if tests interact with a database.
- **Build Tools**: Maven is required to manage dependencies and execute tests.

---


## Reports

After executing the tests, reports will be generated in the `target` directory:

- **HTML Report**: `target/cucumber-reports.html`
- **JSON Report**: `target/cucumber.json`

Open the HTML report in a browser for detailed results.

---
