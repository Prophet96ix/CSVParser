package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Person;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class PersonParserTest {

    private File csv;

    @BeforeMethod
    public void beforeMethod() {
        csv = new File(getClass().getClassLoader().getResource("MOCK_DATA.csv").getPath());
        Assert.assertNotNull(csv);
    }

    @Test
    public void testCreationOfPersonListWithRealCSV() throws IOException {
        PersonParser parser = new PersonParser(csv);
        Assert.assertNotNull(parser);
        Assert.assertTrue(parser.getParsedPersonList().size() > 0);
    }

}
