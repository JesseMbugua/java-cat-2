# ☕ Employee Payroll Management System

A simple yet robust Employee Payroll Management System built with Java Swing and PostgreSQL. This desktop application provides an intuitive interface for managing employees and their payroll records, complete with user authentication and data persistence.


---

## ✨ Features

* 🔐 **Secure User Authentication:**
    * User registration and login system.
    * Passwords are encrypted using SHA-256 hashing for security.
* 👨‍💼 **Employee Management:**
    * Add new employees with details like name, position, department, and salary.
    * View a comprehensive list of all employees in a clear, tabular format.
* 💵 **Payroll Management:**
    * Easily add payroll entries for employees, specifying the amount and pay date.
    * View a detailed history of all payroll records.
* 🖥️ **Intuitive Dashboard:**
    * A central navigation hub to seamlessly access all application features after a successful login.

---

## 🛠️ Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

* **Frontend:** Java Swing
* **Backend:** Java
* **Database:** PostgreSQL
* **Dependency:** PostgreSQL JDBC Driver

---

## 🗄️ Database Schema

The application relies on a PostgreSQL database with the following structure, as defined in `schema.sql`:

| Table     | Column      | Type             | Constraints                                  | Description                                  |
| :-------- | :---------- | :--------------- | :------------------------------------------- | :------------------------------------------- |
| `users`   | `id`        | `SERIAL`         | `PRIMARY KEY`                                | Unique identifier for each user.             |
|           | `username`  | `VARCHAR(50)`    | `UNIQUE`, `NOT NULL`                         | User's login name.                           |
|           | `password`  | `VARCHAR(255)`   | `NOT NULL`                                   | Hashed password for the user.                |
|           | `role`      | `VARCHAR(20)`    | `NOT NULL`, `DEFAULT 'user'`                 | User role for potential future enhancements. |
| `employees`| `id`        | `SERIAL`         | `PRIMARY KEY`                                | Unique identifier for each employee.         |
|           | `name`      | `VARCHAR(100)`   | `NOT NULL`                                   | Employee's full name.                        |
|           | `position`  | `VARCHAR(100)`   |                                              | Employee's job title.                        |
|           | `department`| `VARCHAR(100)`   |                                              | Department the employee works in.            |
|           | `salary`    | `NUMERIC(10, 2)` |                                              | Employee's salary.                           |
| `payrolls`| `id`        | `SERIAL`         | `PRIMARY KEY`                                | Unique identifier for each payroll record.   |
|           | `employee_id`| `INT`           | `REFERENCES employees(id) ON DELETE CASCADE` | Links to the `employees` table.              |
|           | `pay_date`  | `DATE`           | `NOT NULL`                                   | The date the payroll was issued.             |
|           | `amount`    | `NUMERIC(10, 2)` | `NOT NULL`                                   | The gross amount of the payroll.             |

---

## 🚀 Getting Started

### Prerequisites

* Java Development Kit (JDK) 8 or higher
* PostgreSQL Server

### Installation & Setup

1.  **Clone the repository:**
    ```sh
    git clone <your-repository-url>
    cd <repository-directory>
    ```

2.  **Database Setup:**
    * Ensure your PostgreSQL server is running.
    * Create a new database named **`EmployeeDB`**.
    * Execute the `src/schema.sql` script to create the necessary tables and a default admin user.

3.  **Configure Database Connection:**
    * Open `src/DBConnection.java`.
    * Update the database credentials if they differ from the defaults:
        ```java
        static final String DB_URL = "jdbc:postgresql://localhost:5432/EmployeeDB";
        static final String USER = "postgres";
        static final String PASSWORD = "ShzT8gh6"; // Replace with your password
        ```

4.  **Run the Application:**
    * Compile the Java source files.
    * Run the `Main.java` class to start the application. The program starts with the registration screen.

---

## 📖 How to Use

1.  **Register:** Launch the application and register a new user account on the initial screen.
2.  **Login:** After successful registration, you'll be directed to the login screen. Use your new credentials to log in.
3.  **Dashboard:** The main dashboard provides access to all the system's features.
4.  **Manage Data:** Use the dashboard buttons to add and view employees and payrolls.

---

## 📂 Project Structure

The project follows a standard Java project structure managed for VS Code.
