# Web Scraper Application

## Overview

This application was developed using Java Spring Boot and automates the process of extracting product information from the website https://www.web-scraping.dev.

The application logs into the website, scrapes products from the **Consumables** category, stores the data in a database, and displays it through a web interface.

Additionally, the application can process PDF invoices, extract product information, and generate a CSV file containing the extracted data.

---

## Features

### Product Scraping

* Automated login using Playwright
* Product extraction from all available pages
* Automatic pagination handling
* Extraction of:

    * Product image URL
    * Product name
    * Product price
    * Product description
* Duplicate prevention using a unique product name constraint

### Scheduled Execution

* Implemented using Spring Scheduler
* Runs automatically every hour between **12:00 PM and 6:00 PM**

### Product Management

* Display all products in a web interface
* Edit existing products
* Delete products
* Search products by name
* Sort products by price

### Currency Conversion

* Retrieves the current EUR → RON exchange rate from an external API
* Stores the exchange rate in the database
* Calculates and stores product prices in RON

### PDF Invoice Processing

* Upload PDF invoices
* Extract:

    * Product code
    * Product name
    * Unit price
    * Currency
    * Quantity
* Generate and download a CSV file containing the extracted information

### Authentication & Security

* User authentication using Spring Security
* Restricted access to protected pages
* Login page for application access

---

## Technologies Used

### Backend

* Java 17
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Security

### Frontend

* Thymeleaf
* Bootstrap 5

### Database

* PostgreSQL

### Web Scraping

* Playwright

### PDF Processing

* Apache PDFBox

### CSV Export

* OpenCSV

### External APIs

* Frankfurter Currency Exchange API

---

## Application Architecture

The application follows a layered architecture:

### Controller Layer

Handles incoming HTTP requests and returns views or responses.

### Service Layer

Contains the business logic:

* Product scraping
* PDF processing
* Currency conversion
* Data management

### Repository Layer

Responsible for database access using Spring Data JPA.

### Entity Layer

Defines the application's domain models and database mappings.

---

## Project Workflow

1. User logs into the application.
2. Scheduled task starts the scraping process.
3. Playwright launches Chromium and authenticates on the target website.
4. Products are extracted from all available pages.
5. Product information is saved to the database.
6. Exchange rates are retrieved and product prices are converted to RON.
7. Products can be viewed, edited, deleted, filtered, and sorted through the web interface.
8. Users can upload PDF invoices and export extracted data as CSV files.

---

## Database Configuration

Configure the database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:1234/web_scraper
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Running the Application

### Clone the repository

```bash
git clone <repository-url>
```

### Install dependencies

```bash
mvn clean install
```

### Run the application

```bash
mvn spring-boot:run
```

or start the application directly from your IDE by running:

```text
WebScraperApplication
```

---

## Default Credentials

```text
Username: admin
Password: admin
```

---

## Future Improvements

* Store users in the database
* Implement role-based authorization (ADMIN / USER)
* Add DTOs for request/response handling
* Improve PDF parsing flexibility
* Add unit and integration tests
* Implement logging and monitoring
* Export data to Excel format

---

## Learning Objectives

This project demonstrates practical knowledge of:

* Java
* Spring Boot
* Spring MVC
* Spring Security
* Spring Scheduler
* Spring Data JPA
* PostgreSQL
* Playwright
* Web Scraping
* REST API Integration
* PDF Processing
* CSV Generation

---

## Author

Developed as a learning project to practice backend development, web automation, database management, and document processing using the Spring Boot ecosystem.
