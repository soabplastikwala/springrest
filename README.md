Student Management System
This is a Spring Boot-based REST API for managing student records. It provides endpoints to create, read, update, and delete student information.

Features
Create Student: Add a new student to the database.

Get Student: Retrieve student details by ID.

Get All Students: Retrieve a list of all students.

Update Student: Update an existing student's details.

Delete Student: Delete a student by ID.

Technologies Used
Spring Boot: Backend framework for building RESTful APIs.

Spring Data JPA: For database operations.

Hibernate: ORM (Object-Relational Mapping) tool.

H2 Database: In-memory database for development (or replace with your database).

Maven: Build tool for managing dependencies.

SLF4J: Logging framework.

Prerequisites
Before running the project, ensure you have the following installed:

Java Development Kit (JDK) 17 or higher

Maven 3.x

Postman (or any API testing tool)

Setup Instructions
1. Clone the Repository
bash
Copy
git clone https://github.com/your-username/your-repo-name.git
cd your-repo-name
2. Build the Project
Use Maven to build the project:

bash
Copy
mvn clean install
3. Run the Application
Start the Spring Boot application:

bash
Copy
mvn spring-boot:run
The application will start on http://localhost:8080.

API Endpoints
1. Get All Students
URL: /students

Method: GET

Response:

json
Copy
[
  {
    "id": 1,
    "name": "John Doe",
    "subject": "Math",
    "grade": "A",
    "city": "New York",
    "version": 0
  }
]
2. Get Student by ID
URL: /students/{id}

Method: GET

Response:

json
Copy
{
  "id": 1,
  "name": "John Doe",
  "subject": "Math",
  "grade": "A",
  "city": "New York",
  "version": 0
}
3. Create a Student
URL: /students/create

Method: POST

Request Body:

json
Copy
{
  "name": "Jane Doe",
  "subject": "Science",
  "grade": "B",
  "city": "Los Angeles"
}
Response:

json
Copy
{
  "id": 2,
  "name": "Jane Doe",
  "subject": "Science",
  "grade": "B",
  "city": "Los Angeles",
  "version": 0
}
4. Update a Student
URL: /students/update/{id}

Method: PUT

Request Body:

json
Copy
{
  "name": "Jane Doe",
  "subject": "Physics",
  "grade": "A+",
  "city": "Chicago"
}
Response:

json
Copy
{
  "id": 2,
  "name": "Jane Doe",
  "subject": "Physics",
  "grade": "A+",
  "city": "Chicago",
  "version": 1
}
5. Delete a Student
URL: /students/delete/{id}

Method: DELETE

Response:

json
Copy
"Student with ID 2 deleted successfully."
Database Configuration
The application uses an H2 in-memory database by default. You can access the H2 console at:

URL: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (leave blank)

To use a different database (e.g., MySQL, PostgreSQL), update the application.properties file:

properties
Copy
spring.datasource.url=jdbc:mysql://localhost:3306/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
Logging
The application uses SLF4J for logging. Logs are printed to the console. You can configure logging levels in the application.properties file:

properties
Copy
logging.level.org.springframework=DEBUG
logging.level.com.springrest.springrest=DEBUG
Contributing
Contributions are welcome! If you'd like to contribute, please follow these steps:

Fork the repository.

Create a new branch (git checkout -b feature/your-feature).

Commit your changes (git commit -m 'Add some feature').

Push to the branch (git push origin feature/your-feature).

Open a pull request.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Contact
For any questions or feedback, please contact:

Your Name

Email: your-email@example.com

GitHub: your-username

Acknowledgments
Spring Boot Documentation

H2 Database

SLF4J Documentation

