package de.aaronwagner.dev.model;

import de.aaronwagner.dev.type.Postleitzahl;
import lombok.Data;

@Data
public class Anschrift {

    private String straße;
    // plz als eigenen typ
    private Postleitzahl postleitzahl;
    private String stadt;
}
