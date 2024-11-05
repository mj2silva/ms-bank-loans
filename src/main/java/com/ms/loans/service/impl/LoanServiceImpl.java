package com.ms.loans.service.impl;

import com.ms.loans.dto.LoanDto;
import com.ms.loans.exceptions.LoanConflictException;
import com.ms.loans.exceptions.LoanTransactionException;
import com.ms.loans.mapper.LoanMapper;
import com.ms.loans.service.LoanService;
import com.ms.loans.repository.LoanRepository;
import com.ms.restUtilities.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public void createLoan(LoanDto loanDto) {
        var customerLoans = loanRepository.findByCustomerId(loanDto.getCustomerId());
        if (customerLoans != null && !customerLoans.isEmpty()) {
            if (customerLoans.stream().anyMatch(loan -> Objects.equals(loan.getType(), loanDto.getType()))) {
                throw new LoanConflictException("Customer already have a loan of type "+ loanDto.getType(), "Create loan");
            }
        }
        var loanEntity = LoanMapper.toEntity(loanDto);
        loanRepository.save(loanEntity);
    }

    @Override
    public LoanDto getLoan(Long loanId) {
        var loan  = loanRepository.findById(loanId).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "id", loanId.toString())
        );

        return LoanMapper.toDto(loan);
    }

    @Override
    @Transactional
    public void makeLoanPayment(BigDecimal amount, Long loanId) {
        var loan = loanRepository.findById(loanId).orElseThrow(
                () -> new LoanTransactionException("Loan id is invalid or does not exists", "Loan payment")
        );

        // Updating the paid amount
        loan.setAmountPaid(loan.getAmountPaid().add(amount));

        if (loan.getAmountPaid().compareTo(loan.getTotalLoan()) > 0) {
            throw new LoanTransactionException("Loan paid amount cannot be greater than total debt", "Loan payment");
        }

        loanRepository.save(loan);
    }

    @Override
    public List<LoanDto> getCustomerLoans(Long customerId) {
        var customerLoans = loanRepository.findByCustomerId(customerId);
        var customerLoansDtoList = customerLoans.stream().map(LoanMapper::toDto);
        return customerLoansDtoList.toList();
    }

    @Override
    public boolean updateLoan(Long loanId, LoanDto loanDto) {
        var loanExists = loanRepository.existsById(loanId);
        if (!loanExists) throw new ResourceNotFoundException("Loan", "id", loanId.toString());

        var updatedLoan = LoanMapper.toEntity(loanDto);
        updatedLoan.setId(loanId);
        loanRepository.save(updatedLoan);

        return true;
    }

    @Override
    public void deleteLoan(Long loanId) {
        var loanExists = loanRepository.existsById(loanId);
        if (!loanExists) throw new ResourceNotFoundException("Loan", "id", loanId.toString());

        loanRepository.deleteById(loanId);
    }

    @Override
    public LoanDto getLoan(String loanNumber) {
        var loan = loanRepository
                .findByLoanNumber(loanNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "loan number", loanNumber));

        return LoanMapper.toDto(loan);
    }
}
