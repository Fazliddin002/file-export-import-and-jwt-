# E-Commerce System using Spring Boot

This project is a simple **E-Commerce System** developed using **Spring Boot**, designed for managing products, categories, orders, and user baskets in an online store. The system includes an **admin panel** for managing products and categories, along with a **user interface** for browsing products and placing orders.

## Features

- **Admin Panel**: Manage categories, products, and orders.
- **Product Management**: Add, edit, and delete products in the system.
- **Category Management**: Add, edit, and delete product categories.
- **User Basket**: Users can add products to their shopping cart.
- **Order Management**: Users can place orders for products in their basket.
- **File Handling**: Upload and display images for products.
- **Authentication**: Login and logout functionalities with user session management.

## Technologies Used

- **Backend**: Spring Boot, Spring Security, Spring Session
- **Frontend**: Thymeleaf
- **Database**: PostgreSQL
- **File Handling**: MultipartFile for image uploads
- **UUID** for unique identifiers
- **Authentication**: JWT for secure login
- **Session**: For managing user sessions

## Project Structure

The project consists of the following main components:

- **Controllers**:
  - `AdminController`: Manages admin functionalities like product and category management.
  - `AttachmentController`: Handles file uploads and image management.
  - `BasketController`: Manages user shopping cart functionality.
  - `CategoryController`: Manages product categories.
  - `HomeController`: Handles the homepage and product listing.
  - `LoginController`: Manages user login and authentication.
  - `OrderController`: Manages user orders.

- **Models**: Includes entities for products, categories, orders, and user information.
- **Views**: Uses Thymeleaf templates to display the frontend pages.

## Installation and Setup

Follow the steps below to set up the project locally:

### Prerequisites

- Java 11 or higher
- Maven (for building the project)
- PostgreSQL (or any other supported database)
- IDE (IntelliJ IDEA, Eclipse, etc.)

### Steps to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/e-commerce-system.git
