package de.aaronwagner.dev;

import java.io.File;

public class CSVParser {

    private Parser parser;

    public CSVParser() {

    }

    // weitere constructors mit mehr möglichkeiten

    public CSVParser(File csvfile) {
        parser = new Parser(csvfile);
    }

    public void parseFile() {
        parser.parse();
    }

}
