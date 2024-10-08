# CuentasClaras Spring Backend
>(Proyecto presentado y aprobado por la catedra de Taller de Tecnologias de Produccion de Software Opcion Java)

Cuentas Claras is a financial management application developed using Spring Boot. It helps users manage personal or shared expenses by allowing them to track, split, and settle group costs efficiently. This project leverages a RESTful backend API, providing endpoints for user registration, group management, and expense tracking.
## Features

 * User Authentication: Secure user registration and login system.
 * Group Management: Create and manage groups to track shared expenses between multiple users.
 * Expense Tracking: Add and categorize expenses for individuals or groups, with real-time tracking of balances.
 * Expense Splitting: Automatically calculate and split expenses among group members.
 * Debt Settlement: Visualize who owes whom and track payment settlements within groups.
 * RESTful API: Provides a robust API for interaction with the client-side application, with endpoints for managing users, groups, and expenses.
 * Database Integration: Uses MySQL to persist user, group, and expense data.

## Prerequisites

* Java 17
* Maven 3.x.x
* MySQL (or another SQL-based database)

## Getting Started

  1- Clone the repository:
```
git clone https://github.com/Manaaa22/CuentasClarasSpring.git
cd CuentasClarasSpring
```
2- Set up the database:

Create a MySQL database for the application (or use your preferred SQL-based database) and configure the database connection in src/main/resources/application.properties.
```
spring.datasource.url=jdbc:mysql://localhost:3306/cuentasclaras
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
```
3- Build and run the application:
```
mvn clean install
mvn spring-boot:run
```
or right click the CuentasClarasSpringApplication.java file at src/main/java/ttps/java and select Run As > Java Application

4- Access the API:
The API will be running on http://localhost:8080. You can interact with the endpoints using tools like Postman or Swagger at http://localhost:8080/jwt/swagger-ui/index.html

## Technologies Used

* Spring Boot: For the backend framework.
* Spring Security: For user authentication and authorization.
* MySQL: For database management.
* Hibernate: For ORM (Object Relational Mapping).
* Maven: For project management and build.
* Swagger UI: For easier API testing
