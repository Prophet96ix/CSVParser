package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Person;
import lombok.Getter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CSVParser {

    @Getter
    private ParserUtil parserUtil;
    private ArrayList<Person> personen;

    public CSVParser(File csvfile) {
        parserUtil = new ParserUtil(csvfile);
    }

    public void parseFile() {
        parserUtil.parse();

        // TODO parse sollte eine arrylist zurückgeben die generisch ist und es dann zu personen konvertiert wird

        personen = parserUtil.getPersonen();
    }

    public List<Person> findPersonenByName(String name) {
        if (personen == null || personen.isEmpty())
            parseFile();

        List<Person> matches = personen.stream()
                .filter(person -> new String(person.getVorname() + " " + person.getNachname()).contains(name))
                .collect(Collectors.toList());

        return matches;
    }

    public Integer getPersonAge(Person person) {


        return null;
    }

}
