package sample.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Loader {
    private static final Loader instance = new Loader();
    private final ObservableList<Movie> movies;
    private final ObservableList<Message> messages;
    private final String movieFile="src/files/movies.txt";
    private final String messageFile = "src/files/messages.txt";

    private Loader(){
        movies = FXCollections.observableArrayList();
        messages =FXCollections.observableArrayList();
    }
    public static Loader getInstance(){
        return instance;
    }

    public ObservableList<Movie> getMovies() {
        return movies;
    }
    public ObservableList<Message> getMessages(){
        return messages;
    }
    public void read() throws IOException {
        Path moviePath = Paths.get(movieFile);
        Path messagePath = Paths.get(messageFile);

        try(BufferedReader buffer = Files.newBufferedReader(moviePath)){
            String input;
            while((input=buffer.readLine()) != null){
                String[] items =input.split("\t");
                Movie temp = new Movie(items[0],
                        Integer.parseInt(items[1]),
                        Integer.parseInt(items[2]),
                        items[3],
                        items[4],
                        items[5],
                        items[6],
                        Double.parseDouble(items[7]));
                movies.add(temp);
            }
        }
        try(BufferedReader buffer = Files.newBufferedReader(messagePath)){
            String input;
            while((input = buffer.readLine()) != null){
                String[] item = input.split("\t");
                Message temp = new Message(item[0],
                        item[1],
                        item[2]);
                messages.add(temp);
            }
        }

    }

    public void write() throws IOException {

        Path moviePath = Paths.get(movieFile);
        Path messagePath = Paths.get(messageFile);

        try(BufferedWriter buffer = Files.newBufferedWriter(messagePath)){
            for (Message m :messages){
                buffer.write(String.format("%s\t%s\t%s",
                        m.getFrom(),
                        m.getTitle(),
                        m.getMessage()));
                buffer.newLine();
            }
        }

        try(BufferedWriter buffer = Files.newBufferedWriter(moviePath)){
            for (Movie m:movies){
                buffer.write(String.format("%s\t%d\t%d\t%s\t%s\t%s\t%s\t%f",
                        m.getName(),
                        m.getReleaseYear(),
                        m.getRunTime(),
                        m.getDescription(),
                        m.getGenre(),
                        m.getDirector(),
                        m.getStars(),
                        m.getRating()
                ));
                buffer.newLine();
            }
        }

    }

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }
}
