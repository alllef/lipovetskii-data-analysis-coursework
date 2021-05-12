package com.github.lipovetskii.data_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePage {

    private String link;
    private Document doc;

    public GamePage(String link) {
        this.link = link;

        try {
            doc = Jsoup.connect(link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Integer> getTimeToBeat = getTimeToBeat();
        Map<String, String> getAddiInformation = getAdditionalInformation();

    }

    public Map<String, Integer> getTimeToBeat() {
        Map<String, String> timesToBeat = new HashMap<>();
        Elements times = doc.select(".game_times>ul>li");

        for (Element elem : times) {
            String key = elem.select("h5").text();
            String value = elem.select("div").text();
            timesToBeat.put(key, value);
        }

        return filterTimeToBeat(timesToBeat);
    }


    private Map<String, Integer> filterTimeToBeat(Map<String, String> timeToBeat) {
        Map<String, Integer> filteredTimeToBeat = new HashMap<>();

        for (Map.Entry<String, String> pair : timeToBeat.entrySet()) {
            double minutes = Double.parseDouble(pair.getValue().split(" ")[0].replace("½", "0.5"));
            if (pair.getValue().contains("Hours")) minutes *= 60;
            filteredTimeToBeat.put(pair.getKey(), (int) minutes);
        }

        return filteredTimeToBeat;
    }

    public Map<String, String> getAdditionalInformation() {

        List<String> pluralFormKeys = List.of("Publishers", "Developers", "Genres", "Platforms");
        Map<String, String> additionalInformation = new HashMap<>();
        Elements addInfoElements = doc.select("[class=\"profile_info\"],[class=\"profile_info medium\"]");

        for (Element elem : addInfoElements) {
            String key = elem.select("strong").text().replace(":", "");
            String value = elem.text().replace(key + ": ", "");
            if (pluralFormKeys.contains(key)) additionalInformation.put(key.substring(0, key.length() - 1), value);
            else additionalInformation.put(key,value);
        }

        return additionalInformation;

    }


}
