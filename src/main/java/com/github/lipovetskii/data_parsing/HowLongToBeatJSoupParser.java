package com.github.lipovetskii.data_parsing;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class HowLongToBeatJSoupParser {

    void getInformation(int number) {
        Document doc = null;

        try {
            doc = Jsoup.connect("https://howlongtobeat.com/game?id=" + number).followRedirects(false).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void parseHowLongToBeat() {
        FileSaver.saveString(StringFormer.formHeader());
        for (int i = 1; i < 80000; i++) {
            System.out.println(i);
            GamePage page  = new GamePage("https://howlongtobeat.com/game?id=" + i);
           if (page.isSinglePlayerPage())FileSaver.saveString(StringFormer.formRow(page));
        }

    }


    public static void main(String[] args) {
        HowLongToBeatJSoupParser jSoupTest = new HowLongToBeatJSoupParser();
        jSoupTest.parseHowLongToBeat();
    }
}
