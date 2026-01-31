# Watchlist JDBC CRUD Application

A simple **menu-driven Java JDBC application** that performs **CRUD operations** on a MySQL database to manage a movie watchlist.

---

## Features
- Add movie (Create)
- View movies (Read)
- Update movie details (Update)
- Delete movie with confirmation (Delete)
- Console-based menu system
- Uses PreparedStatement for database operations

---

## Technologies Used
- Java (JDK 21)
- JDBC
- MySQL
- MySQL Connector/J

---

## Database
**Database:** movielistdb  
**Table:** watchlist

---

## How to Run
1. Create the database and table
2. Add MySQL Connector/J to the classpath
3. Compile and run the program

```bash
javac WatchlistApp.java
java WatchlistApp
