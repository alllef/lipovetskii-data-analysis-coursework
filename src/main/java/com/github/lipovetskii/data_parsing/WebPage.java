package com.github.lipovetskii.data_parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

public abstract class WebPage {
    String link;
    protected Document doc;

    public WebPage(String link) {
        this.link = link;

        try {
            doc = Jsoup.connect(link).followRedirects(false).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
