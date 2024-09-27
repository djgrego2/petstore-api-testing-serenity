# API Testing Project for Petstore

## Description
This project implements a set of automated tests for the Petstore API using Rest Assured with Serenity BDD and Cucumber. It is designed to demonstrate best practices in RESTful API testing and follows industry-recommended design patterns.

## Technologies Used
- Kotlin
- Gradle
- Rest Assured
- Serenity BDD
- Cucumber
- JUnit 5

## Prerequisites
- JDK 17 or higher
- Gradle 7.x or higher

## Project Setup
1. Clone the repository:
   ```
   git clone https://github.com/your-username/petstore-api-testing-serenity.git
   ```
2. Navigate to the project directory:
   ```
   cd petstore-api-testing-serenity
   ```
3. Ensure you are using the correct Java version:
   ```
   java -version
   ```

## Running Tests
To run all tests, use the following command in the project root:
```
./gradlew clean test aggregate
```

This command will clean any previous build, run all tests, and generate the Serenity report.

## Project Structure
```
src
├── main
│   └── kotlin
│       └── com
│           └── petstore
│               ├── api
│               ├── models
│               └── utils
└── test
    ├── kotlin
    │   └── com
    │       └── petstore
    │           ├── runners
    │           └── stepdefinitions
    └── resources
        └── features
```

- `api`: Contains classes that interact directly with the API.
- `models`: Defines the data structures used in the project.
- `utils`: Utilities and helpers for the project.
- `runners`: Contains the configuration to run the tests.
- `stepdefinitions`: Implementations of Cucumber steps.
- `features`: Cucumber .feature files that define the test scenarios.

## Reports
After running the tests, you can find the detailed Serenity report at:
```
target/site/serenity/index.html
```

## Contributing
If you wish to contribute to this project, please:
1. Fork the repository
2. Create a new branch for your feature
3. Make your changes and commit them
4. Submit a pull request

## Support
If you encounter any issues or have any questions, please open an issue in the GitHub repository.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
