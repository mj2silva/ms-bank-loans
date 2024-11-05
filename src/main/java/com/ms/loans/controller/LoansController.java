package com.ms.loans.controller;

import com.ms.loans.constants.LoanConstants;
import com.ms.loans.dto.LoanDto;
import com.ms.loans.dto.LoanValidationGroups;
import com.ms.loans.service.LoanService;
import com.ms.restUtilities.dto.ResponseDto;
import com.ms.restUtilities.dto.ValidationGroups;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class LoansController {

    private final LoanService loanService;

    @PostMapping(LoanConstants.LOAN_PATH)
    public ResponseEntity<ResponseDto> createLoan(
            @PathVariable Long customerId,
            @RequestBody @Validated({ValidationGroups.CreationGroup.class, Default.class}) LoanDto loanDto
    ) {
        loanDto.setCustomerId(customerId);
        loanService.createLoan(loanDto);
        ResponseDto responseDto = new ResponseDto(201, LoanConstants.LOAN_CREATED_MSG);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/loans/{loanId}")
    public ResponseEntity<LoanDto> getLoan(@PathVariable Long loanId) {
        var customer = loanService.getLoan(loanId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customer);
    }

    @GetMapping("/loans/loan-number/{loanNumber}")
    public ResponseEntity<LoanDto> getLoan(@PathVariable String loanNumber) {
        var customer = loanService.getLoan(loanNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customer);
    }

    @GetMapping(LoanConstants.CUSTOMER_PATH + "/{customerId}/loans")
    public ResponseEntity<List<LoanDto>> getCustomerLoans(@PathVariable Long customerId) {
        var customer = loanService.getCustomerLoans(customerId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customer);
    }

    @PutMapping("loans/{loanId}")
    public ResponseEntity<ResponseDto> updateLoan(
            @PathVariable Long loanId,
            @RequestBody @Validated({ValidationGroups.UpdateGroup.class, Default.class}) LoanDto loanDto
    ) {
        var updated = loanService.updateLoan(loanId, loanDto);
        if (updated) return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "Loan updated")
        );
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("loans/payments")
    public ResponseEntity<ResponseDto> makeLoanPayment(
            @RequestBody @Validated({LoanValidationGroups.LoanPaymentGroup.class, Default.class}) LoanDto loanDto
    ) {
        loanService.makeLoanPayment(loanDto.getAmountPaid(), loanDto.getLoanId());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(HttpStatus.CREATED.value(), "Loan payment was successfully made")
        );
    }

    @DeleteMapping("/loans/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable String loanId) {
        loanService.deleteLoan(Long.getLong(loanId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
