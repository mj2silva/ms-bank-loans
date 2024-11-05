package com.ms.loans.service;

import com.ms.loans.dto.LoanDto;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {
    /**
     * Creates a loan.
     *
     * @param loanDto A LoanDto object containing new loan information.
     */
    void createLoan(LoanDto loanDto);

    /**
     * Fetches loan details by loan ID.
     *
     * @param loanId The ID of the loan.
     * @return A LoanDto object containing loan details.
     */
    LoanDto getLoan(Long loanId);

    /**
     * Fetches loan details by loan number.
     *
     * @param loanNumber The number of the loan.
     * @return A LoanDto object containing loan details.
     */
    LoanDto getLoan(String loanNumber);

    /**
     * Creates a new loan payment, which recalculates amountPaid and outstandingAmount
     * @param amount The paid amount
     * @param loanId The loan id
     */
    void makeLoanPayment(BigDecimal amount, Long loanId);

    /**
     * Lists all the loans made by the customer
     * @param customerId The customer id
     * @return A list containing all the loans made by the given customer
     */
    List<LoanDto> getCustomerLoans(Long customerId);

    /**
     * Updates existing loan details.
     *
     * @param loanDto An object containing the updated user loan information.
     * @return A boolean indicating whether the update was successful or not.
     */
    boolean updateLoan(Long loanId, @Valid LoanDto loanDto);

    /**
     * Deletes the loan by loan id.
     *
     * @param loanId The id of the loan.
     */
    void deleteLoan(Long loanId);
}
