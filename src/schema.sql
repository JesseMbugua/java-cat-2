-- schema.sql

-- Users table for authentication
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'user'
);


-- Employees table
CREATE TABLE employees (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    position VARCHAR(100),
    department VARCHAR(100),
    salary NUMERIC(10, 2)
);

-- Payroll table
CREATE TABLE payrolls (
    id SERIAL PRIMARY KEY,
    employee_id INT REFERENCES employees(id) ON DELETE CASCADE,
    pay_date DATE NOT NULL,
    amount NUMERIC(10, 2) NOT NULL
);

-- Example: Insert an admin user (password: 'admin123' hashed with SHA-256)
INSERT INTO users (username, password) VALUES
('admin', '240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9');
