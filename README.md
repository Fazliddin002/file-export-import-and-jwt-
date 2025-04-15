This project is a Spring Boot based web application that provides APIs for managing files, video links, and program links, along with user authentication and profile image upload functionality.

🚀 Features
✅ User Registration & Login with JWT Authentication

🖼️ Upload and serve user profile images

📥 Upload, 📄 list, 📤 download, and 🗑️ delete files

🔗 Manage video links (e.g. YouTube links)

🔗 Manage program or software links

🔐 Secured endpoints using Spring Security

📄 API documentation using OpenAPI/Swagger UI

🛠️ Tech Stack
Java 17

Spring Boot 3.4.2

Spring Data JPA

Spring Security

JWT (JSON Web Tokens)

PostgreSQL

Lombok

Thymeleaf

iTextPDF (for PDF support)

Springdoc OpenAPI

🔐 Authentication Endpoints
POST /api/auth/login — Login with phone and password

POST /api/auth/register — Register with phone, password, and profile image

📁 File Endpoints
POST /api/files/upload — Upload a file

GET /api/files — List all uploaded files

GET /api/files/download/{fileName} — Download a file

DELETE /api/files/delete/{fileName} — Delete a file

🎥 Video Link Endpoints
GET /api/video_link — Get all video links

GET /api/video_link/{id} — Get a video link by ID

POST /api/video_link — Create a new video link

DELETE /api/video_link/{id} — Delete a video link

💻 Program Link Endpoints
GET /api/program_link — Get all program links

GET /api/program_link/{id} — Get a program link by ID

POST /api/program_link — Create a new program link

DELETE /api/program_link/{id} — Delete a program link
