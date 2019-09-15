package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Anschrift;
import de.aaronwagner.dev.model.Person;
import de.aaronwagner.dev.type.Postleitzahl;
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
public class CSVPersonenParser {

    @Getter
    private ParserUtil parserUtil;
    @Getter
    private ArrayList<Person> personen;
    private ArrayList<ArrayList<Object>> parsedList;


    public CSVPersonenParser(File csvfile) {
        parserUtil = new ParserUtil(csvfile);
    }

    public void parseFile() {
        try {
            parsedList = parserUtil.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertParsedListToPersonenData() {

        personen = new ArrayList<>();

        parsedList.stream().forEach(entry -> {
            Person person = new Person();

            person.setNachname((String) entry.get(0));

            person.setVorname((String) entry.get(1));

            Anschrift anschrift = new Anschrift();
            anschrift.setStrasse((String) entry.get(2));

            Postleitzahl postleitzahl = new Postleitzahl();
            postleitzahl.setZipcode(Integer.valueOf((String) entry.get(3)));
            anschrift.setPostleitzahl(postleitzahl);
            anschrift.setStadt((String) entry.get(4));

            person.setAnschrift(anschrift);
            person.setGeburtsdatum(LocalDate.parse((CharSequence) entry.get(5), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

            personen.add(person);
        });
    }

    public List<Person> findPersonenByName(String name) {
        if (personen == null || personen.isEmpty())
            parseFile();

        List<Person> matches = personen.stream()
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
     * @return
     */
    public Integer getPersonAgeInYears(Person person, LocalDate calculationBasis) {

        LocalDate birth = person.getGeburtsdatum();

        Period period = Period.between(birth, calculationBasis);
        int diff = period.getYears();

        return Integer.valueOf(diff);
    }

}
