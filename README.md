# 📚 Spring Boot Book Store

This is a RESTful Book Store API built with **Spring Boot**, featuring CRUD operations for managing books and authors. The application uses Spring Data JPA with an H2 in-memory database and includes Swagger UI for easy API exploration.

## 🚀 Features

- Create, read, update, and delete books
- Associate books with authors
- Search books by author name
- API validation using DTOs
- In-memory H2 database for fast setup
- Swagger UI for API documentation and testing

## 🛠 Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Jakarta Bean Validation
- Swagger (springdoc-openapi)

### Prerequisites

- Java 17+
- Maven or mvnd

## Swagger UI
Once the app is running, access the Swagger UI at:
http://localhost:8080/swagger-ui/index.html

## 📂 Project Structure

    src
    └── main
        ├── java
        │   └── com.book
        │       ├── Controller
        │       ├── DTO
        │       ├── Model
        │       ├── Repository
        │       └── Service
        └── resources
## 📬 API Endpoints
POST /newBook - Add a new book

GET /books - List all books

GET /books/{id} - Get book by ID

PUT /books/{id} - Update book

DELETE /books/{id} - Delete book

GET /books/search?name= - Search books by author name

And more (see Swagger UI)

## ✅ To-Do
Add unit/integration tests

Add user authentication (future enhancement)

Connect to PostgreSQL or MySQL for production-ready DB

## 📎 License
This project is open-source and available under the MIT License.

## 👤 Author
Julian Blanco

[GitHub Profile
](https://github.com/jblanco081)

[LinkedIn
](https://www.linkedin.com/in/julian-blanco-27b708275/)
