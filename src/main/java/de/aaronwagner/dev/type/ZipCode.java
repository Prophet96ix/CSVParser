package de.aaronwagner.dev.type;

import lombok.Getter;

public class ZipCode {

    @Getter
    private String zip;

    public boolean setZip(String zipcode) {
        if (zipcode.length() == 5 && zipcode.matches("-?\\d+(\\.\\d+)?")) {
            zip = zipcode;
            return true;
        }
        return false;
    }
}
