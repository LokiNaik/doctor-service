# 🏥 Doctor Service — HMS Microservice

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)
![Build](https://img.shields.io/github/actions/workflow/status/LokiNaik/doctor-service/ci.yml?branch=main&style=flat-square)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

A Spring Boot microservice responsible for managing **Doctor profiles** and **Availability Slots** as part of the **Hospital Management System (HMS)**. Exposes REST APIs consumed internally by the **Appointment Service** via OpenFeign.

---

## 📁 Project Structure

```
doctor-service/
├── src/
│   ├── main/java/com/hms/doctor_service/
│   │   ├── controller/
│   │   │   └── DoctorController.java
│   │   ├── service/
│   │   │   └── DoctorService.java
│   │   ├── repo/
│   │   │   └── DoctorRepository.java
│   │   ├── entity/
│   │   │   └── Doctor.java
│   │   ├── dto/
│   │   │   ├── DoctorDTO.java
│   │   │   ├── DoctorResponseDTO.java
│   │   │   └── AvailabilityResponseDTO.java
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── ResourceNotFoundException.java
│   │   └── DoctorServiceApplication.java
│   └── main/resources/
│       └── application.yml
└── pom.xml
```

---

## 🚀 Features

- Register and manage Doctor profiles (CRUD)
- Configure and query availability slots by doctor and date
- REST APIs consumed by Appointment Service via **Feign Client**
- Global exception handling with meaningful error responses
- Input validation using Bean Validation (`@Valid`)

---

## 🛠️ Tech Stack

| Technology          | Usage                          |
|---------------------|--------------------------------|
| Java 17             | Core language                  |
| Spring Boot 3.x     | Application framework          |
| Spring Data JPA     | ORM / Database layer           |
| MySQL 8             | Relational database            |
| OpenFeign           | Inter-service communication    |
| Eureka Client       | Service discovery              |
| Lombok              | Boilerplate reduction          |
| JUnit 5 + Mockito   | Unit & integration testing     |
| JaCoCo              | Code coverage                  |
| SonarCloud          | Static analysis & code quality |
| Maven               | Build tool                     |

---

## ⚙️ Setup & Configuration

**Prerequisites:**
- Java 17+
- MySQL 8 running locally
- Eureka Server running on port `8761`

**1. Clone the repo:**
```bash
git clone https://github.com/LokiNaik/doctor-service.git
cd doctor-service
```

**2. Create the database:**
```sql
CREATE DATABASE hms_doctor;
```

**3. Update `src/main/resources/application.yml`:**
```yaml
server:
  port: 8081

spring:
  application:
    name: doctor-service
  datasource:
    url: jdbc:mysql://localhost:3306/hms_doctor
    username: root
    password: yourpassword
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

**4. Build & Run:**
```bash
./mvnw clean install
./mvnw spring-boot:run
```

---

## 📡 API Endpoints

| Method   | Endpoint                                            | Description                      |
|----------|-----------------------------------------------------|----------------------------------|
| `POST`   | `/api/v1/doctors`                                   | Register a new doctor            |
| `GET`    | `/api/v1/doctors`                                   | Get all doctors                  |
| `GET`    | `/api/v1/doctors/{id}`                              | Get doctor by ID                 |
| `PUT`    | `/api/v1/doctors/{id}`                              | Update doctor details            |
| `DELETE` | `/api/v1/doctors/{id}`                              | Delete a doctor                  |
| `GET`    | `/api/v1/doctors/{id}/availability?date=YYYY-MM-DD` | Get available slots by date      |

### Example Requests

**Get Doctor by ID:**
```bash
curl -X GET http://localhost:8081/api/v1/doctors/DOC001
```

**Get Availability:**
```bash
curl -X GET "http://localhost:8081/api/v1/doctors/DOC001/availability?date=2024-03-15"
```

**Sample Availability Response:**
```json
{
  "doctorId": "DOC001",
  "date": "2024-03-15",
  "availableSlots": ["09:00", "10:00", "11:00", "14:00"]
}
```

---

## 🧪 Running Tests

```bash
# Run all tests
./mvnw test

# Run with JaCoCo coverage report
./mvnw verify
# Report output: target/site/jacoco/index.html
```

---

## 🔗 Part of HMS Microservices Ecosystem

| Service               | Port  | Description                      |
|-----------------------|-------|----------------------------------|
| `eureka-server`       | 8761  | Service discovery                |
| `api-gateway`         | 8080  | Entry point for all requests     |
| **`doctor-service`**  | 8081  | Doctor & availability management |
| `patient-service`     | 8082  | Patient management               |
| `appointment-service` | 8083  | Appointment booking              |

---

## 👤 Author

**Loki Naik** — [@LokiNaik](https://github.com/LokiNaik)
