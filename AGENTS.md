# Agents Guide for Spring Batch 5 Project

This document describes the available agents and tools for working on this Spring Batch 5 demonstration project.

## Available Agents

### **explore** - Codebase Research
Specialized for fast parallel investigation of multiple independent areas.

**Use when:**
- Analyzing multiple modules or components simultaneously
- Tracing flows across entity relationships, processors, and jobs
- Understanding interactions between Spring Batch components and JPA repositories
- Investigating how controllers integrate with batch jobs

**Example use:**
- "Explore how Person entity relates to the batch processor and job configuration"
- "Research all components in the SpringBatchConfig and how they chain together"

---

### **task** - Execution & Validation
Runs commands with focus on success/failure status. Best for build, test, and deployment tasks.

**Use when:**
- Building the project (`mvn clean package`)
- Running tests (`mvn test`)
- Starting the application (`mvn spring-boot:run`)
- Executing batch jobs programmatically
- Database operations

**Example use:**
- "Run all tests to verify the batch configuration works correctly"
- "Build the project and verify no compilation errors"

---

### **general-purpose** - Complex Multi-Step Tasks
Full-capability agent for involved development work requiring iteration and refinement.

**Use when:**
- Adding new batch job configurations with multiple processors and readers/writers
- Refactoring existing batch jobs
- Creating new API endpoints that trigger batch jobs
- Implementing new database layers
- Complex debugging scenarios

**Example use:**
- "Add a new batch job that reads from CSV and writes to the database"
- "Create a new REST endpoint that allows triggering batch jobs"

---

### **code-review** - Quality Assurance
Analyzes code changes for bugs, security issues, and logic errors (ignores style/formatting).

**Use when:**
- Reviewing changes before committing
- Verifying batch job logic correctness
- Checking Spring Batch configurations for potential issues
- Ensuring proper transaction handling
- Validating data processing pipeline

**Example use:**
- "Review the changes to SpringBatchConfig for potential issues"
- "Check if the PersonProcessor correctly handles exceptions"

---

### **research** - Documentation & Investigation
Thorough search and verification of information.

**Use when:**
- Understanding Spring Batch 5 best practices
- Looking up Spring Boot or Spring Data JPA documentation
- Finding examples for batch job patterns
- Verifying compatibility between library versions

**Example use:**
- "Research best practices for error handling in Spring Batch 5 processors"
- "Find examples of multi-step batch jobs in Spring Batch documentation"

---

## CLI Tools

### **Built-in Tools (Preferred)**

- **grep**: Search code content using ripgrep patterns
- **glob**: Find files by name patterns (Java classes, XML configs, etc.)
- **view**: Read file content and directory structure
- **edit**: Modify existing files
- **create**: Create new files

### **PowerShell**

Use for:
- Complex build configurations
- Running Maven commands with specific profiles
- Executing Java applications directly
- Database console access

---

## Typical Workflows

### **Adding a New Batch Job**
1. Use **explore** to understand current job structure
2. Use **general-purpose** to implement new job configuration
3. Use **code-review** to validate the implementation
4. Use **task** to run tests and verify

### **Debugging Issues**
1. Use **grep** to find relevant code sections
2. Use **explore** to trace data flow through components
3. Use **general-purpose** for complex debugging
4. Use **task** to run tests in isolation

### **Refactoring Existing Code**
1. Use **grep/glob** to understand scope of changes
2. Use **code-review** to plan changes
3. Use **general-purpose** for implementation
4. Use **task** to verify refactoring didn't break anything

---

## Project Context

- **Language**: Java 21
- **Framework**: Spring Boot 3.3.5
- **Batch Framework**: Spring Batch 5
- **Database**: H2 in-memory
- **Build Tool**: Maven
- **Key Components**:
  - `SpringBatchDemoApplication`: Main entry point
  - `SpringBatchConfig`: Batch job configuration
  - `PersonProcessor`: Item processor for batch
  - `Person`: Entity model
  - `PersonRepository`: JPA repository
  - `JobController`: REST API for job control

---

## Getting Started

See **TASKS.md** for common tasks to perform on this project.
