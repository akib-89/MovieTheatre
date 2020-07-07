package com.company;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
      /*  try{
            Document doc = Jsoup.connect("https://www.imdb.com/list/ls062458414/").userAgent("mozilla/17.0").get();
            Elements temp = doc.select("h3.lister-item-header");

            int i = 1;
            for( Element movie:temp ){
                System.out.println(i+" "+movie.getElementsByTag("a").first().text());
                i++;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
       
       */
        while(true){
            try{
                System.out.println("Enter the link of Udemy course");
                Scanner scanner = new Scanner(System.in);
                String url = scanner.nextLine();
                //url = "https://www.amazon.com/TP-Link-AC1750-Smart-WiFi-Router/dp/B079JD7F7G/ref=lp_16225007011_1_9?s=computers-intl-ship&ie=UTF8&qid=1591873163&sr=1-9";
                Document doc = Jsoup.connect(url).userAgent("mozilla/17.0").get();
                URI uri = new URI(url);
                System.out.println(uri.getHost());
                // Udemy
              /*  Element name = doc.selectFirst("h1.clp-lead__title ");
                System.out.println(name.text());
               // Element author = doc.selectFirst("a.instructor-links__link");
                Element author = doc.selectFirst("a[rel='noopener noreferrer'],a.instructor-links__link");
                if( author!=null ) {
                    System.out.println(author.text());
                } else{
                    System.out.println("Unnamed");
                }
                Element temp = doc.select("span.price-text__current").first();
                if(temp!=null){
                    System.out.println(temp.text());
                } else{
                    System.out.println("Free");
                }

                Element temp2 = doc.select("span.price-text__old").first();
                if(temp2!=null){
                    System.out.println(temp2.text());
                }*/

                // Amazon

                Element name = doc.selectFirst("span[id='productTitle']");
                System.out.println(name.text());
                Element prod = doc.selectFirst("a[id='bylineInfo']");
                System.out.println(prod.text());
                Element el = doc.select("span[id='priceblock_ourprice']").first();
                System.out.println(el.text());
                Element ship = doc.selectFirst("#ourprice_shippingmessage > span:nth-child(3)");
                String wtf = ship.text();
                String[] words = wtf.split(" ");
                System.out.println(words[1]);




                break;




            } catch(IOException | URISyntaxException e){
                e.printStackTrace();
            }
        }
    }
}

/*
<span class="sr-only">Current price</span><span><span>$10.99</span></span>
 */
