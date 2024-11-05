CREATE TABLE IF NOT EXISTS `loans` (
    `loan_id` INT AUTO_INCREMENT,
    `customer_id` INT NOT NULL,
    `loan_number` VARCHAR(100) NOT NULL,
    `loan_type` VARCHAR(100) NOT NULL,
    `total_loan` DECIMAL NOT NULL,
    `amount_paid` DECIMAL NOT NULL,
    `outstanding_amount` DECIMAL NOT NULL,
    `created_at` DATE NOT NULL,
    `created_by` VARCHAR(20) NOT NULL,
    `updated_at` DATE DEFAULT NULL,
    `updated_by` VARCHAR(20) DEFAULT NULL,
    PRIMARY KEY (`loan_id`)
);
