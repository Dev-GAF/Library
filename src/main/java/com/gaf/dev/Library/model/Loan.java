package com.gaf.dev.Library.model;

import com.gaf.dev.Library.utils.DateValidator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Data
@Table(name = "Loan", schema = "Lib")
public class Loan implements DateValidator
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reader_id", referencedColumnName = "id", nullable = false)
    private final Reader reader;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exemplar_id", referencedColumnName = "id", nullable = false)
    private final Exemplary exemplary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "library_id", referencedColumnName = "id", nullable = false)
    private final Library library;

    @Column(name = "loan_date", nullable = false)
    private final LocalDate loanDate; //Data do empréstimo

    @Column(name = "due_date", nullable = false)
    private final LocalDate dueDate;

    @Setter(AccessLevel.NONE)
    @Column(name = "return_date", nullable = false)
    private LocalDate returnDate;

    @Setter(AccessLevel.NONE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loan")
    private List<Penalty> penalties;

    @Override
    public boolean isValid(byte day, byte month, short year)
    {
        if (day<1 || day>31 || month<1 || month>12) return true;

        if (day>30 && (month==4 || month==6 || month==9 || month==11)) return true;

        if (day>29 && month==2) return true;
        if (day>28 && month==2 && this.isLeapYear(year)) return true;

        return false;
    }

    public Loan(Reader reader, Exemplary exemplary, Library library, LocalDate loanDate)
    {
        this.reader = reader;
        this.exemplary = exemplary;
        this.library = library;

        if (loanDate==null)
            throw new IllegalArgumentException("Date cannot be null.");

        if (!this.isValid((byte) loanDate.getDayOfMonth(), (byte) loanDate.getMonthValue(), (byte) loanDate.getYear()))
            throw new IllegalArgumentException("Date format does not exist.");

        if (loanDate.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("Date cannot be in the past.");

        this.loanDate = loanDate;
        this.dueDate = loanDate.plusWeeks(1);
        this.returnDate = null;
    }

    public boolean isOverdue() // → verifica se está atrasado
    {
        if (returnDate == null)
            return LocalDate.now().isAfter(dueDate);

        return returnDate.isAfter(dueDate);
    }

    public short getDaysLate() // → retorna quantos dias de atraso há (se houver)
    {
        if (returnDate == null)
            return (short)ChronoUnit.DAYS.between(dueDate, LocalDate.now());

        return (short)ChronoUnit.DAYS.between(dueDate, returnDate);
    }

    public boolean hasPenalty() // → indica se há multa
    {
        return this.isOverdue() && this.getDaysLate() > 0;
    }

    public void calculatePenalty(double dailyRate)
    {
        if (this.isOverdue())
        {
            Penalty penalty = new Penalty(this.reader, this, this.getDaysLate());
            this.penalties.add(penalty);
        }
    }

    public boolean isActive() // → empréstimo ainda aberto
    {
        return dueDate==null;
    }

    public void markAsReturned(LocalDate returnDate)
    {
        this.returnDate = returnDate;
        this.penalties.clear();
    }
    
}
