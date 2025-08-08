-- schema.sql
-- Creates the tables needed for the Employee Payroll System

-- Drop tables if they already exist (for resetting the DB)
DROP TABLE IF EXISTS payroll;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS users;

-- Table to store user login credentials
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password_hash VARCHAR(255) NOT NULL
);

-- Table to store employee details
CREATE TABLE employee (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(50),
    department VARCHAR(50)
);

-- Table to store payroll records
CREATE TABLE payroll (
    id SERIAL PRIMARY KEY,
    employee_id INT NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    pay_date DATE NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employee(id) ON DELETE CASCADE
);

