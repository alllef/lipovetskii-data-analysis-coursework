package com.github.lipovetskii.data_parsing;

import java.util.Arrays;

public class DataFileSaver {

    private final String[] walkthroughType = new String[]{"Main Story", "Main + Extras", "Completionist", "All Styles"};
    private final String[] dataInformation = new String[]{"Platform", "Genre", "Developer", "Publisher", "NA", "EU", "JP", "Type"};

    String formHeader() {
        String header = "Name,";

        for (String tmp : dataInformation)
            header += tmp += ",";

        for (String tmp : walkthroughType)
            header += tmp += ",";

        return header.substring(0, header.length() - 1);
    }

    public static void main(String[] args) {
        DataFileSaver fileSaver = new DataFileSaver();
        fileSaver.formHeader();
    }

}
