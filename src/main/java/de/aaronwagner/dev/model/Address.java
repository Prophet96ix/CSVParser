package de.aaronwagner.dev.model;

import de.aaronwagner.dev.type.ZipCode;
import lombok.Data;

@Data
public class Address {

    private String strasse;
    private ZipCode postleitzahl;
    private String stadt;
}
