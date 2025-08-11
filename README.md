Employee Payroll Management System
This is a simple Employee Payroll Management System built with Java Swing and PostgreSQL. It allows users to register, log in, and manage employee and payroll information.

Features
User Authentication:

Users can register for a new account.

Existing users can log in with their credentials.

Passwords are encrypted using SHA-256 hashing for security.

Employee Management:

Add new employees with details like name, position, department, and salary.

View a list of all employees in a table.

Payroll Management:

Add payroll records for employees, including the amount and pay date.

View a list of all payroll records.

Dashboard:

A central dashboard provides easy navigation to all features.

Technologies Used
Frontend: Java Swing

Backend: Java

Database: PostgreSQL

Database Schema
The application uses a PostgreSQL database with the following tables:

users: Stores user credentials for authentication.

id (SERIAL PRIMARY KEY)

username (VARCHAR(50) UNIQUE NOT NULL)

password (VARCHAR(255) NOT NULL)

role (VARCHAR(20) NOT NULL DEFAULT 'user')

employees: Stores employee information.

id (SERIAL PRIMARY KEY)

name (VARCHAR(100) NOT NULL)

position (VARCHAR(100))

department (VARCHAR(100))

salary (NUMERIC(10, 2))

payrolls: Stores payroll records for employees.

id (SERIAL PRIMARY KEY)

employee_id (INT REFERENCES employees(id) ON DELETE CASCADE)

pay_date (DATE NOT NULL)

amount (NUMERIC(10, 2) NOT NULL)

Getting Started
Prerequisites
Java Development Kit (JDK)

PostgreSQL

Database Setup
Make sure your PostgreSQL server is running.

Create a database named EmployeeDB.

Run the schema.sql script to create the necessary tables and insert an admin user.

Configuration
Update the database credentials in src/DBConnection.java if they differ from the default values:

Java

static final String DB_URL = "jdbc:postgresql://localhost:5432/EmployeeDB";
static final String USER = "postgres";
static final String PASSWORD = "ShzT8gh6";
Running the Application
Compile and run the Main.java class to start the application. The registration screen will be the first window you see.

Folder Structure
src: Contains all the Java source files.

lib: Contains the project dependencies (e.g., PostgreSQL JDBC driver).

bin: The output directory for compiled .class files.

How to Use
Register: Launch the application and register a new user account.

Login: After successful registration, you will be taken to the login screen. Enter your new credentials to log in.

Dashboard: Upon successful login, you will see the main dashboard, which provides access to all the application's features.

Manage Employees and Payrolls: Use the buttons on the dashboard to add and view employees and payrolls.

Dependency Management
The project's dependencies are managed in the lib folder. The JAVA PROJECTS view in VS Code can be used to manage these dependencies.

Author
jessembugua
