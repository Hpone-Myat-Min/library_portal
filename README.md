# Library Portal Microservice

This Libary Portal is a Java SpringBoot-based microservice that allows to manage book borrowing and returning processes. It works together with two other microservice - Student Portal and Finance Portal - using RESTful APIs for seamless communication and data flow.

## Features
- View available books in stock
- Borrow and return books
- Issue overdue invoices via Finance Portal API
- Admin functions (add/ view books, students and loans)
- Dockerized deployment using Docker Compose

## Microservice Architecture
This microservice communicates with:
- Finance Portal Service: Issues invoices for overdue books and accept payments
- Student Portal Service: Manages student and course related processes

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- Docker & Docker Compose
- RESTful APIs
- MariaDB

## Getting Started
1. Clone the repository.
   ``git clone https://github.com/Hpone-Myat-Min/library_portal.git
   cd student-portal``
2. Create .env File
   Create a .env file at the project root:
```angular2html
DB_Name=
DB_APPLICATION_USER=
DB_APPLICATION_PASSWORD=
DB_ROOT_PASSWORD=
```
``Adjust these variable values to match your environment.``

3. Build the Project
   ``./mvnw clean install``

4. Run with Docker Compose
   ``docker-compose up --build``





