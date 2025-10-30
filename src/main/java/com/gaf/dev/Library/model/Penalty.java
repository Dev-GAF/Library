package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Penalty", schema = "Lib")
public class Penalty
{
    private static final double DAILY_RATE = 5.0D;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", referencedColumnName = "id", nullable = false)
    private final Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_id", referencedColumnName = "id", nullable = false)
    private final Loan loan;

    @Setter(AccessLevel.NONE)
    @Column(name = "days_late", nullable = false)
    private short daysLate;

    @Setter(AccessLevel.NONE)
    @Column(name = "amount", nullable = false)
    private double amount;

    @Setter(AccessLevel.NONE)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PenaltyStatus status;

    @Column(name = "creation_date", nullable = false)
    private final LocalDate creationDate;

    @Setter(AccessLevel.NONE)
    @Column(name = "payment_date")
    private LocalDate paymentDate;

    public Penalty(Reader reader, Loan loan, short daysLate)
    {
        this.reader = reader;
        this.loan = loan;
        this.daysLate = daysLate;
        this.creationDate = LocalDate.now();
        this.status = PenaltyStatus.PENDING;
        this.calculateAmount();
    }

    public void calculateAmount()
    {
        this.amount = daysLate * DAILY_RATE;
    }

    public void markAsPaid()
    {
        this.status = PenaltyStatus.PAID;
        this.paymentDate = LocalDate.now();
    }

    public boolean isPaid()
    {
        return this.status == PenaltyStatus.PAID;
    }

    public boolean isPending()
    {
        return this.status == PenaltyStatus.PENDING;
    }
}
