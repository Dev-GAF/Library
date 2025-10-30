package com.gaf.dev.Library.model;

import com.gaf.dev.Library.utils.DateValidator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "Employee", schema = "Lib")
public abstract class Employee extends Person implements DateValidator, Cloneable
{
    @Column(name = "registration_employee", length = 10, nullable = false, unique = true)
    private final String registration;

    @Column(name = "hiring_date", nullable = false)
    private final LocalDate hiringDate;

    @Setter(AccessLevel.NONE)
    @Column(name = "role", length = 20, nullable = false)
    private String role;

    @Setter(AccessLevel.NONE)
    @Column(name = "salary", precision = 6, scale = 2, nullable = false)
    private double salary;

    @Override
    public boolean isValid(byte day, byte month, short year)
    {
        int currentYear = LocalDate.now().getYear();
        if (year < currentYear-120) return true;
        if (year > currentYear) return true;

        if (day<1 || day>31 || month<1 || month>12) return true;

        if (day>30 && (month==4 || month==6 || month==9 || month==11)) return true;

        if (day>29 && month==2) return true;
        if (day>28 && month==2 && this.isLeapYear(year)) return true;

        return false;
    }

    public Employee(String registration, String name, CPF cpf, RG rg, LocalDate hiringDate, String role, LocalDate dateBirth, char gender, double salary)
    {
        super(name, cpf, rg, dateBirth, gender);

        if (registration==null || registration.trim().isEmpty())
            throw new IllegalArgumentException("Registration cannot be null or empty.");

        if (hiringDate==null)
            throw new IllegalArgumentException("Hiring date cannot be null.");

        if (this.isValid((byte) hiringDate.getDayOfMonth(), (byte) hiringDate.getMonthValue(), (byte) hiringDate.getYear()))
            throw new IllegalArgumentException("Date of improper hiring. Must be a real date and no more than 120 years old.");

        if (role==null || role.trim().isEmpty())
            throw new IllegalArgumentException("Role cannot be null or empty.");

        this.registration = registration;
        this.hiringDate = hiringDate;
        this.setRole(role);
        this.setSalary(salary);
    }

    public void setRole(String role)
    {
        if (role==null || role.trim().isEmpty())
            throw new IllegalArgumentException("Role cannot be null and empty.");

        this.role = role;
    }

    public void setSalary(double salary)
    {
        if (salary<0) throw new IllegalArgumentException("Salary cannot be negative.");

        this.salary = salary;
    }

    public abstract void promote(String newPosition, double newSalary);

    public abstract void applyRaise(double percentage);

    public abstract int getSeniority();

    @Override
    public Object clone()
    {
        Employee clone = (Employee)super.clone();

        clone.role = this.role;
        clone.salary = this.salary;

        return clone;
    }
}
