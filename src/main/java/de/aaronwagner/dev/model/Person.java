package de.aaronwagner.dev.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Person {

    private String nachname;
    private String vorname;
    private LocalDate geburtsdatum;
    private Anschrift anschrift;

}
