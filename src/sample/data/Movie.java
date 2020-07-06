package sample.data;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private Image poster;
    private final String name;
    private final int releaseYear;
    private final int runTime;
    private final String description;
    private final List<String> genre;
    private final String director;
    private final List<String> stars;
    private double rating;

    public Movie(String name, int releaseYear, int runTime, String description, String genre, String director, String stars, double rating) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.runTime = runTime;
        this.description = description;
        this.rating = rating;
        String[] temp = genre.split(",");
        this.genre = new ArrayList<>();
        this.genre.addAll(Arrays.asList(temp));
        this.director = director;
        temp = stars.split(",");
        this.stars = new ArrayList<>();
        this.stars.addAll(Arrays.asList(temp));
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getRunTime() {
        return runTime;
    }

    public String getGenre() {
        StringBuilder s = new StringBuilder();
        for (String temp:genre){
            s.append(temp);
        }
        return s.toString();
    }

    public String description(){
        StringBuilder des = new StringBuilder();
        des.append("Runtime: ").append(this.runTime).append("\n");
        des.append(this.description).append("\n");
        des.append("IMDB Rating: ").append(this.rating).append("\n").append("Genre: ");
        for (String s: genre){
            des.append(s).append(", ");
        }
        des.append("\n");
        des.append("Director: ").append(this.director).append("\n").append("Stars: ");
        for (String s: stars){
            des.append(s).append(", ");
        }
        return des.toString();
    }

    public String getDirector() {
        return director;
    }

    public String getStars() {
        StringBuilder s = new StringBuilder();
        for (String star: stars){
            s.append(star).append(",");
        }
        return s.toString();
    }

    public double getRating() {
        return rating;
    }

    public Image getPoster() {
        return poster;
    }

    public void setPoster(Image poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this ==  obj){
            return true;
        }

        if (obj instanceof Movie){
            return this.name.equals(((Movie)obj).name);
        }

        return false;
    }

    @Override
    public String toString() {
        return this.name + ": (" + this.releaseYear + ")" ;
    }
}
