package com.github.lipovetskii.data_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HowLongToBeatJSoupParser {

    void getInformation(int number) {
        Document doc = null;

        try {
            doc = Jsoup.connect("https://howlongtobeat.com/#search"+number).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        doc.select("a[href^=game?id]").forEach((element) -> new GamePage("https://howlongtobeat.com/"+element.attr("href")));
    }



    void testGamePage(String link) {
        Document doc = null;

        try {
            doc = Jsoup.connect("https://howlongtobeat.com/" + link).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.select(".game_times>ul>li");

    }

    void get1PercentInformation(){

        for (int i=1;i<20;i++ ){
getInformation(i);
        }
    }

    public static void main(String[] args) {
        HowLongToBeatJSoupParser jSoupTest = new HowLongToBeatJSoupParser();
        jSoupTest.get1PercentInformation();

    }
}
