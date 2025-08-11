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






format it using the readme language



Show thinking
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

How to Use
Register: Launch the application and register a new user account.

Login: After successful registration, you will be taken to the login screen. Enter your new credentials to log in.

Dashboard: Upon successful login, you will see the main dashboard, which provides access to all the application's features.

Manage Employees and Payrolls: Use the buttons on the dashboard to add and view employees and payrolls.

Folder Structure
src: Contains all the Java source files.

lib: Contains the project dependencies (e.g., PostgreSQL JDBC driver).

bin: The output directory for compiled .class files.

Dependency Management
The project's dependencies are managed in the lib folder. The JAVA PROJECTS view in VS Code can be used to manage these dependencies.

Author
jessembugua







Make it look good on github



Show thinking
☕ Employee Payroll Management System
A simple yet robust Employee Payroll Management System built with Java Swing and PostgreSQL. This desktop application provides an intuitive interface for managing employees and their payroll records, complete with user authentication and data persistence.

✨ Features
🔐 User Authentication:

Secure user registration with password hashing (SHA-256).

Login system to protect access to the application.

👨‍💼 Employee Management:

Add new employee records, including name, position, department, and salary.

View a comprehensive list of all employees in a clear, tabular format.

💵 Payroll Management:

Easily add payroll entries for each employee, specifying the pay date and amount.

View a detailed history of all payroll records.

🖥️ Intuitive Dashboard:

A central navigation hub to seamlessly access all application features.

🛠️ Technologies Used
Frontend: Java Swing

Backend: Java

Database: PostgreSQL

🗄️ Database Schema
The application relies on a PostgreSQL database with the following structure:

Table	Column	Type	Constraints	Description
users	id	SERIAL	PRIMARY KEY	Unique identifier for each user.
username	VARCHAR(50)	UNIQUE, NOT NULL	User's login name.
password	VARCHAR(255)	NOT NULL	Hashed password for the user.
role	VARCHAR(20)	NOT NULL, DEFAULT 'user'	User role for potential future enhancements.
employees	id	SERIAL	PRIMARY KEY	Unique identifier for each employee.
name	VARCHAR(100)	NOT NULL	Employee's full name.
position	VARCHAR(100)		Employee's job title.
department	VARCHAR(100)		Department the employee works in.
salary	NUMERIC(10, 2)		Employee's salary.
payrolls	id	SERIAL	PRIMARY KEY	Unique identifier for each payroll record.
employee_id	INT	REFERENCES employees(id) ON DELETE CASCADE	Links to the employees table.
pay_date	DATE	NOT NULL	The date the payroll was issued.
amount	NUMERIC(10, 2)	NOT NULL	The gross amount of the payroll.

Export to Sheets
This schema is defined in the schema.sql file.

🚀 Getting Started
Prerequisites
Java Development Kit (JDK) 8 or higher

PostgreSQL server

Installation & Setup
Clone the repository:

Bash

git clone https://github.com/jessembugua/java-cat-2.git
cd java-cat-2
Database Setup:

Ensure your PostgreSQL server is running.

Create a new database named EmployeeDB.

Execute the src/schema.sql script to create the necessary tables and a default admin user.

Configure Database Connection:

Open src/DBConnection.java.

Update the database credentials if they differ from the defaults:

Java

static final String DB_URL = "jdbc:postgresql://localhost:5432/EmployeeDB";
static final String USER = "postgres";
static final String PASSWORD = "ShzT8gh6"; // Replace with your password
Run the Application:

Compile the Java source files.

Run the Main.java class to start the application.

📖 How to Use
Register: Upon launching the application, you will be greeted with the registration screen. Create a new user account.

Login: After registration, you'll be directed to the login screen. Use your new credentials to log in.

Dashboard: The main dashboard provides access to all the system's features.

Manage Data: Use the dashboard buttons to add and view employees and payroll records.

📂 Project Structure
.
├── bin/                # Compiled .class files and schema
├── lib/                # Project dependencies (PostgreSQL JDBC Driver)
├── src/                # Java source code (.java files)
└── .vscode/            # VS Code settings
Note: This project is configured to be used with Visual Studio Code and its Java extensions. The JAVA PROJECTS view can be used for dependency management.
