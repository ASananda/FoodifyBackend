# 🍽️ Foodify Backend

The **Foodify Backend** is built using **Java** and **Spring Boot** to power a seamless food ordering experience for users and restaurant partners. It handles user authentication, restaurant and menu management, order processing, and integrates **Razorpay for dummy payment processing**.

---

## 🧾 Overview

This backend supports:

- 🔐 Secure user registration and login (JWT)
- 🏪 Restaurant, menu, and order management
- 💳 Razorpay dummy payment integration
- 📦 RESTful APIs consumed by the React frontend
- 🧩 Scalable modular architecture using Spring Boot

---

## ⚙️ Tech Stack

- **Backend Language**: Java 17+
- **Framework**: Spring Boot
- **Database**: MySQL / PostgreSQL
- **Security**: Spring Security + JWT
- **ORM**: Spring Data JPA
- **Payment Integration**: Razorpay (dummy/test mode)
- **API Documentation**: Swagger (optional)
- **Frontend (connected)**: React.js (separate repo)

---

## 🔧 Environment Setup

### 📦 Prerequisites

- Java 17+
- Maven
- MySQL/PostgreSQL
- Postman (for testing APIs)

### 🔑 `application.properties` (Example)

```properties
# Server
server.port=8080

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/foodify
spring.datasource.username=root
spring.datasource.password=your_password

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT
app.jwt.secret=your_jwt_secret
app.jwt.expirationMs=86400000

# Razorpay (Test Mode)
razorpay.key=your_test_key
razorpay.secret=your_test_secret
🚀 How to Run the Project Locally
1. Clone the Repo
git clone https://github.com/ASananda/FoodifyBackend.git
cd FoodifyBackend
Configure Database
Create a database named foodify in your MySQL/PostgreSQL instance.
3. Update application.properties with your DB credentials and Razorpay test keys.
4. Build and Run
🔌 API Endpoints (Sample)
Auth
Method	Endpoint	Description
POST	/api/auth/signup	Register a new user
POST	/api/auth/login	User login & JWT

Restaurant
Method	Endpoint	Description
GET	/api/restaurants	List all restaurants
POST	/api/restaurants	Add a new restaurant

Orders
Method	Endpoint	Description
POST	/api/orders	Place a new order
GET	/api/orders/user	View user order history

Payments (Razorpay)
Method	Endpoint	Description
POST	/api/payment/order	Create a Razorpay payment order
POST	/api/payment/verify	Verify Razorpay payment signature

💳 Razorpay Test Flow
Frontend hits /api/payment/order to create a payment order.

Razorpay checkout is launched on frontend using test keys.

On success, Razorpay sends a payment ID + signature.

Backend verifies the payment via /api/payment/verify.

Order is marked as paid (in test mode).

No real money is involved in Razorpay test mode.

🔐 Security Features
Spring Security for route protection

JWT-based stateless authentication

Role-based access control (User/Admin)

Input validation and global exception handling

🧪 Testing
Use Postman to test all endpoints.
