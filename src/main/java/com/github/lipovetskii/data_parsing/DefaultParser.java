package com.github.lipovetskii.data_parsing;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.tidy.Node;
import org.w3c.tidy.Tidy;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class DefaultParser {

    private File file;
    private String url;
    FileInputStream fileIS;
    DocumentBuilderFactory builderFactory;
    DocumentBuilder builder;
    Document xmlDocument;
    XPath xPath;



    public void createParsingMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter xpath query");
           String expression = scanner.nextLine();


            try {
                NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
                System.out.println(nodeList.getLength());
                for (int i = 0; i < nodeList.getLength(); i++)
                    System.out.println(nodeList.item(i).getTextContent());

            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }

        }
    }

    NodeList geNodesByExpression(String expression) {
        NodeList nodeList = null;
        try {
            nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return nodeList;
    }

    void parseByUrl(String url) throws IOException {
        this.url = url;


        BufferedReader in = new BufferedReader(
                new InputStreamReader(new URL(url).openStream()));

        String inputLine = "";
        String addLine;

        do {
            addLine = in.readLine();
            if (addLine != null) inputLine += addLine;
        }
        while (addLine != null);

        System.out.println(inputLine);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Tidy tidy = new Tidy();
        tidy.setXHTML(true);


        StringReader reader = new StringReader(inputLine);
        StringWriter writer = new StringWriter();
        Node n = tidy.parse(reader, writer);

        try {
            xmlDocument = builder.parse(new InputSource(new StringReader(writer.getBuffer().toString())));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void parseByFile(String path) throws IOException, SAXException {

        Tidy tidy = new Tidy();
        tidy.setXHTML(true);

        StringWriter writer = new StringWriter();

        Node n = tidy.parse(new FileReader(new File(path)), writer);
        //xmlDocument = tidy.parseDOM(new FileReader(new File(path)),writer);

        System.out.println(writer.getBuffer().toString());
        // xmlDocument = tidy.parseDOM(new StringReader(writer.getBuffer().toString()), secondWriter);
        xmlDocument = builder.parse(new InputSource(new StringReader(writer.getBuffer().toString())));
    }

    public DefaultParser() throws IOException, SAXException {
        builderFactory = DocumentBuilderFactory.newInstance();
        try {
            builder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        xPath = XPathFactory.newInstance().newXPath();
    }


    public static void main(String[] args) {
        DefaultParser parser = null;

        try {
            parser = new DefaultParser();
            parser.parseByUrl("https://howlongtobeat.com/#search");
            //parser.parseByFile("src/main/resources/haskelevich.txt");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        parser.createParsingMenu();


    }

}
