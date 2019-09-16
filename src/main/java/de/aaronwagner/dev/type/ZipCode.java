package de.aaronwagner.dev.type;

import lombok.Getter;

public class ZipCode {

    @Getter
    private String zip;

    public boolean setZip(String zipCode) {
        if (zipCode.length() == 5 && zipCode.matches("-?\\d+(\\.\\d+)?")) {
            zip = zipCode;
            return true;
        }
        return false;
    }
}
