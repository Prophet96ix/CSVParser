package de.aaronwagner.dev;

import de.aaronwagner.dev.model.Person;
import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ParserUtil {

    private final File filetoParse;

    public ParserUtil(File csvfile) {
        filetoParse = csvfile;
    }

    public ArrayList<ArrayList<Object>> parse() {

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

        ArrayList<ArrayList<Object>> list = new ArrayList<>();

        // Accessing Values by Column Index
        for (CSVRecord record : csvParser) {
            ArrayList<Object> data = new ArrayList<>();

            for (int i = 0; i < record.size(); i++) {
                data.add(record.get(i));
            }

            list.add(data);
        }

        return list;
    }
}
