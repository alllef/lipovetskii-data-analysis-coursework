package com.github.lipovetskii.data_parsing;

import com.sun.tools.javac.Main;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchPage extends WebPage {

    public SearchPage(String link) {
        super(link);
    }

    public void parseElements() {
        Elements elements = doc.select("[class=\"search_list_details\"]");
        doc.select(".search_list_details").forEach(this::parseElement);
    }

    private void parseElement(Element shortGameCard) {
        System.out.println("wtf");
        int i=1;
        if (shortGameCard.select(".search_list_details_block").text().startsWith(" Main Story")) {
            System.out.println(i++);
            doc.select("a[href^=game?id]").forEach((elem) -> new GamePage("https://howlongtobeat.com/" + shortGameCard.attr("href")));
        }
        else {
            System.out.println("fist");
        }
    }

    public static void main(String[] args) {
        SearchPage page = new SearchPage("https://howlongtobeat.com/#search23");
        page.parseElements();
    }

}
