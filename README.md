# 🎬 MovieTube Backend

This is the backend API for **MovieTube**, a full-stack movie web application built using **Spring Boot** and **MongoDB**. It handles authentication, user data, favorites, watchlists, and integrates with the TMDB API securely using JWT.

```
FRONTEND LINK
https://github.com/John-Oliver04/movietube-frontend
```
---

## 🚀 Features

- ✅ User registration & login (with JWT)
- 🎟 JWT-based route protection
- ❤️ User favorites & ⏳ watchlist management
- 🔐 API key hidden securely (no frontend exposure)
- 📦 MongoDB data persistence

---

## 🛠 Tech Stack

- ⚙️ Java 17
- 🌱 Spring Boot
- 🔐 Spring Security (JWT)
- 📦 MongoDB (with Spring Data)
- 🌍 TMDB API integration

---

## 📦 Setup Instructions

### 1. Clone the Repository


```bash
git clone https://github.com/your-username/springboot-movie-backend.git
cd springboot-movie-backend

```
###2. Configure Environment Variables

```bash
Create a file named .env or use system environment variables:

env
MONGODBURI=mongodb+srv://<username>:<password>@cluster.mongodb.net/movietube
TMDB_API_KEY=your_tmdb_api_key
You can also define them in application.properties:

properties
spring.data.mongodb.uri=${MONGODBURI}
tmdb.api.key=${TMDB_API_KEY}
```
###3. Run the App
```bash
Using Maven:
bash
./mvnw spring-boot:run
Or in VS Code/IntelliJ, run the MovietubeApplication class.

```
###4. API Endpoints
```bash
Method	Endpoint	                        Description
POST	/api/auth/signup	                Create new user
POST	/api/auth/login	                 Login & receive JWT token
GET	/api/auth/{username}/library	     Get user’s favorites/watchlist
POST	/api/auth/{username}/favorites	  Add movie to favorites
POST	/api/auth/{username}/watchlist	  Add movie to watchlist
```
🔐 Security Notes
JWT token is returned after login

Frontend must store it in localStorage and send it via Authorization: Bearer <token> header

📁 Folder Structure
```
css
src/
 └── main/
     ├── java/dev/virola/
     │   ├── model/
     │   ├── controller/
     │   ├── repository/
     │   ├── config/     (JWT utilities, security)
     │   └── MovietubeApplication.java
     └── resources/
         └── application.properties
```
🤝 License
This project is for educational/demo purposes only. You can adapt it for your portfolio or personal use.


✨ Author
```
Created by John Oliver Virola
📧 [johnolivervirola4@gmail.com]
🔗 [https://www.linkedin.com/in/john-oliver-virola-309315285/]

```
