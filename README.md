# ToRest API testing using Rest-Assured Java Framework
This is repository of basic REST API testing framework
The public API used for this example is - https://www.tokopedia.com/
This framework is developed using Rest-Assured library for Rest API testing
For JSON Parsing in java the library used is - json-simple and Jackson API
Reporting is by Allure API

# Technologies/Tools used in building the framework
1. Rest Assured
2. TestNG
3. Java
4. Allure Reports
5. Lombok
6. IntelliJ
7. GitHub
8. Maven
9. Extent Report

# Framework implements below best practices
1. Scalable and extensible
2. Reusable Rest Assured specifications
3. Reusable Rest Assured API requests
4. Separation of API layer from test layer
5. POJOs for Serialization and Deserialization
6. Singleton Design Pattern
7. Lombok for reducing Boilerplate code
8. Builder pattern for Setter methods in POJOs
9. Robust reporting and logging using Allure
10. Automate positive and negative scenarios
11. Support parallel execution
12. Data driven using TestNG Data Provider
13. Automated access token renewal
14. Maven command line execution
15. Integration with Git
16. Integration with Jenkins

#How to run the tests?
1. First way Open the termial and code the project location and run mvn command - mvn test
2. Second way right click on the "TestSuite.xml" file
3. Third way right click on the ValidateOrder inside testcases package

#Scenarios Covered
1. Positive Flow:
   * Valid input minimum and maximum length of order id
   * Valid input order status
   * Valid input timestamp
2. Negative Flow:
   * Invalid input such as invalid order id
   * Invalid input such as invalid order status
   * Invalid input such as invalid timestamp format
   * Invalid header file type
3. Error Message:
   * Verify error message for different input data
4. Security Flow:
   * Invalid Authorization values in header
   * Request without authorization header
5. Performance Flow:
   * Validate response time take for request
   * Validate the latency for each request



