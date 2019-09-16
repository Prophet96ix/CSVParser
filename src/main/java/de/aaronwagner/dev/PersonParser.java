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

    private CSVParserUtil parserUtil;
    private ArrayList<ArrayList<String>> parsedList;

    public PersonParser(File csvfile) throws IOException {
        parserUtil = new CSVParserUtil(csvfile);
        parseFile();
    }

    /**
     * parses the provided csv to a list that can be further processed
     * @throws IOException
     */
    private void parseFile() throws IOException {
        parsedList = parserUtil.parse();
    }

    /**
     * converts the parsed csv list to a Person-List that provides all the persons information
     * @return parsed ArrayList of Persons
     */
    public ArrayList<Person> getParsedPersonList() {

        ArrayList<Person> personList = new ArrayList<>();

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

        return personList;
    }
}
