# JPAProject - Employee Management System

A Java EE web application that demonstrates full CRUD (Create, Read, Update, Delete) functionality for managing employee records using the Java Persistence API (JPA). The project follows a clean layered architecture separating the Controller, Service, DAO, and Entity layers. The front end is built with JSP pages styled with pure CSS.

---

## Table of Contents

1. Project Overview
2. Architecture
3. Project Structure
4. Technology Stack
5. Employee Entity Fields
6. Application Flow
7. Servlet Endpoints
8. JPA Queries Used
9. Database Configuration
10. How to Add Your Own Database Driver
11. Setup and Installation
12. Screenshots
13. Contributing

---

## 1. Project Overview

JPAProject is a session-based Employee Management System that allows users to register, log in, and then perform full CRUD operations on employee records stored in a relational database. The persistence layer uses JPA with an EntityManager, making it database-agnostic. Any JDBC-compatible relational database can be plugged in simply by updating the Maven dependency and the persistence.xml configuration file.

---

## 2. Architecture

The application follows a four-layer architecture.

Controller Layer - Jakarta Servlets handle HTTP requests and responses. Each servlet maps to a specific URL endpoint and delegates business logic to the Service layer.

Service Layer - The Service interface and its implementation (ServiceImp) contain the business rules. The service converts raw request parameters into Employee entity objects before passing them to the DAO layer.

DAO Layer - The DataBaseOperation interface defines database operations. DataBaseOperationImp implements these operations using JPA EntityManager and JPQL queries.

Entity Layer - The Employee class is a JPA-annotated entity that maps directly to the Employee table in the database.

---

## 3. Project Structure

```
JPAproject/
|
|-- src/main/java/
|   |
|   |-- com.example.Controller/
|   |   |-- LoginJPA.java           (URL: /login)
|   |   |-- RegisterJPA.java        (URL: /register)
|   |   |-- HomeJPA.java            (URL: /home)
|   |   |-- EditJPA.java            (URL: /edit)
|   |   |-- DeleteJPA.java          (URL: /delete)
|   |
|   |-- com.example.dao/
|   |   |-- DataBaseOperation.java  (DAO Interface)
|   |
|   |-- com.example.daoimplementation/
|   |   |-- DataBaseOperationImp.java (JPA Implementation)
|   |
|   |-- com.example.Entity/
|   |   |-- Employee.java           (JPA Entity)
|   |
|   |-- com.example.Service/
|   |   |-- Service.java            (Service Interface)
|   |
|   |-- com.example.ServiceImplementation/
|       |-- ServiceImp.java         (Business Logic)
|
|-- src/main/resources/
|   |-- META-INF/
|       |-- persistence.xml         (JPA Configuration)
|
|-- src/main/webapp/
|   |-- login.jsp
|   |-- register.jsp
|   |-- home.jsp
|
|-- pom.xml
```

---

## 4. Technology Stack

| Component     | Technology                            |
|---------------|---------------------------------------|
| Language      | Java 8 (JavaSE-1.8)                   |
| Persistence   | JPA (Java Persistence API)            |
| Servlet API   | Jakarta Servlet (Jakarta EE)          |
| View Layer    | JSP with embedded CSS                 |
| Build Tool    | Maven                                 |
| Architecture  | MVC with DAO and Service Layers       |
| Server        | Apache Tomcat (or any Java EE server) |

---

## 5. Employee Entity Fields

The Employee entity is mapped to the "Employee" table in the database. The following fields are defined.

| Field       | Type   | Column Name | Notes                        |
|-------------|--------|-------------|------------------------------|
| id          | int    | id          | Primary key, auto-generated  |
| name        | String | name        | Employee name used for login |
| mail_id     | String | mail_id     | Email address                |
| phonenumber | String | phonenumber | Contact number               |
| role        | String | role        | Job role or course name      |
| company     | String | company     | Company or college name      |
| password    | String | password    | Login password               |

