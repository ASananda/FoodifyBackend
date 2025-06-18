# 🍽️ Foodify Backend

The **Foodify Backend** is a RESTful API built using **Node.js** and **Express.js**, serving as the backbone for the Foodify food ordering platform. It handles user authentication, restaurant and menu management, order processing, and **dummy payment integration using Razorpay API**.

---

## 🧾 Overview

This backend application supports:

- 🔐 User authentication (JWT-based)
- 🏪 Restaurant and menu management
- 🛒 Order creation and tracking
- 💳 Razorpay payment integration (dummy)
- 🔧 Scalable and modular MVC architecture

---

## ⚙️ Tech Stack

- **Runtime**: Node.js
- **Framework**: Express.js
- **Database**: MongoDB (via Mongoose)
- **Authentication**: JWT, bcrypt
- **Environment Configuration**: dotenv
- **Payment Gateway**: Razorpay API (dummy)
- **Request Validation**: express-validator / Joi
- **Logging**: morgan
- **Testing**: Postman / Jest (optional)

---

## 🔧 Environment Variables

Create a `.env` file in the root with:

```env
PORT=5000
DB_URI=mongodb://localhost:27017/foodify
JWT_SECRET=your_jwt_secret
RAZORPAY_KEY_ID=your_test_key_id
RAZORPAY_KEY_SECRET=your_test_key_secret

1.Clone the Repo
git clone https://github.com/ASananda/FoodifyBackend.git
cd FoodifyBackend
2. Install Dependencies
npm install
3.Start MongoDB
Make sure MongoDB is running:
mongod
4. Start the Backend Server
npm run dev

🔌 API Endpoints (Quick Overview)
Auth Routes
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Login and get JWT token

Restaurant Routes
Method	Endpoint	Description
GET	/api/restaurants	List all restaurants
POST	/api/restaurants	Add a new restaurant (admin)
GET	/api/restaurants/:id	Get restaurant with menu

Order Routes
Method	Endpoint	Description
POST	/api/orders	Create a new order
GET	/api/orders/:id	Get order status
GET	/api/orders/user	Get orders for current user

Payment Routes
Method	Endpoint	Description
POST	/api/payment/order	Create Razorpay payment order
POST	/api/payment/verify	Verify payment signature (dummy)

💳 Razorpay Payment Flow (Dummy)
Frontend calls /api/payment/order to create a Razorpay order.

Razorpay opens checkout using test keys.

On success, frontend sends response to /api/payment/verify.

Backend verifies signature using Razorpay secret key (dummy for now).

Order marked as Paid.

🔐 Security
JWT authentication middleware

bcrypt for password hashing

Input validation via express-validator

Centralized error handler

