package de.aaronwagner.dev.model;

import lombok.Data;

import java.util.Date;

@Data
public class Person {

    private String nachname;
    private String vorname;
    private Date geburtsdatum;
    private Anschrift anschriftModel;

}