---

## 6. Application Flow

Step 1 - Register: A new user submits the registration form. RegisterJPA servlet reads the form fields, passes them to ServiceImp.registerEmployeeData(), which creates an Employee entity and calls DataBaseOperationImp.save() to persist it using JPA.

Step 2 - Login: The user submits name and password. LoginJPA calls ServiceImp.findloginData(), which runs a JPQL query to find a matching employee record. On success, the name is stored in the HTTP session and the user is redirected to the home page.

Step 3 - Home / Dashboard: HomeJPA retrieves the session attribute, calls ServiceImp.findAll(), and passes the full employee list to home.jsp. The JSP renders all records in an HTML table with Edit and Delete buttons for each row.

Step 4 - Edit: Clicking Edit opens a modal dialog pre-populated with the employee data using JavaScript. On form submit, EditJPA reads all fields, builds an Employee entity, and calls ServiceImp.updateEmployeeData(), which uses em.merge() to update the record in the database.

Step 5 - Delete: Clicking Delete opens a confirmation modal. On confirm, DeleteJPA reads the fields, builds the Employee entity, and calls ServiceImp.deleteEmployeeData(), which uses em.merge() followed by em.remove() to delete the record.

Step 6 - Logout: A logout link on the home page redirects to /login, ending the active session.

---

## 7. Servlet Endpoints

| Servlet     | URL       | HTTP Method | Action                                |
|-------------|-----------|-------------|---------------------------------------|
| LoginJPA    | /login    | GET         | Forward to login.jsp                  |
| LoginJPA    | /login    | POST        | Authenticate user and create session  |
| RegisterJPA | /register | GET         | Forward to register.jsp               |
| RegisterJPA | /register | POST        | Save new employee to database         |
| HomeJPA     | /home     | GET         | Fetch all employees and show dashboard|
| EditJPA     | /edit     | POST        | Update employee record in database    |
| DeleteJPA   | /delete   | POST        | Delete employee record from database  |

---

## 8. JPA Queries Used

The following JPQL queries are defined and executed inside DataBaseOperationImp.java.

Login Query - Finds a single employee matching name and password:

    SELECT e FROM Employee e WHERE e.name = :name AND e.password = :password

This uses TypedQuery with named parameters and getSingleResult(). A NoResultException is caught and returns false when no match is found.

Fetch All Query - Retrieves all employee records:

    SELECT e FROM Employee e

This uses createQuery with Employee.class as the result type and returns a typed List of Employee objects.

Save - Uses em.persist(emp) to insert a new employee record into the database.

Update - Uses em.merge(emp) to update an existing employee record matched by ID.

Delete - Uses em.merge(emp) to re-attach the detached entity to the persistence context, then em.remove(emp) to delete it.

All write operations are wrapped in an EntityTransaction with tx.begin(), tx.commit(), and tx.rollback() on failure. The EntityManager is always closed in the finally block.

---

## 9. Database Configuration

JPA is configured through the persistence.xml file located at:

    src/main/resources/META-INF/persistence.xml

The persistence unit name used in the code is "employeePU". This name must match exactly in both persistence.xml and in DataBaseOperationImp.java at the line:

    emf = Persistence.createEntityManagerFactory("employeePU");

The hibernate.hbm2ddl.auto property is set to "update", which means JPA will automatically create or update the Employee table on startup based on the entity class definition. No manual SQL script is required to create the table.

---

## 10. How to Add Your Own Database Driver

This project is fully database-agnostic. You can connect it to any relational database by following two steps.

Step 1 - Add the driver dependency to pom.xml

Open pom.xml and add the dependency block for your chosen database inside the dependencies section.

MySQL:

    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.33</version>
    </dependency>

PostgreSQL:

    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <version>42.6.0</version>
    </dependency>

Oracle:

    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc8</artifactId>
        <version>21.9.0.0</version>
    </dependency>

H2 (In-Memory, useful for quick testing):

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.2.224</version>
    </dependency>

