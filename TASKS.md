# Tasks Guide for Spring Batch 5 Project

This document provides common development tasks and workflows for the Spring Batch 5 demonstration project.

## Setup & Build Tasks

### Build the Project
```bash
mvn clean package
```
Compiles the code, runs tests, and creates the JAR artifact.

**When to use:** Before deploying, after major changes, or to verify the project builds successfully.

---

### Run Tests
```bash
mvn test
```
Executes all unit tests in the project.

**When to use:** After making code changes, before committing, or to verify batch job configurations work correctly.

---

### Run Specific Test
```bash
mvn test -Dtest=SpringBatchDemoApplicationTests
```
Runs a specific test class.

**When to use:** When debugging a particular test or component.

---

### Start the Application
```bash
mvn spring-boot:run
```
Runs the Spring Boot application locally.

**When to use:** For local development, testing REST endpoints, accessing the H2 console at `http://localhost:9090/h2-console/`.

---

## Development Tasks

### Add a New Batch Job
**Steps:**
1. Create a new job configuration in `SpringBatchConfig` or separate config class
2. Define reader (ItemReader), processor (ItemProcessor), writer (ItemWriter)
3. Register the job using JobBuilderFactory
4. Create corresponding REST endpoint in `JobController`
5. Write tests to verify job executes correctly

**Related files:**
- `src/main/java/com/codesnippet/SpringBatchDemo/config/SpringBatchConfig.java`
- `src/main/java/com/codesnippet/SpringBatchDemo/controller/JobController.java`

---

### Create a New Entity
**Steps:**
1. Create entity class in `src/main/java/com/codesnippet/SpringBatchDemo/entity/`
2. Annotate with `@Entity`, `@Table`, and appropriate `@Column` annotations
3. Create corresponding JPA repository in the `repository` package
4. If needed, create a processor to handle the entity in a batch job

**Related files:**
- `src/main/java/com/codesnippet/SpringBatchDemo/entity/Person.java`
- `src/main/java/com/codesnippet/SpringBatchDemo/repository/PersonRepository.java`

---

### Create a Custom Item Processor
**Steps:**
1. Extend `ItemProcessor<I, O>` interface
2. Implement `process(T item)` method
3. Add business logic for data transformation
4. Handle exceptions appropriately
5. Register in batch job configuration

**Related files:**
- `src/main/java/com/codesnippet/SpringBatchDemo/config/PersonProcessor.java`

---

### Add a REST API Endpoint
**Steps:**
1. Open or create a controller class in `src/main/java/com/codesnippet/SpringBatchDemo/controller/`
2. Inject `JobLauncher` and `Job` beans
3. Create mapping method (e.g., `@PostMapping`, `@GetMapping`)
4. Use `JobLauncher.run()` to trigger batch jobs
5. Return job execution status or response

**Related files:**
- `src/main/java/com/codesnippet/SpringBatchDemo/controller/JobController.java`

---

## Testing Tasks

### Test a Batch Job
**Steps:**
1. Create test class extending `ApplicationTests`
2. Inject `JobLauncher` and relevant `Job` beans
3. Create test data or use repositories
4. Execute job using `JobLauncher.run()`
5. Assert job completion status and outcomes

**Related files:**
- `src/test/java/com/codesnippet/SpringBatchDemo/SpringBatchDemoApplicationTests.java`

---

### Test a Custom Processor
**Steps:**
1. Create unit test for the processor
2. Create sample input data
3. Call `processor.process(item)`
4. Assert expected output

---

### Verify Database State After Job
**Steps:**
1. Access H2 console at `http://localhost:9090/h2-console/`
2. Use credentials from `application.properties`: root / bismillah
3. Query tables to verify batch job results
4. Use `PersonRepository` in tests to verify data

---

## Debugging Tasks

### Debug Batch Job Execution
**Steps:**
1. Add breakpoints in processor or job config
2. Run with `mvn spring-boot:run` or in IDE debug mode
3. Monitor job parameters and item processing
4. Check job repository for execution history

---

### Inspect Database State
**Steps:**
1. Start application: `mvn spring-boot:run`
2. Navigate to `http://localhost:9090/h2-console/`
3. Login with: username=root, password=bismillah
4. Query `PERSON` table or other relevant tables

---

### Review Spring Batch Metadata
**Steps:**
1. After running a job, check Spring Batch metadata tables:
   - `BATCH_JOB_INSTANCE`
   - `BATCH_JOB_EXECUTION`
   - `BATCH_STEP_EXECUTION`
2. These tables track job execution history

---

## Code Quality Tasks

### Run Maven Validation
```bash
mvn validate
```
Checks POM syntax and project structure.

---

### Check for Compilation Errors
```bash
mvn compile
```
Compiles only without running tests.

---

### Generate Project Report
```bash
mvn site
```
Generates documentation and reports (requires `maven-site-plugin`).

---

## Deployment Tasks

### Create Production JAR
```bash
mvn clean package -DskipTests
```
Builds JAR without running tests for production deployment.

---

### Run JAR File
```bash
java -jar target/SpringBatchDemo-0.0.1-SNAPSHOT.jar
```
Executes the built JAR file independently.

---

## Common Troubleshooting Tasks

### Clear Maven Cache
```bash
mvn clean
```
Removes the target directory and build artifacts.

---

### Update Maven Dependencies
```bash
mvn dependency:resolve
```
Downloads and resolves all dependencies.

---

### Check Test Coverage
Add test dependencies to `pom.xml` and use:
```bash
mvn jacoco:report
```

---

## Project Structure Reference

```
src/
├── main/java/com/codesnippet/SpringBatchDemo/
│   ├── SpringBatchDemoApplication.java          # Main entry point
│   ├── config/
│   │   ├── SpringBatchConfig.java               # Batch job configurations
│   │   └── PersonProcessor.java                 # Item processor
│   ├── entity/
│   │   └── Person.java                          # JPA entity
│   ├── repository/
│   │   └── PersonRepository.java                # JPA repository
│   └── controller/
│       └── JobController.java                   # REST endpoints
├── resources/
│   └── application.properties                   # Configuration
└── test/java/...                                # Test classes
```

---

## Tips & Best Practices

1. **Always run tests after changes**: `mvn test`
2. **Check database state in H2 console** after batch jobs complete
3. **Use Spring Batch metadata tables** to troubleshoot job failures
4. **Keep ItemProcessors simple** and focused on a single responsibility
5. **Handle exceptions in processors** to prevent jobs from failing unexpectedly
6. **Test with small datasets first**, then scale to larger data
7. **Monitor job execution parameters** to ensure batch jobs receive correct input

---

## Further Reading

- See **AGENTS.md** for available tools and agents
- Spring Batch Documentation: https://spring.io/projects/spring-batch
- Spring Boot Reference: https://spring.io/projects/spring-boot
