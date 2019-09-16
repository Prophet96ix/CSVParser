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

        parser.parseFile();
        parser.convertParsedListToPersonData();

        Assert.assertNotNull(parser.getPersonList());
        Assert.assertTrue(parser.getPersonList().size() > 0);
    }

    @Test
    public void testAgeCalculation() throws IOException {
        PersonParser parser = new PersonParser(csv);
        parser.parseFile();
        parser.convertParsedListToPersonData();

        Person person = Mockito.mock(Person.class);
        LocalDate birth = LocalDate.of(1977,01,01);
        LocalDate now = LocalDate.of(2019,01,01);

        Mockito.when(person.getGeburtsdatum()).thenReturn(birth);

        Assert.assertTrue(parser.getPersonAgeInYears(person, now).compareTo(Integer.valueOf(42)) == 0);
    }

    @Test
    public void testFindPersonenByName() throws IOException {
        PersonParser parser = new PersonParser(csv);
        parser.parseFile();
        parser.convertParsedListToPersonData();

        List<Person> matches = parser.findPersonsByName("Susann");

        Assert.assertNotNull(matches);
        Assert.assertTrue(!matches.isEmpty());
        Assert.assertTrue(matches.size() == 1);

    }

}
