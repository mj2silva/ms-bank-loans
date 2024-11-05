package com.ms.loans.exceptions;

public class LoanConflictException extends RuntimeException {
    /**
     * Error thrown when a loan creation / update operation causes a conflict with an existing loan.
     * @param message Message representative to the error
     * @param operation Operation name (Update loan, create loan, etc.)
     */
    public LoanConflictException(String message, String operation) {
        super("Loan conflict: " + message + ". Operation: " + operation.trim());
    }
}
