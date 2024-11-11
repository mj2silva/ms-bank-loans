CREATE TABLE IF NOT EXISTS loans (
    loan_id serial primary key,
    customer_id INT NOT NULL,
    loan_number VARCHAR(100) UNIQUE NOT NULL,
    loan_type VARCHAR(100) NOT NULL,
    total_loan DECIMAL NOT NULL,
    amount_paid DECIMAL NOT NULL,
    outstanding_amount DECIMAL NOT NULL,
    created_at DATE NOT NULL,
    created_by VARCHAR(20) NOT NULL,
    updated_at DATE DEFAULT NULL,
    updated_by VARCHAR(20) DEFAULT NULL
);
