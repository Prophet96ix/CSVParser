package de.aaronwagner.dev;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class CSVParserTest {

    public void testConstructorWithFile() {
        File csvMock = Mockito.mock(File.class);
        CSVParser parser = new CSVParser(csvMock);
        Assert.assertNotNull(parser);
        Assert.assertNotNull(parser.getParserUtil());
    }

    public void testWithRealFile() {

        File csv = new File(getClass().getClassLoader().getResource("MOCK_DATA.csv").getPath());
        Assert.assertNotNull(csv);

        CSVParser parser = new CSVParser(csv);
        Assert.assertNotNull(parser);

        parser.parseFile();

        Assert.assertNotNull(parser.getParserUtil().getPersonen());
        Assert.assertTrue(parser.getParserUtil().getPersonen().size() > 0);

    }

}
