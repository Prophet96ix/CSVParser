package de.aaronwagner.dev;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CSVParserUtilTest {

    private File csv;

    @BeforeMethod
    public void beforeMethod() {
        csv = new File(getClass().getClassLoader().getResource("MOCK_DATA.csv").getPath());
        Assert.assertNotNull(csv);
    }

    @Test
    public void testParse() throws IOException {
        CSVParserUtil parserUtil = new CSVParserUtil(csv);
        ArrayList<ArrayList<String>> output = parserUtil.parse();
        Assert.assertNotNull(output);
        Assert.assertTrue(output.size() > 0);
    }

    @Test
    public void testParseWithInvalidFile() throws IOException {
        File invalidFile = new File("xxx");
        Assert.assertThrows(IOException.class, () -> new CSVParserUtil(invalidFile));
    }
}
