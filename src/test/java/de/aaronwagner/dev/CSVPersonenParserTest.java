package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Person;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;

public class CSVPersonenParserTest {

    private File csv;

    @BeforeMethod
    public void beforeMethod() {
        csv = new File(getClass().getClassLoader().getResource("MOCK_DATA.csv").getPath());
        Assert.assertNotNull(csv);
    }

    @Test
    public void testConstructorWithFile() {
        File csvMock = Mockito.mock(File.class);
        CSVPersonenParser parser = new CSVPersonenParser(csvMock);
        Assert.assertNotNull(parser);
        Assert.assertNotNull(parser.getParserUtil());
    }

    @Test
    public void testWithRealFile() {
        CSVPersonenParser parser = new CSVPersonenParser(csv);
        Assert.assertNotNull(parser);

        parser.parseFile();
        parser.convertParsedListToPersonenData();

        Assert.assertNotNull(parser.getPersonen());
        Assert.assertTrue(parser.getPersonen().size() > 0);

    }

    // TODO Negativtest
    @Test
    public void testAgeCalculation() {
        CSVPersonenParser parser = new CSVPersonenParser(csv);
        parser.parseFile();
        parser.convertParsedListToPersonenData();

        Person person = Mockito.mock(Person.class);
        LocalDate birth = LocalDate.of(1977,01,01);
        LocalDate now = LocalDate.of(2019,01,01);

        Mockito.when(person.getGeburtsdatum()).thenReturn(birth);

        Assert.assertTrue(parser.getPersonAgeInYears(person, now).compareTo(Integer.valueOf(42)) == 0);

    }

}
