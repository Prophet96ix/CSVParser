package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Person;
import lombok.Getter;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
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
     * @param person the person who's age should be calculated
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
