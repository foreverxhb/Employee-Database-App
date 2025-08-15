# Employee-Database-App
Java DB app with connection code. (JDBC, MySQL DB performs CRUD)

##
A beginner-friendly Java application that connects to a MySQL database and performs CRUD (Create, Read, Update, Delete) operations on employee records using JDBC.

## Project Overview
Objective:
Build a simple Java application to manage employee data with database connectivity.

## Technologies & Tools:

Java
MySQL
JDBC (Java Database Connectivity)
VS Code

## Key Features:
- Add new employee records
- View all employees
- Update employee information
- Delete employee records
- Console-based menu for easy interaction

## Setup Instructions
1. Clone the repository
git clone <your-repo-url>
cd "Employee Database App"

2. Install MySQL

Make sure MySQL server is installed and running on your machine.

3. Create Database and Table
CREATE DATABASE empDB;
USE empDB;

CREATE TABLE emp (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(100) NOT NULL,
    salary DOUBLE NOT NULL
);

4. Add MySQL JDBC Driver

Place mysql-connector-j-9.4.0.jar in the lib/ folder of your project. (ps:i got stuck while doing this)

5. Update Database Credentials

Edit EmpApp.java:

private static final String USER = "root"; // your MySQL username
private static final String PASSWORD = "----"; // your MySQL password

6. Compile the Java Code
javac -cp "lib/mysql-connector-j-9.4.0.jar;." src\EmpApp.java -d bin

7. Run the Application
java -cp "lib/mysql-connector-j-9.4.0.jar;bin;." EmpApp

## How It Works

Add Employee – Enter name, position, and salary.

View Employees – List all employees in the database.

Update Employee – Modify employee data using their ID.

Delete Employee – Remove an employee by ID.

Uses PreparedStatement for safe and efficient database operations.



Contributions are welcome!

Create a branch for your feature
Submit a pull request
