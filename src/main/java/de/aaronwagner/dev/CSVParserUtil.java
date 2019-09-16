package de.aaronwagner.dev;

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

    private Reader reader;

    public CSVParserUtil(File csvFile) throws IOException {
        reader = Files.newBufferedReader(Paths.get(csvFile.getAbsolutePath()));
    }

    public ArrayList<ArrayList<String>> parse() throws IOException {

        ArrayList<ArrayList<String>> list = new ArrayList<>();
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);

        // accessing values by column index
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
