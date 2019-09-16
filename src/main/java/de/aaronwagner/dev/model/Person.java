package de.aaronwagner.dev.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Person {

    private String nachname;
    private String vorname;
    private LocalDate geburtsdatum;
    private Address anschrift;

}
