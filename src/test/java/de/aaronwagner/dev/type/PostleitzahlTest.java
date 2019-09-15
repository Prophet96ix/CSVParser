package de.aaronwagner.dev.type;

import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class PostleitzahlTest {

    String validZipCode = "45663";
    String invalidZipCode1 = "";
    String invalidZipCode2 = "4566";
    String invalidZipCode3 = "ESSEN";

    public void testValidZipCode() {
        Postleitzahl postleitzahl = new Postleitzahl();
        Assert.assertTrue(postleitzahl.setZipcode(validZipCode));
    }

    public void testInvalidEmptyZipCode() {
        Postleitzahl postleitzahl = new Postleitzahl();
        Assert.assertFalse(postleitzahl.setZipcode(invalidZipCode1));
    }

    public void testInvalidShortZipCode() {
        Postleitzahl postleitzahl = new Postleitzahl();
        Assert.assertFalse(postleitzahl.setZipcode(invalidZipCode2));
    }

    public void testInvalidAlphanumericZipCode() {
        Postleitzahl postleitzahl = new Postleitzahl();
        Assert.assertFalse(postleitzahl.setZipcode(invalidZipCode3));
    }

}
