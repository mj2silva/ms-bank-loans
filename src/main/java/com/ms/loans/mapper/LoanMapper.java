package com.ms.loans.mapper;

import com.ms.loans.dto.LoanDto;
import com.ms.loans.entity.Loan;

public class LoanMapper {
    public static LoanDto toDto(Loan loan) {
        var loanDto = new LoanDto();
        loanDto.setLoanId(loan.getId());
        loanDto.setCustomerId(loan.getCustomerId());
        loanDto.setLoanNumber(loan.getLoanNumber());
        loanDto.setType(loan.getType());
        loanDto.setTotalLoan(loan.getTotalLoan());
        loanDto.setAmountPaid(loan.getAmountPaid());
        loanDto.setOutstandingAmount(loan.getOutstandingAmount());

        return loanDto;
    }

    public static Loan toEntity(LoanDto loanDto) {
        var loan = new Loan();
        loan.setId(loanDto.getLoanId());
        loan.setCustomerId(loanDto.getCustomerId());
        loan.setLoanNumber(loanDto.getLoanNumber());
        loan.setType(loanDto.getType());
        loan.setTotalLoan(loanDto.getTotalLoan());
        loan.setAmountPaid(loanDto.getAmountPaid());

        return loan;
    }
}
