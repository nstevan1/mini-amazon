# 🛒 Mini Amazon

A full-stack demo e-commerce application built with **Java (Spring Boot)** and **React**.
This project simulates a simplified version of Amazon's product catalog system — where users can view, add, and manage products.

---

## 🚀 Tech Stack

**Backend**

* Java 17
* Spring Boot 3 (REST API, JPA, H2 Database, Validation)
* Gradle Build System
* Spring Security (JWT-ready configuration)

**Frontend**

* React (with functional components and hooks)
* Axios for API communication
* HTML, CSS, JavaScript (ES6)

**Database**

* H2 (in-memory for development)
* PostgreSQL-ready configuration (optional)

---

## ⚙️ Project Structure

```
mini-amazon/
├── backend-java/          # Spring Boot backend
│   ├── src/
│   │   ├── main/java/com/example/miniamazon/
│   │   │   ├── controller/   # REST Controllers
│   │   │   ├── model/        # Entities
│   │   │   ├── repository/   # JPA Repositories
│   │   │   ├── service/      # Business Logic
│   │   │   └── config/       # Security Configuration
│   │   └── resources/
│   │       └── application.properties
│   └── build.gradle
│
├── frontend/              # React frontend
│   ├── public/
│   ├── src/
│   │   ├── App.jsx
│   │   └── index.js
│   └── package.json
│
├── .gitignore
├── build.gradle
└── settings.gradle
```

---

## 🧠 Features

✅ Add and list products via REST API
✅ React frontend with live updates
✅ JWT-ready authentication setup
✅ Simple H2 in-memory persistence
✅ Clean Gradle build system
✅ Fully containerizable with Docker (optional)

---

## 🧩 API Endpoints

### Catalog

| Method   | Endpoint        | Description            |
| -------- | --------------- | ---------------------- |
| `GET`    | `/catalog`      | List all products      |
| `POST`   | `/catalog`      | Add a new product      |
| `DELETE` | `/catalog/{id}` | Delete a product by ID |

### Example Request

```bash
curl -X POST http://localhost:8080/catalog \
  -H "Content-Type: application/json" \
  -d '{"title":"Demo Product","description":"A test item","price":12.99,"inventory":5}'
```

---

## 🖥️ Running the Project

### 1️⃣ Backend (Spring Boot)

```bash
cd backend-java
./gradlew bootRun
```

The API runs at: **[http://localhost:8080](http://localhost:8080)**

### 2️⃣ Frontend (React)

```bash
cd frontend
npm install
npm start
```

The app runs at: **[http://localhost:3000](http://localhost:3000)**

---

## 🧪 Development Notes

* The app uses **H2 in-memory DB** by default.
  Access the H2 console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

  ```
  JDBC URL: jdbc:h2:mem:miniamazon
  User: sa
  Password:
  ```

* You can switch to PostgreSQL by updating:

  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/miniamazon
  spring.datasource.username=postgres
  spring.datasource.password=yourpassword
  ```

---

## 🖼️ Preview

### Product Catalog

![Mini Amazon UI](docs/preview.png)

---

## 🧱 Future Improvements

* Add user authentication (JWT + roles)
* Add shopping cart and order endpoints
* Deploy backend (Render / AWS Elastic Beanstalk)
* Deploy frontend (Vercel / Netlify)
* Integrate CI/CD with GitHub Actions

---

## 👩‍💻 Author

**Your Name**
Fourth-year Computer Science Student
GitHub: [github.com/yourusername](https://github.com/yourusername)

---

## 📝 License

This project is for educational and demonstration purposes only.
It is **not affiliated with or endorsed by Amazon**.
