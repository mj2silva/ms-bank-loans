package com.ms.loans.entity;

import com.ms.restUtilities.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "loans")
public class Loan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @Column(updatable = false)
    private Long customerId;

    @Column(updatable = false, unique = true, nullable = false)
    private String loanNumber;

    @Column(updatable = false, nullable = false, name = "loan_type")
    private String type;

    @Column(updatable = false)
    private BigDecimal totalLoan;

    private BigDecimal amountPaid;

    @Setter(AccessLevel.NONE)
    private BigDecimal outstandingAmount;

    @PrePersist
    private void prePersist() {
        generateAccountNumber();
        initializeAmounts();
    }

    @PreUpdate
    private void generateAmountPaid() {
        outstandingAmount = totalLoan.subtract(amountPaid);
    }

    private void generateAccountNumber() {
        var loanPrefix = "00";
        switch (this.type) {
            case "CAMPAIGN":
                loanPrefix = "10";
                break;
            case "REGULAR":
                loanPrefix = "20";
                break;
            default:
                break;
        }
        this.loanNumber = loanPrefix + String.format("%06d", customerId) + String.format("%09d", Instant.now().getEpochSecond());
    }

    private void initializeAmounts() {
        this.amountPaid = BigDecimal.ZERO;
        this.outstandingAmount = this.totalLoan;
    }
}
