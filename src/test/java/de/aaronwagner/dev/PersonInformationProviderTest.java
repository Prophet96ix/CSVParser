package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Person;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonInformationProviderTest {

    private File csv;

    @BeforeMethod
    public void beforeMethod() {
        csv = new File(getClass().getClassLoader().getResource("MOCK_DATA.csv").getPath());
        Assert.assertNotNull(csv);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIllegalArgumentNull() {
        PersonInformationProvider personInformationProvider = new PersonInformationProvider(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testIllegalArgumentEmpty() {
        PersonInformationProvider personInformationProvider = new PersonInformationProvider(new ArrayList<>());
    }

    @Test
    public void testAgeCalculation() throws IOException {
        PersonParser parser = new PersonParser(csv);
        ArrayList<Person> personList = Mockito.mock(ArrayList.class);
        Mockito.when(personList.isEmpty()).thenReturn(false);
        PersonInformationProvider personInformationProvider = new PersonInformationProvider(personList);
        Person person = Mockito.mock(Person.class);
        LocalDate birth = LocalDate.of(1977,01,01);
        LocalDate now = LocalDate.of(2019,01,01);

        Mockito.when(person.getGeburtsdatum()).thenReturn(birth);

        Assert.assertTrue(personInformationProvider.getPersonAgeInYears(person, now).compareTo(Integer.valueOf(42)) == 0);
    }

    @Test
    public void testFindPersonenByName() throws IOException {
        PersonParser parser = new PersonParser(csv);
        PersonInformationProvider personInformationProvider = new PersonInformationProvider(parser.getParsedPersonList());

        List<Person> matches = personInformationProvider.findPersonsByName("Susann");

        Assert.assertNotNull(matches);
        Assert.assertTrue(!matches.isEmpty());
        Assert.assertTrue(matches.size() == 1);
    }
}
