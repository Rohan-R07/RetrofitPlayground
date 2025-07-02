# 📱 Retrofit Example App

A simple Android app demonstrating how to use Retrofit for GET, POST, and DELETE requests.  
It uses the [JSONPlaceholder](https://jsonplaceholder.typicode.com/) free REST API.

---

## 🚀 Features
✅ Fetch posts from JSONPlaceholder using `@GET`  
✅ Create new posts using `@POST` (visible in Android Studio Network Inspector)  
✅ Delete posts using `@DELETE`  
✅ Clean UI with a FloatingActionButton to trigger POST requests and Delete buttons on each item.

---

## 🔗 API Used
- Base URL: `https://jsonplaceholder.typicode.com/`

| Method | Endpoint                       | Description                  |
|--------|--------------------------------|------------------------------|
| GET    | `/posts`                       | Fetches list of posts        |
| POST   | `/posts`                       | Creates a new post           |
| DELETE | `/posts/{id}`                  | Deletes a post by ID         |

---

## 🛠 Built With
- 🐘 Kotlin
- 🪄 Retrofit
- 🏗 MVVM (optional, depending on your implementation)
- 📊 Android Studio Network Inspector (to view requests)

---

## 📸 Screen-Recording
| Home - GET | POST/DELETE actions |
|------------|---------------------|



https://github.com/user-attachments/assets/cd079377-1abe-4e09-8442-7f32aff4e423


---

## 🚀 Getting Started
1. Clone the repo
   ```bash
   git clone https://github.com/yourusername/yourrepo.git
