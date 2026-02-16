# Spring Boot RESTful APIs - Assignment Summary

This folder contains six separate Spring Boot projects implementing the assignment questions:

- Question 1: Library Book Management API
- Question 2: Student Registration API
- Question 3: Restaurant Menu API
- Question 4: E-Commerce Product API
- Question 5: Task Management API
- Bonus: User Profile API with response wrapper

Each project:
- Uses Spring Boot with the Web starter only.
- Exposes REST controllers (no service or repository layers).
- Stores data in in-memory `List` instances with sample records.
- Returns appropriate HTTP status codes (200, 201, 204, 404).

## How to Run Any Question

For each project (Q1–Q6):
- Open the project in your IDE.
- Run the `*Application` main class in `src/main/java`.
- Default base URL is `http://localhost:8080`.
- Test with Postman or a browser.

## Question 1 – Library Book Management API

Project: `question1-library-api`  
Main class: `Question1LibraryApiApplication`

Model:
- `Book` with `id`, `title`, `author`, `isbn`, `publicationYear`.

Controller:
- `BookController` at `/api/books`.

Endpoints:
- `GET /api/books` – list all books.
- `GET /api/books/{id}` – get book by ID.
- `GET /api/books/search?title={title}` – search by title.
- `POST /api/books` – add new book.
- `DELETE /api/books/{id}` – delete book.

## Question 2 – Student Registration API

Project: `question2-student-api`  
Main class: `Question2StudentApiApplication`

Model:
- `Student` with `studentId`, `firstName`, `lastName`, `email`, `major`, `gpa`.

Controller:
- `StudentController` at `/api/students`.

Endpoints:
- `GET /api/students` – all students.
- `GET /api/students/{studentId}` – student by ID.
- `GET /api/students/major/{major}` – students by major.
- `GET /api/students/filter?gpa={minGpa}` – students with `gpa >= minGpa`.
- `POST /api/students` – register new student.
- `PUT /api/students/{studentId}` – update student.

## Question 3 – Restaurant Menu API

Project: `question3-restaurant-api`  
Main class: `Question3RestaurantApiApplication`

Model:
- `MenuItem` with `id`, `name`, `description`, `price`, `category`, `available`.

Controller:
- `MenuController` at `/api/menu`.

Endpoints:
- `GET /api/menu` – all menu items.
- `GET /api/menu/{id}` – item by ID.
- `GET /api/menu/category/{category}` – items by category.
- `GET /api/menu/available?available={true/false}` – by availability.
- `GET /api/menu/search?name={name}` – search by name.
- `POST /api/menu` – add menu item.
- `PUT /api/menu/{id}/availability` – toggle availability.
- `DELETE /api/menu/{id}` – delete item.

## Question 4 – E-Commerce Product API

Project: `question4-E-commerce-api`  
Main class: `Question4ECommerceApiApplication`

Model:
- `Product` with `productId`, `name`, `description`, `price`, `category`, `stockQuantity`, `brand`.

Controller:
- `ProductController` at `/api/products`.

Endpoints:
- `GET /api/products?page={page}&limit={limit}` – all products with pagination.
- `GET /api/products/{productId}` – product details.
- `GET /api/products/category/{category}` – by category.
- `GET /api/products/brand/{brand}` – by brand.
- `GET /api/products/search?keyword={keyword}` – search in name or description.
- `GET /api/products/price-range?min={min}&max={max}` – filter by price.
- `GET /api/products/in-stock` – `stockQuantity > 0`.
- `POST /api/products` – add product.
- `PUT /api/products/{productId}` – update product.
- `PATCH /api/products/{productId}/stock?quantity={quantity}` – update stock.
- `DELETE /api/products/{productId}` – delete product.

## Question 5 – Task Management API

Project: `question5-task-api`  
Main class: `Question5TaskApiApplication`

Model:
- `Task` with `taskId`, `title`, `description`, `completed`, `priority`, `dueDate`.

Controller:
- `TaskController` at `/api/tasks`.

Endpoints:
- `GET /api/tasks` – all tasks.
- `GET /api/tasks/{taskId}` – task by ID.
- `GET /api/tasks/status?completed={true/false}` – by completion status.
- `GET /api/tasks/priority/{priority}` – by priority.
- `POST /api/tasks` – create task.
- `PUT /api/tasks/{taskId}` – update task.
- `PATCH /api/tasks/{taskId}/complete` – mark completed.
- `DELETE /api/tasks/{taskId}` – delete task.

## Bonus – User Profile API with Response Wrapper

Project: `question6-user-profile-api`  
Main class: `Question6UserProfileApiApplication`

Models:
- `UserProfile` with `userId`, `username`, `email`, `fullName`, `age`, `country`, `bio`, `active`.
- `ApiResponse<T>` generic wrapper with `success`, `message`, `data`.

Controller:
- `UserProfileController` at `/api/user-profiles`.

Main endpoints:
- `GET /api/user-profiles` – all profiles (wrapped response).
- `GET /api/user-profiles/{userId}` – profile by ID.
- `POST /api/user-profiles` – create profile.
- `PUT /api/user-profiles/{userId}` – update profile.
- `DELETE /api/user-profiles/{userId}` – delete profile.
- `GET /api/user-profiles/search/username/{username}` – search by username.
- `GET /api/user-profiles/search/country/{country}` – search by country.
- `GET /api/user-profiles/search/age-range?minAge={min}&maxAge={max}` – search by age range.
- `PATCH /api/user-profiles/{userId}/activate` – activate profile.
- `PATCH /api/user-profiles/{userId}/deactivate` – deactivate profile.

## Testing Evidence

Folder: `testedScreenshoots/`  
Contains Postman screenshots for each question (Q1–Q5) showing:
- Successful GET, POST, PUT/PATCH, DELETE requests.
- Filtering and search endpoints where required.

