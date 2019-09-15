package de.aaronwagner.dev.model;

import de.aaronwagner.dev.type.Postleitzahl;
import lombok.Data;

@Data
public class Anschrift {

    private String strasse;
    private Postleitzahl postleitzahl;
    private String stadt;
}
