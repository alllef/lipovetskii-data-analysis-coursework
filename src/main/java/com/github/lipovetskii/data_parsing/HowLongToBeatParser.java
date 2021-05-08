package com.github.lipovetskii.data_parsing;

import org.w3c.dom.NodeList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HowLongToBeatParser {
    DefaultParser defaultParser = new DefaultParser();
    final String baseURL = "https://howlongtobeat.com/";

    List<String> parseLink() {
        List<String> strings = new ArrayList<>();
        defaultParser.parseByUrl("https://howlongtobeat.com/#search");

        NodeList nodeList = defaultParser.getNodesByExpression("//@href");
        String url = nodeList.item(0).getTextContent();

        for (int i = 0; i < nodeList.getLength(); i++) {
            String tmp = nodeList.item(i).getTextContent();
            if(tmp.contains("game?")) strings.add(tmp);
        }

        return strings;
    }

    public static void main(String[] args) {
        HowLongToBeatParser parser = new HowLongToBeatParser();
        parser.parseLink();
    }
}
