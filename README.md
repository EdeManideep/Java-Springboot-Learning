- 🔗 **Swagger UI**: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- 
## ✅ To-Do Tasks

1. **Add Sonar Plugin to IntelliJ**

   * Install and configure SonarLint
   * Resolve all warnings as per Sonar
   * Stop auto-save in IntelliJ to control scan triggers

2. **Convert Logging to SLF4J with Streams**

   * Use `@Slf4j` annotation for cleaner logging integration

3. **Write JUnit Test Cases**

   * Cover all endpoint URLs
   * Achieve 100% test coverage

4. **Learn Kafka + Spring Boot**

   * Understand Kafka basics and Spring Boot integration

5. **Learn Java Strings**

   * Understand differences between `String`, `StringBuilder`, and `StringBuffer`

6. **Create Custom Annotations in Spring Boot**

   * Example use case: Logging annotation

7. **Define and Work with Swagger File**

   * Create a Swagger/OpenAPI YAML file manually
   * Learn to use [https://editor.swagger.io/](https://editor.swagger.io/) for validation

8. **Learn JPA Criteria Queries**

   * Use dynamic queries via `CriteriaBuilder` and `CriteriaQuery`

9. **Learn and Implement Reactive Java**

   * Study `WebFlux` and reactive patterns in Spring Boot

10. **Set Up Database Versioning**

    * Use tools like Flyway or Liquibase
    * Reference: [How to store H2 database file](https://stackoverflow.com/questions/43470295/how-to-store-h2-database-file-into-project-directory)

11. **Implement Predicate**

    * Use Java predicates for filtering logic in search endpoints

12. **Fix Maven Build**

    * Check and resolve errors in `Dev` branch workflow actions

---

## ✅ Completed Tasks

13. **Add Logging (Log4j2)**

    * Integrated `log4j2` for logging

14. **Filter Endpoint with Streams**

    * Created endpoint for filtering by name
    * ✔️ 5.1: Added pagination
    * ✔️ 5.2: Predicate logic pending (see To-Do #11)

15. **Detailed Swagger Documentation**

    * All possible exceptions and outcomes documented
    * ✔️ 3.1: Swagger file pending (see To-Do #7)

16. **Custom Exception Handling**

    * ✔️ 2.1: Handled invalid URI
    * ✔️ 2.2: Learned difference between URI & URL
    * ✔️ 2.3: Handled invalid HTTP verbs
    * ✔️ 2.4: Managed invalid arguments

17. **Pagination for Get All Employees**

    * Implemented pagination
    * ✔️ 4.1: Added pagination metadata in response

18. **Update HTTP Status Codes**

    * `getById` and `getAll` now respond with HTTP 204 (No Content) instead of 404

19. **Database File Persistence**

    * Configured H2 to persist data to project directory

20. **Change Data Type of Salary Field**

    * Updated `salary` field datatype

21. **Dockerize the Project**

    * Added Docker support and configuration

22. **Scheduled Email Task**

    * Wrote a scheduler to run every 24 hours and email newly created employees
