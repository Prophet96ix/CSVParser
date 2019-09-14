package de.aaronwagner.dev;

import lombok.Getter;

import java.io.File;

public class CSVParser {

    @Getter
    private ParserUtil parserUtil;

    public CSVParser(File csvfile) {
        parserUtil = new ParserUtil(csvfile);
    }

    public void parseFile() {
        parserUtil.parse();
    }

}
