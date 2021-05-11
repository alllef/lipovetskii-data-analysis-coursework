package com.github.lipovetskii.data_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
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
        Map<String, String> getTimeToBeat = getTimeToBeat();
        Map<String, String> getAddiInformation = getAdditionalInformation();

    }

    public Map<String, String> getTimeToBeat() {
        Map<String, String> timesToBeat = new HashMap<>();
        Elements times = doc.select(".game_times>ul>li");

        for (Element elem : times) {
            String key = elem.select("h5").text();
            String value = elem.select("div").text();
            timesToBeat.put(key, value);
        }

        return timesToBeat;
    }

    public Map<String, String> getAdditionalInformation() {

        Map<String, String> additionalInformation = new HashMap<>();
        Elements addInfoElements = doc.select("[class=\"profile_info\"],[class=\"profile_info medium\"]");

        for (Element elem : addInfoElements) {
            String key = elem.select("strong").text();
            String value = elem.text();
            additionalInformation.put(key, value);
        }

        return additionalInformation;

    }

}
