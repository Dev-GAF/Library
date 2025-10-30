package com.gaf.dev.Library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Librarian", schema = "Lib")
public class Librarian extends Employee
{
    public Librarian(String registration, String name, CPF cpf, RG rg, LocalDate hiringDate, String role, LocalDate dateBirth, char gender, double salary)
    {
        super(registration, name, cpf, rg, hiringDate, role, dateBirth, gender, salary);
    }

    @Override
    public void promote(String newPosition, double newSalary)
    {
        if (newSalary<0) throw new IllegalArgumentException("New salary cannot be negative.");

        super.setRole(newPosition);
        super.setSalary(newSalary);
    }

    @Override
    public void applyRaise(double percentage)
    {
        if (percentage<=0) throw new IllegalArgumentException("Percentage must be greater than zero.");

        super.setSalary(super.getSalary() + (super.getSalary() * (percentage/100)) );
    }

    @Override
    public int getSeniority()
    {
        return Period.between(super.getHiringDate(), LocalDate.now()).getYears();
    }
}
