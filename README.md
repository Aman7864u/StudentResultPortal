# 🎓 Student Result Portal

A full-featured **Spring Boot REST API** for managing student records, subject catalogue, and exam results — with automatic grade calculation, CGPA computation, and Swagger UI documentation.

---

## 📋 Features

- **Student Management** — CRUD operations, search by name/roll number/department
- **Subject Management** — Subject catalogue with credits and department mapping
- **Result Management** — Add/update/delete results with automatic grade & CGPA calculation
- **Grade System** — O / A+ / A / B+ / B / C / F with 10-point grade scale
- **Swagger UI** — Interactive API documentation at `/swagger-ui.html`
- **H2 Console** — In-browser SQL console at `/h2-console`
- **Validation** — Bean validation on all request bodies
- **Global Exception Handling** — Consistent error response format

---

## 🏗️ Project Structure

```
student-result-portal/
├── src/main/java/com/studentportal/
│   ├── StudentPortalApplication.java
│   ├── controller/        # REST endpoints
│   ├── service/           # Business logic interfaces
│   ├── service/impl/      # Business logic implementations
│   ├── repository/        # Spring Data JPA repositories
│   ├── entity/            # JPA entities (Student, Subject, Result)
│   ├── dto/               # Data Transfer Objects
│   ├── exception/         # Global exception handling
│   ├── config/            # Swagger / OpenAPI config
│   └── util/              # GradeCalculator utility
├── src/main/resources/
│   ├── application.properties
│   └── data.sql           # Sample seed data
└── pom.xml
```

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+

### Run the Application

```bash
# Clone the repo
git clone https://github.com/yourname/student-result-portal.git
cd student-result-portal

# Run with Maven
mvn spring-boot:run

# Or build JAR and run
mvn clean package
java -jar target/student-result-portal-1.0.0.jar
```

The server starts on **http://localhost:8080**

---

## 🔗 API Endpoints

### Students  `/api/students`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/students` | Create a new student |
| GET    | `/api/students` | Get all students |
| GET    | `/api/students/{id}` | Get student by ID |
| GET    | `/api/students/roll/{rollNumber}` | Get by roll number |
| GET    | `/api/students/department/{dept}` | Get by department |
| GET    | `/api/students/search?name=` | Search by name |
| PUT    | `/api/students/{id}` | Update student |
| DELETE | `/api/students/{id}` | Delete student |

### Subjects  `/api/subjects`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/subjects` | Create subject |
| GET    | `/api/subjects` | Get all subjects |
| GET    | `/api/subjects/{id}` | Get by ID |
| GET    | `/api/subjects/code/{code}` | Get by code |
| PUT    | `/api/subjects/{id}` | Update subject |
| DELETE | `/api/subjects/{id}` | Delete subject |

### Results  `/api/results`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/api/results` | Add result |
| GET    | `/api/results` | Get all results |
| GET    | `/api/results/{id}` | Get by ID |
| GET    | `/api/results/student/{studentId}` | Get student results |
| GET    | `/api/results/roll/{rollNumber}` | Get by roll number |
| GET    | `/api/results/student/{id}/cgpa` | Get CGPA |
| PUT    | `/api/results/{id}` | Update result |
| DELETE | `/api/results/{id}` | Delete result |

---

## 📊 Grade Scale

| Percentage | Grade | Grade Point |
|------------|-------|-------------|
| ≥ 90       | O     | 10.0        |
| ≥ 80       | A+    | 9.0         |
| ≥ 70       | A     | 8.0         |
| ≥ 60       | B+    | 7.0         |
| ≥ 50       | B     | 6.0         |
| ≥ 40       | C     | 5.0         |
| < 40       | F     | 0.0         |

---

## 🛠️ Tech Stack

| Technology | Version |
|-----------|---------|
| Java | 17 |
| Spring Boot | 3.2.0 |
| Spring Data JPA | 3.2.0 |
| H2 Database | Latest |
| Lombok | Latest |
| SpringDoc OpenAPI | 2.3.0 |
| Maven | 3.8+ |

---

## 🔧 Switch to MySQL

1. Uncomment MySQL dependency in `pom.xml`
2. In `application.properties`, comment H2 block and uncomment MySQL block
3. Update credentials and database name
4. Run `mvn spring-boot:run`

---

## 📖 API Documentation

Visit **http://localhost:8080/swagger-ui.html** after starting the app.

H2 Console: **http://localhost:8080/h2-console**  
JDBC URL: `jdbc:h2:mem:studentdb`

---

## 🧪 Sample Request

```json
POST /api/students
{
  "name": "Aarav Sharma",
  "rollNumber": "CS2024001",
  "email": "aarav@example.com",
  "department": "Computer Science",
  "semester": "5th",
  "gender": "Male",
  "phoneNumber": "9876543210",
  "dateOfBirth": "2002-03-14"
}
```

```json
POST /api/results
{
  "studentId": 1,
  "subjectId": 1,
  "marksObtained": 85,
  "totalMarks": 100,
  "examType": "FINAL",
  "academicYear": "2024-25"
}
```
