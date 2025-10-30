package com.gaf.dev.Library.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "Reader", schema = "Lib")
public class Reader extends Person implements Cloneable
{
    @Column(name = "registration_number", length = 10, nullable = false, unique = true)
    private String registrationNumber;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "telephone_id", referencedColumnName = "id", nullable = false)
    private Telephone telephone;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Setter(AccessLevel.NONE)
    @JoinColumn(name = "email_id", referencedColumnName = "id", nullable = false)
    private Email email;

    public Reader(String registrationNumber, String name, CPF cpf, LocalDate dateBirth, Telephone telephone, Email email, char gender)
    {
        super(name, cpf, dateBirth, gender);
        this.registrationNumber = registrationNumber;
        this.telephone = telephone;
        this.email = email;
    }

    public void setTelephone(String telephone)
    {
        if (Telephone.isPhone(telephone))
            this.telephone.setValue(telephone);
    }

    public void setEmail(String email)
    {
        if (Email.isEmail(email))
            this.email.setValue(email);
    }

    public Reader(Reader model)
    {
        super(model);

        this.registrationNumber = model.registrationNumber;
        this.telephone = model.telephone != null ? new Telephone(model.telephone.getValue()) : null;
        this.email  = model.email  != null ? new Email(model.email.getValue())  : null;
    }

    @Override
    public Object clone()
    {
        Reader reader=null;
        try
        {
            reader = new Reader(this);
        } catch (RuntimeException e)
        {
            throw new AssertionError("Unexpected error during clone: " + e.getMessage(), e);
        }

        return reader;
    }
}
