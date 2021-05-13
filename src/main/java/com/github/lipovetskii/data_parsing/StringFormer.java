package com.github.lipovetskii.data_parsing;

import java.util.Map;

public class StringFormer {

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

    String formRow(GamePage page) {

        String row = page.getHeader() + ",";
        Map<String, String> additionalInfo = page.getAdditionalInformation();
        Map<String, Integer> timeToBeat = page.getTimeToBeat();

        for (String info : dataInformation) {
            row += additionalInfo.get(info);
            row += ",";
        }

        for (String walkthrough : walkthroughType) {
            row += timeToBeat.get(walkthrough);
            row += ",";
        }

        return row.substring(0, row.length() - 1).replace("null", "");
    }

    public static void main(String[] args) {
        StringFormer fileSaver = new StringFormer();
        String result = fileSaver.formRow(new GamePage("https://howlongtobeat.com/game?id=8934"));
    }

}
