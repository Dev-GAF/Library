package main.java.gaf.dev.biblioteca.model;

import gaf.dev.biblioteca.utils.DateValidator;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Employee extends Person implements DateValidator, Cloneable
{
    private final String registration;
    private final LocalDate hiringDate;
    private String role;
    private double salary;

    @Override
    public boolean isValid(byte day, byte month, short year)
    {
        int currentYear = LocalDate.now().getYear();
        if (year < currentYear-120) return false;
        if (year > currentYear) return  false;

        if (day<1 || day>31 || month<1 || month>12) return false;

        if (day>30 && (month==4 || month==6 || month==9 || month==11)) return false;

        if (day>29 && month==2) return false;
        if (day>28 && month==2 && !this.isLeapYear(year)) return false;

        return true;
    }

    public Employee(String registration, String name, CPF cpf, RG rg, LocalDate hiringDate, String role, LocalDate dateBirth, char gender, double salary)
    {
        super(name, cpf, rg, dateBirth, gender);

        if (registration==null || registration.trim().isEmpty())
            throw new IllegalArgumentException("Registration cannot be null or empty.");

        if (hiringDate==null)
            throw new IllegalArgumentException("Hiring date cannot be null.");

        if (!this.isValid((byte)hiringDate.getDayOfMonth(), (byte)hiringDate.getMonthValue(), (byte)hiringDate.getYear()))
            throw new IllegalArgumentException("Date of improper hiring. Must be a real date and no more than 120 years old.");

        if (role==null || role.trim().isEmpty())
            throw new IllegalArgumentException("Role cannot be null or empty.");

        this.registration = registration;
        this.hiringDate = hiringDate;
        this.setRole(role);
        this.setSalary(salary);
    }

    public String getRegistration() { return this.registration; }

    public LocalDate getHiringDate() { return this.hiringDate; }

    public String getRole() { return this.role; }

    public void setRole(String role)
    {
        if (role==null || role.trim().isEmpty())
            throw new IllegalArgumentException("Role cannot be null and empty.");

        this.role = role;
    }

    public double getSalary() { return salary; }

    public void setSalary(double salary)
    {
        if (salary<0) throw new IllegalArgumentException("Salary cannot be negative.");

        this.salary = salary;
    }

    public abstract void promote(String newPosition, double newSalary);

    public abstract void applyRaise(double percentage);

    public abstract int getSeniority();

    @Override
    public String toString()
    {
        return super.toString() + " Employee{" + super.toString() +
                "; registration='" + this.registration + '\'' +
                ", role='" + this.role + '\'' +
                ", salary=" + this.salary +
                ", hiringDate=" + this.hiringDate +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(this.salary, employee.salary) == 0 && Objects.equals(this.registration, employee.registration) && Objects.equals(this.role, employee.role) && Objects.equals(this.hiringDate, employee.hiringDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(super.hashCode(), this.registration, this.role, this.salary, this.hiringDate);
    }

    public Employee(Employee model)
    {
        super(model);

        this.registration = model.registration;
        this.hiringDate = model.hiringDate;
        this.role = model.role;
        this.salary = model.salary;
    }

    @Override
    public Object clone()
    {
        Employee clone = (Employee)super.clone();

        clone.role = this.role;
        clone.salary = this.salary;

        return clone;
    }
}
