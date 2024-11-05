package com.ms.loans.constants;

public class LoanConstants {
    private LoanConstants() {
        // Private constructor in order to avoid object initialization
    }

    public static final String CUSTOMER_PATH = "/customers";
    public static final String LOAN_PATH = CUSTOMER_PATH + "/{customerId}/loans";
    public static final String LOAN_CREATED_MSG = "Loan was successfully created!";
}
