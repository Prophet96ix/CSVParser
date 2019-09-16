package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Person;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonInformationProvider {

    private ArrayList<Person> personList;

    public PersonInformationProvider(ArrayList<Person> personList) {
        if (personList == null || personList.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.personList = personList;
    }

    /**
     * searches all the persons by their name
     * @param name the name of a person
     * @return a list of possible matches
     */
    public List<Person> findPersonsByName(String name) {
        return personList.stream()
                .filter(person -> new StringBuffer()
                        .append(person.getVorname())
                        .append(" ")
                        .append(person.getNachname())
                        .toString()
                        .contains(name))
                .collect(Collectors.toList());
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

    /**
     * Calculates a persons age
     *
     * @param person the person whose age should be calculated
     * @return age in years
     */
    public Integer getPersonAgeInYears(Person person) {
        return getPersonAgeInYears(person, LocalDate.now());
    }
}


