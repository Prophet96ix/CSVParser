package de.aaronwagner.dev;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class CSVParserTest {

    public void testConstructor() {
        CSVParser parser = new CSVParser();
        Assert.assertNotNull(parser);
    }

    public void testConstructorWithFile() {
        File csvMock = Mockito.mock(File.class);
        CSVParser parser = new CSVParser(csvMock);
        Assert.assertNotNull(parser);
    }

}
