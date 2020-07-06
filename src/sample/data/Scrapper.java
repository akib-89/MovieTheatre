package sample.data;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Scrapper {
    public static ObservableList<Movie> find(String string){
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        string.replace(' ','+');
        StringBuilder url =new StringBuilder("http://www.imdb.com/search/title/?title=");
        url.append(string);
        url.append("&title_type=feature");
        try {
            Document doc = Jsoup.connect(url.toString()).userAgent("mozilla/17.0").get();
            Elements elements = doc.select("#main > div > div.lister.list.detail.sub-list > div > div");
            for (Element element : elements){
                int counter = 0;
                String name = element.select("div.lister-item-content > h3 > a").text();
                String str = element.select("div.lister-item-content > h3 > span.lister-item-year.text-muted.unbold").text();
                String s;
                if (!str.isEmpty() && str.length() >= 6){
                    s = str.substring(str.length()-5,str.length()-1);
                }else{
                    s="-1";
                }
                int release = Integer.parseInt(s);
                str = element.select(" div.lister-item-content > p:nth-child(2) > span.runtime").text();
                if (!str.isEmpty()){
                    s = str.substring(0,str.length()-4);
                }else{
                    s="-1";
                }
                int runtime = Integer.parseInt(s);
                str = "div.lister-item-content > p:nth-child(2) > span.genre";
                String genre = element.select(str).text();
                if (genre.isEmpty()){
                    genre="No Genre present";
                }
                str = element.select("div.lister-item-content > div > div.inline-block.ratings-imdb-rating > strong").text();
                if (!str.isEmpty()){
                    s=str;
                }else{
                    s="-1";
                    counter++;
                }
                double rating = Double.parseDouble(s);
                str = "div.lister-item-content > p:nth-child("+ (4-counter) +")";
                String description = element.select(str).text();
                if (description.isEmpty()){
                    description="No Description present";
                }
                String director;
                StringBuilder stars = new StringBuilder();
                str = "div.lister-item-content > p:nth-child("+(5-counter)+") > a";
                Elements faculty = element.select(str);
                if (faculty.isEmpty()){
//                    System.out.println("faculty fucked");
                    director= "Director info not available in imdb";
                    stars.append("Director info not available in imdb");
                }else{
                    director= faculty.first().text();
                    faculty.remove(faculty.first());
                    for (Element star : faculty){
                        stars.append(star.text()).append(", ");
                    }
                }

                Movie temp = new Movie(name,release,runtime,description,genre,director,stars.toString(),rating);
                movies.add(temp);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return movies;
    }
}