Microsoft SQL Server:

    <dependency>
        <groupId>com.microsoft.sqlserver</groupId>
        <artifactId>mssql-jdbc</artifactId>
        <version>12.4.2.jre8</version>
    </dependency>


Step 2 - Update persistence.xml with your database connection details

    <persistence xmlns="https://jakarta.ee/xml/ns/persistence" version="3.0">
        <persistence-unit name="employeePU">
            <properties>
                <!-- Change driver class to match your database -->
                <property name="jakarta.persistence.jdbc.driver"
                          value="com.mysql.cj.jdbc.Driver"/>

                <!-- Change URL to your database host, port, and schema name -->
                <property name="jakarta.persistence.jdbc.url"
                          value="jdbc:mysql://localhost:3306/your_database_name"/>

                <!-- Your database username -->
                <property name="jakarta.persistence.jdbc.user"
                          value="your_username"/>

                <!-- Your database password -->
                <property name="jakarta.persistence.jdbc.password"
                          value="your_password"/>

                <!-- Auto-creates or updates the Employee table on startup -->
                <property name="hibernate.hbm2ddl.auto" value="update"/>

                <!-- Optional: prints SQL statements to console for debugging -->
                <property name="hibernate.show_sql" value="true"/>
            </properties>
        </persistence-unit>
    </persistence>

Driver class and URL reference for supported databases:

| Database    | Driver Class                                 | JDBC URL Format                                     |
|-------------|----------------------------------------------|-----------------------------------------------------|
| MySQL       | com.mysql.cj.jdbc.Driver                     | jdbc:mysql://localhost:3306/dbname                  |
| PostgreSQL  | org.postgresql.Driver                        | jdbc:postgresql://localhost:5432/dbname             |
| Oracle      | oracle.jdbc.OracleDriver                     | jdbc:oracle:thin:@localhost:1521:xe                 |
| H2 Memory   | org.h2.Driver                                | jdbc:h2:mem:testdb                                  |
| SQL Server  | com.microsoft.sqlserver.jdbc.SQLServerDriver | jdbc:sqlserver://localhost:1433;databaseName=dbname |

No changes to any Java source files are needed when switching databases. Only pom.xml and persistence.xml need to be updated.

---

## 11. Setup and Installation

Prerequisites:

- Java 8 or higher
- Apache Maven 3.x
- Apache Tomcat 10.x (or any Jakarta EE 10 compatible server)
- Any relational database of your choice
- Eclipse IDE or IntelliJ IDEA (recommended)

Steps:

1. Clone the repository

       git clone https://github.com/your-username/JPAproject.git
       cd JPAproject

2. Add your database driver to pom.xml (refer to Section 10)

3. Update persistence.xml with your database credentials (refer to Section 10)

4. Build the project using Maven

       mvn clean install

5. Deploy the generated .war file from the target/ directory to your Tomcat webapps folder

6. Start Tomcat and open your browser at

       http://localhost:8080/JPAproject/login

7. Register a new account on the Register page, then log in to begin managing employee records

---

## 12. Screenshots

Add screenshots of the following pages to help contributors understand the user interface:

- Login page (login.jsp)
- Registration page (register.jsp)
- Home dashboard with employee records table (home.jsp)
- Edit modal dialog
- Delete confirmation modal

---

## 13. Contributing

Contributions are welcome from anyone. To contribute:

1. Fork the repository on GitHub
2. Create a new branch for your feature or bug fix
3. Make your changes and write clear, descriptive commit messages
4. Push your branch to your fork
5. Open a Pull Request against the main branch

If you are testing with a different database, follow Section 10 to configure your own driver. No changes to the application business logic or entity classes are required when switching databases.

---

## License

This project is open source and available under the MIT License.

---

## Author

Your Name
GitHub: https://github.com/Hemapadmavathi-sanka(https://github.com/Hemapadmavathi-sanka)
