# Contributing to Pathmodel

Thank you for your interest in contributing to **Pathmodel**! This guide will help you get started.

---

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Building the Project](#building-the-project)
- [Running Tests](#running-tests)
- [Code Style & Conventions](#code-style--conventions)
- [Commit Guidelines](#commit-guidelines)
- [Branch Workflow](#branch-workflow)
- [Pull Request Process](#pull-request-process)
- [CI Pipeline](#ci-pipeline)
- [Project Structure](#project-structure)

---

## Prerequisites

| Tool       | Version | Notes                              |
|------------|---------|------------------------------------|
| Java (JDK) | 17+     | Required by Maven and Spring Boot  |
| Maven      | 3.9+    | Or use the included `mvnw` wrapper |
| Git        | 2.x+    | Version control                    |

> **Tip:** You do not need to install Maven globally — the project ships with a Maven Wrapper (`mvnw` / `mvnw.cmd`).

---

## Getting Started

1. **Fork** the repository on GitHub.
2. **Clone** your fork locally:
   ```bash
   git clone https://github.com/<your-username>/Pathmodel.git
   cd Pathmodel
   ```
3. **Create a feature branch** from `main`:
   ```bash
   git checkout -b feature/my-feature
   ```

---

## Building the Project

All build commands are run from the `PathmodelBackend/` directory.

```bash
cd PathmodelBackend

# Using Maven Wrapper (recommended)
./mvnw clean install        # Linux / macOS
mvnw.cmd clean install      # Windows

# Using a globally installed Maven
mvn clean install
```

A successful build compiles the code, runs all tests, and packages the application.

### Running the Application

```bash
./mvnw spring-boot:run
```

---

## Running Tests

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw -Dtest=PathmodelBackendApplicationTests test

# Run tests with verbose output
./mvnw test -X
```

The project uses **JUnit 5** (via `spring-boot-starter-test`). All test classes reside under:

```
PathmodelBackend/src/test/java/
```

### Test Conventions

- Place tests in the same package as the class under test.
- Name test classes `<ClassName>Test` or `<ClassName>Tests`.
- Use descriptive method names: `shouldCalculatePortfolioValue_whenPricesChange()`.
- Prefer `@Test` (JUnit 5) over legacy JUnit 4 annotations.
- Keep unit tests fast — mock external dependencies.

---

## Code Style & Conventions

The project enforces the **Sun/Oracle Java Code Conventions** via [Checkstyle](https://checkstyle.org/). The full rules
are defined in [`config/checkstyle/checkstyle.xml`](PathmodelBackend/config/checkstyle/checkstyle.xml) and are
automatically checked during the Maven `validate` phase.

### Summary of Key Rules

#### Formatting

| Rule                   | Convention                                     |
|------------------------|------------------------------------------------|
| Indentation            | **4 spaces** (no tabs)                         |
| Line length            | Max **80 characters** (default Checkstyle)     |
| Braces                 | Always use braces, even for single-line blocks |
| Brace style            | Opening brace on the **same line** (K&R style) |
| Trailing whitespace    | **Not allowed**                                |
| Newline at end of file | **Required**                                   |

#### Naming

| Element              | Convention              | Example                            |
|----------------------|-------------------------|------------------------------------|
| Packages             | `lowercase`             | `org.philipp.fun.pathmodelbackend` |
| Classes / Interfaces | `PascalCase`            | `MarketSimulator`                  |
| Methods              | `camelCase`             | `calculateReturns()`               |
| Constants            | `UPPER_SNAKE_CASE`      | `MAX_ITERATIONS`                   |
| Local variables      | `camelCase`             | `dailyReturn`                      |
| Type parameters      | Single uppercase letter | `<T>`, `<E>`                       |

#### Imports

- **No wildcard imports** (`import java.util.*` ❌).
- **No unused imports**.
- **No redundant imports**.
- Order: `java.*`, `javax.*`, third-party, project imports — separated by blank lines.

#### Javadoc

- **Required** on all public classes and interfaces (`JavadocType`).
- **Required** on all public methods (`JavadocMethod`, `MissingJavadocMethod`).
- **Required** on all public fields (`JavadocVariable`).
- Follow the [Oracle Javadoc style guide](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html).

#### Coding Practices

- No empty statements or empty blocks.
- Always implement `equals()` **and** `hashCode()` together.
- No magic numbers — use named constants.
- Always include a `default` case in `switch` statements.
- One variable declaration per line.
- Simplify boolean expressions and returns.
- Methods should use **final parameters** where possible.
- Prefer composition over inheritance; classes should be `final` where appropriate.
- Utility classes should have a private constructor.

#### Example Class

```java
package org.philipp.fun.pathmodelbackend.domain;

/**
 * Represents a financial asset with a name and asset class.
 * @param name  The display name of the asset. 
 * @param assetClass  The asset class category. 
 */
public record BasicAsset(String name, AssetClass assetClass) implements Asset {

    /**
     * Creates a new BasicAsset.
     *
     * @param name       the display name
     * @param assetClass the asset class category
     */
    public BasicAsset {
    }

    /**
     * Returns the display name.
     *
     * @return the asset name
     */
    @Override
    public String name() {
        return name;
    }

    /**
     * Returns the asset class.
     *
     * @return the asset class
     */
    @Override
    public AssetClass assetClass() {
        return assetClass;
    }
}
```

---

## Commit Guidelines

We use **conventional commits** for a clean, readable history.

### Format

```
<type>(<scope>): <short summary>

<optional body>
```

### Types

| Type       | Usage                                   |
|------------|-----------------------------------------|
| `feat`     | New feature                             |
| `fix`      | Bug fix                                 |
| `docs`     | Documentation only                      |
| `style`    | Formatting, no logic change             |
| `refactor` | Code change that neither fixes nor adds |
| `test`     | Adding or updating tests                |
| `chore`    | Build, CI, tooling changes              |

### Examples

```
feat(domain): add Asset interface and BasicAsset implementation
fix(simulation): correct off-by-one in daily return calculation
docs: add CONTRIBUTING.md with coding conventions
test(portfolio): add unit tests for PortfolioValue calculation
chore(ci): add GitHub Actions workflow for Maven build
```

---

## Branch Workflow

| Branch      | Purpose                    |
|-------------|----------------------------|
| `main`      | Stable, release-ready code |
| `feature/*` | New features               |
| `fix/*`     | Bug fixes                  |
| `docs/*`    | Documentation changes      |
| `chore/*`   | Tooling and CI updates     |

1. Branch off `main`.
2. Make your changes in small, focused commits.
3. Push your branch and open a **Pull Request**.

---

## Pull Request Process

1. **Ensure** the build passes locally (`./mvnw clean install`).
2. **Ensure** all tests pass (`./mvnw test`).
3. **Follow** the code style — Checkstyle violations will fail the build.
4. **Write** or update tests for any new or changed functionality.
5. **Update** documentation (Javadoc, README, etc.) if needed.
6. **Open** a Pull Request against `main` with a clear description.
7. **Address** review feedback promptly.

### PR Checklist

- [ ] Code compiles without errors
- [ ] All tests pass
- [ ] Checkstyle passes with no violations
- [ ] New code has Javadoc comments
- [ ] Tests cover the new/changed functionality
- [ ] Commit messages follow the conventional commit format

---

## CI Pipeline

The project uses a CI pipeline (GitHub Actions) to ensure code quality on every push and pull request.

### What CI Checks

| Step       | Command                | Purpose                        |
|------------|------------------------|--------------------------------|
| Compile    | `mvn compile`          | Code compiles without errors   |
| Test       | `mvn test`             | All tests pass                 |
| Checkstyle | `mvn checkstyle:check` | Code style enforcement         |
| Package    | `mvn package`          | Application packages correctly |

> **Note:** CI must pass before a PR can be merged. If CI fails, check the build logs for details and fix any issues
> locally before pushing again.

### Running CI Checks Locally

You can replicate the full CI pipeline locally:

```bash
cd PathmodelBackend
./mvnw clean verify
```

This runs compilation, tests, and Checkstyle in one command.

---

## Project Structure

```
Pathmodel/
├── README.md                        # Project overview and roadmap
├── CONTRIBUTING.md                  # This file
├── PathmodelBackend/
│   ├── pom.xml                      # Maven build configuration
│   ├── mvnw / mvnw.cmd             # Maven Wrapper scripts
│   └── src/
│       ├── main/
│       │   ├── java/org/philipp/fun/pathmodelbackend/
│       │   │   └── ...              # Application source code
│       │   └── resources/
│       │       └── application.properties
│       └── test/
│           └── java/org/philipp/fun/pathmodelbackend/
│               └── ...              # Test source code
```

### Package Organization (Planned)

As the project grows, code will be organized into domain-oriented packages:

```
org.philipp.fun.pathmodelbackend
├── domain/          # Core domain objects (Asset, Portfolio, MarketState, ...)
├── market/          # Market models and simulation (GBM, returns, correlation)
├── simulation/      # Monte Carlo engine, path simulation
├── strategy/        # Investment strategies (BuyAndHold, Rebalance, ...)
├── statistics/      # Performance metrics, risk analytics
├── api/             # REST controllers and DTOs
└── config/          # Spring configuration
```

---

## Questions?

If you have questions or suggestions, feel free to open a [GitHub Issue](https://github.com/<owner>/Pathmodel/issues).

Thank you for contributing! 🎉

