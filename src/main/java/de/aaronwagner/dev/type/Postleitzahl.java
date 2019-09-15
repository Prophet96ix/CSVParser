package de.aaronwagner.dev.type;

import lombok.Getter;

public class Postleitzahl {

    @Getter
    private Integer zipcode;

    public boolean setZipcode(Integer zipcode) {
        if (zipcode.toString().length() == 5) {
            this.zipcode = zipcode;
            return true;
        }
        return false;
    }

    public boolean setZipcode(String zipcode) {

        Integer zip = null;

        try {
            zip = Integer.valueOf(zipcode);
        }catch (Exception e) {
            return false;
        }

        return setZipcode(zip);
    }
}
