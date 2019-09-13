package de.aaronwagner.dev;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    private final File filetoParse;

    public Parser(File csvfile) {
        filetoParse = csvfile;
    }

    public void parse() {

        try (
                Reader reader = Files.newBufferedReader(Paths.get(filetoParse.getAbsolutePath()));
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        ) {
            for (CSVRecord csvRecord : csvParser) {
                // Accessing Values by Column Index
                String name = csvRecord.get(0);
                String email = csvRecord.get(1);

                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("---------------");
                System.out.println("Name : " + name);
                System.out.println("Email : " + email);
                System.out.println("---------------\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
