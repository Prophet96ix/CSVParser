package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Address;
import de.aaronwagner.dev.model.Person;
import de.aaronwagner.dev.type.ZipCode;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CSVParser
 * <p>
 * parses a csv file in the format of
 * Nachname,Vorname,Straße,Postleitzahl,Stadt,Geburtsdatum
 */
public class PersonParser {

    @Getter
    private CSVParserUtil parserUtil;
    @Getter
    private ArrayList<Person> personList;
    private ArrayList<ArrayList<String>> parsedList;

    public PersonParser(File csvfile) {
        parserUtil = new CSVParserUtil(csvfile);
    }

    /**
     * parses the provided csv to a list that can be further processed
     * @throws IOException
     */
    public void parseFile() throws IOException {
        parsedList = parserUtil.parse();
    }

    /**
     * converts the parsed csv list to a Person-List that provides all the persons information
     */
    public void convertParsedListToPersonData() {

        personList = new ArrayList<>();

        parsedList.stream().forEach(entry -> {
            Person person = new Person();

            person.setNachname(entry.get(0));
            person.setVorname(entry.get(1));

            Address anschrift = new Address();
            anschrift.setStrasse(entry.get(2));

            ZipCode postleitzahl = new ZipCode();

            if (!postleitzahl.setZip(entry.get(3))) {
                postleitzahl.setZip("00000");
            }

            anschrift.setPostleitzahl(postleitzahl);
            anschrift.setStadt(entry.get(4));

            person.setAnschrift(anschrift);
            person.setGeburtsdatum(LocalDate.parse(entry.get(5), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

            personList.add(person);
        });
    }

    /**
     * searches all the persons by their name
     * @param name the name of a person
     * @return a list of possible matches
     */
    public List<Person> findPersonsByName(String name) {
        if (personList == null || personList.isEmpty()) {
            try {
                parseFile();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }

        List<Person> matches = personList.stream()
                .filter(person -> new StringBuffer()
                        .append(person.getVorname())
                        .append(" ")
                        .append(person.getNachname())
                        .toString()
                        .contains(name))
                .collect(Collectors.toList());

        return matches;
    }

    /**
     * Calculates a persons age
     *
     * @param person           the person whose age should be calculated
     * @param calculationBasis the time from which the calculation should be started, for example now or 10 years ago
     * @return age in years
     */
    public Integer getPersonAgeInYears(Person person, LocalDate calculationBasis) {

        LocalDate birth = person.getGeburtsdatum();

        Period period = Period.between(birth, calculationBasis);
        int diff = period.getYears();

        return Integer.valueOf(diff);
    }

}
