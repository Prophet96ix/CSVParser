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

public class CSVParserUtil {

    @Getter
    private final File filetoParse;

    public CSVParserUtil(File csvfile) {
        filetoParse = csvfile;
    }

    public ArrayList<ArrayList<String>> parse() throws IOException {

        Reader reader = null;
        CSVParser csvParser = null;

        reader = Files.newBufferedReader(Paths.get(filetoParse.getAbsolutePath()));

        if (reader != null) {
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        }

        ArrayList<ArrayList<String>> list = new ArrayList<>();

        // Accessing Values by Column Index
        for (CSVRecord record : csvParser) {
            ArrayList<String> data = new ArrayList<>();

            for (int i = 0; i < record.size(); i++) {
                data.add(record.get(i));
            }

            list.add(data);
        }

        return list;
    }
}
