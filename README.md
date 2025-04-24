# 📁 File and Resource Management System

This project is a Spring Boot based web application that provides APIs for managing files, video links, and program links, along with user authentication and profile image upload functionality.

## 🚀 Features

- ✅ **User Registration & Login** with JWT Authentication
- 🖼️ **Upload and serve user profile images**
- 📥 **Upload, 📄 list, 📤 download**, and 🗑️ **delete files**
- 🔗 **Manage video links** (e.g. YouTube links)
- 🔗 **Manage program or software links**
- 🔐 **Secured endpoints** using Spring Security
- 📄 **API documentation** using OpenAPI/Swagger UI

## 🛠 Tech Stack

- Java 17
- Spring Boot 3.4.2
- Spring Security
- Spring Data JPA
- PostgreSQL
- Thymeleaf
- JWT (JSON Web Tokens)
- iTextPDF (for PDF support)
- Lombok
- Springdoc OpenAPI (Swagger UI)
- Maven

---

## 🔐 Auth API

- `POST /api/auth/register` – Register a user with phone, password, and profile image
- `POST /api/auth/login` – Login with phone and password, returns JWT token

---

## 👤 User Profile Image

- `POST /file/user/{id}` – Get the user's profile image (read from `User` object)

---

## 📁 File Management (`FileController`)

- `POST /api/files/upload` – Upload a file
- `GET /api/files` – List all uploaded files
- `GET /api/files/download/{filename}` – Download a file
- `DELETE /api/files/delete/{filename}` – Delete a file

---

## 🔗 Program Link Management (`ProgramLinkController`)

- `GET /api/program_link` – List all program links
- `GET /api/program_link/{id}` – Get a program link by ID
- `POST /api/program_link` – Create a new program link
- `DELETE /api/program_link/{id}` – Delete a program link

---

## 🎥 Video Link Management (`VideoLinkController`)

- `GET /api/video_link` – List all video links
- `GET /api/video_link/{id}` – Get a video link by ID
- `POST /api/video_link` – Create a new video link
- `DELETE /api/video_link/{id}` – Delete a video link

---

## 🧪 Testing

The following libraries are available for testing:

- Spring Boot Starter Test
- Spring Security Test

```bash
./mvnw test
