package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Anschrift;
import de.aaronwagner.dev.model.Person;
import de.aaronwagner.dev.type.Postleitzahl;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ParserUtil {

    private final File filetoParse;
    @Getter
    private ArrayList<Person> personen;

    public ParserUtil(File csvfile) {
        filetoParse = csvfile;
    }

    public void parse() {

        Reader reader = null;

        try {
            reader = Files.newBufferedReader(Paths.get(filetoParse.getAbsolutePath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        CSVParser csvParser = null;

        if (reader != null) {
            try {
                csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        personen = new ArrayList();

        // Accessing Values by Column Index
        for (CSVRecord record : csvParser) {
            Person person = new Person();
            person.setNachname(record.get(0));
            person.setVorname(record.get(1));

            Anschrift anschrift = new Anschrift();
            anschrift.setStraße(record.get(2));

            Postleitzahl postleitzahl = new Postleitzahl();
            anschrift.setPostleitzahl(postleitzahl);
            anschrift.setStadt(record.get(4));

            person.setAnschrift(anschrift);

            System.out.println(record.get(5));


            LocalDate date = LocalDate.parse(record.get(5), DateTimeFormatter.ofPattern("dd.MM.YYYY"));

            System.out.println(date);


            person.setGeburtsdatum(null);

            personen.add(person);
        }
    }
}
