package de.aaronwagner.dev;

import java.io.File;

public class CSVParser {

    public CSVParser() {

    }

    // weitere constructors mit mehr möglichkeiten

    public CSVParser(File csvfile) {

        Parser parser = new Parser(csvfile);
        parser.parse();

    }
}
