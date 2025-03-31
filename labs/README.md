# JDBC Lab: Library Management System

## Objective
In this lab, you will build a **Library Management System** using JDBC. You will create a table for books, insert book records, and retrieve book information based on specific criteria. This will help you understand how to interact with a relational database using Java.

## Scenario
You are working as a developer for a local library. The library needs a system to store and retrieve information about books, including their title, author, publication year, and availability status. Your task is to implement the core database interactions using JDBC.

## Setup
1. **Create the Database:**
   ```sql
   CREATE DATABASE library_db;
   ```
2. **Create the Books Table:**
   ```sql
   CREATE TABLE books (
       book_id SERIAL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL,
       publication_year INT NOT NULL,
       is_available BOOLEAN DEFAULT TRUE
   );
   ```

## Tasks
### Task 1: Establish a Database Connection
- Write a Java program that establishes a connection to the PostgreSQL database `library_db`.
- Use the JDBC `DriverManager` to connect.
- Print a message confirming successful connection.

### Task 2: Insert New Books
- Create a method `insertBook(Connection conn, String title, String author, int publicationYear, boolean isAvailable)` that inserts a new book into the `books` table.
- Insert at least **three** books into the table with different authors and years.

### Task 3: Retrieve All Books
- Implement a method `getAllBooks(Connection conn)` that retrieves all books and prints them in a formatted way.
- Display: `ID | Title | Author | Year | Available`

### Task 4: Find Books Published Between Two Years
- Implement a method `findBooksBetweenYears(Connection conn, int startYear, int endYear)` that retrieves books published between the given years and prints them.
- Display: `ID | Title | Author | Year`

### Task 5: Retrieve Available Books
- Implement a method `getAvailableBooks(Connection conn)` that retrieves books where `is_available` is `true` and prints them.

### Bonus Challenges
1. **Paginate book results:** Implement `paginateBooks(Connection conn, int limit, int offset)` to retrieve books in chunks of a specified size.
2. **Find books by title prefix:** Implement `findBooksByTitlePrefix(Connection conn, String prefix)` to find books where the title starts with a given substring.
3. **Sort books dynamically:** Implement `getBooksSorted(Connection conn, String orderBy, String orderDirection)` to retrieve books sorted by a user-specified column and direction (ASC/DESC).
4. **Advanced Search:** Implement `findBooksByTitleAuthorYear(Connection conn, String titleSubstring, String authorSubstring, int startYear, int endYear)` to find books where:
   - The title contains `titleSubstring`.
   - The author contains `authorSubstring`.
   - The publication year is between `startYear` and `endYear`.

## Expected Output Example
```
Connection established successfully.
Inserted book: The Catcher in the Rye by J.D. Salinger
Inserted book: 1984 by George Orwell
Inserted book: To Kill a Mockingbird by Harper Lee

All Books:
1 | The Catcher in the Rye | J.D. Salinger | 1951 | Available
2 | 1984 | George Orwell | 1949 | Available
3 | To Kill a Mockingbird | Harper Lee | 1960 | Available

Books published between 1945 and 1955:
1 | The Catcher in the Rye | J.D. Salinger | 1951
2 | 1984 | George Orwell | 1949
```
