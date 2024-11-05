package com.ms.loans.exceptions;

public class LoanTransactionException extends RuntimeException {
    /**
     * Error while making a loan transaction
     * @param message Message to be sent to the client
     */
    public LoanTransactionException(String message, String transactionName) {
        super("Loan transaction exception: " + message + ". Transaction name: " + transactionName);
    }
}
