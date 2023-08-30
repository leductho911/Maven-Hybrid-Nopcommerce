## Hybrid Test Automation Framework TestNG and Selenium Java building by Le Duc Tho

**SOME FEATURES IN FRAMEWORK**

1. Run the parallel on runTest.xml file
2. Allure Report
3. Write Log to file
4. Record video and Screenshot test case
5. Read data test from external file (json)
6. Base function in the package: commons, utils
7. Design by Page Object Pattern
8. Run test on multiple environment : dev, staging, product
9. Run test on local, grid (Docker), cloud (Browserstack)

### **SYSTEM REQUIREMENTS**

- **JDK version 11 min**
- Chrome Browser, Edge Browser, Firefox browser
- Setup Allure:
  https://mvnrepository.com/artifact/io.qameta.allure/allure-java-commons Download jar and setting Variable Environment
  as Java JDK
- Setup Docker Desktop for running test with Docker
- IntelliJ is the best choice (to change JDK version)

### **HOW TO USE**

**1. Run the parallel on runTest.xml file**

- Edit suite "parallel" in "src/test/resources/runTest.xml" file

**2. Allure Report**

- Open Terminal: allure serve target/allure-results
- Insert @Step("title/message") above @Test or any Method in the project

**3. Write Log to file**

- Call class: Log.info , Log.pass, Log.error,... (Log is a custom global class from Log4j2) (import utils.Log.java)

**4. Record video and Screenshot test case**

- Config screenshot and video recording in src/main/java/reportConfig

**5. Read data test from external file (json)**

- DataUtil class

**6. Base function in the package: commons, utils**

- src/main/java/anhtester/com/utils

**7. Design by Page Object Pattern**

**8. Run test on multiple environment : dev, staging, product**

- Run test by command line : mvn clean test -Denvironment=*
- Replace * = dev / staging / prod
- Setting for each environment in src/test/resources files

**9. Run test on local, grid (Docker), cloud (Browserstack)**

- Edit parameter name="service" in runTest.xml file

### Project structure

Updating ...

### I shall write document for my Framework. Coming soon...

## Copyright 2023 Le Duc Tho