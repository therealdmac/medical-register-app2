# Medical Register App

![CI](https://github.com/your-org/your-repo/actions/workflows/ci-selenium.yml/badge.svg)

A Spring Boot + JSF medical record system with:
- Auth0 or form-based login
- Role-based access (Admin, Doctor, Nurse)
- Audit logging
- Selenium UI tests with GitHub Actions CI
- PrimeFaces modern UI

## Features

| Feature        | Admin | Doctor | Nurse |
|----------------|-------|--------|-------|
| View Records   | ✅    | ✅     | ✅    |
| Create/Edit    | ✅    | ✅     | ❌    |
| Delete         | ✅    | ❌     | ❌    |
| Audit Log View | ✅    | ❌     | ❌    |

## Running Tests

```bash
./mvnw clean test
```

Test reports are stored in `target/surefire-reports` and screenshots in `target/screenshots`.

## CI/CD

This project runs Selenium UI tests in GitHub Actions using headless Chrome.
