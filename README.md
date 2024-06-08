## Introduction

This project aims to automate various processes related to saucedemo.com website. The project is implemented using Java, Kotlin, Selenium, and TestNG.

## Project Structure

The repository is organized as follows:

```
automation-swag-luxoft/
├── src/
│   ├── main/
│   │   └── kotlin/
│   │       ├── allureListeners/
│   │       ├── core/
│   │       ├── pages/
│   │       ├── utils/
│   │       └── resources/
│   └── test/
│       ├── kotlin/
│       │   └── test/
│       │       └── BaseTest
│       └── resources/
│           ├── allure.properties
│           ├── config.properties
│           └── testng.xml
├── target/
├── .gitignore
├── pom.xml
└── README.md

```

## Prerequisites

Ensure you have the following installed before proceeding with the installation:

- Java Development Kit (JDK) 8 or higher
- Maven
- ChromeDriver (for Selenium)
- Browser (Chrome is recommended)

## Installation

Follow these steps to set up the project locally:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/ShpyliukOleksii/automation-swag-luxoft.git
    cd automation-swag-luxoft
    ```

2. **Install the dependencies**:
    ```bash
    mvn install
    ```

3. **Configure ChromeDriver path**:
    Ensure the path to ChromeDriver is set in your system's PATH variable or update the configuration files accordingly.

## Usage

To run the automation scripts, use the included TestNG configurations. 

Ensure to configure the necessary parameters in the `config` directory before running the scripts.

