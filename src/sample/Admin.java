package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import sample.data.Loader;
import sample.data.Message;
import sample.data.Movie;

import java.io.IOException;
import java.util.Optional;

public class Admin {

    @FXML
    private Button addMovie;

    @FXML
    private Button addStock;

    @FXML
    private ListView<Movie> movieList;

    @FXML
    private ListView<Message> messageList;

    @FXML
    private Label title;

    @FXML
    private TextArea movieDescription;

    @FXML
    private TextArea messageDescription;

    public void initialize(){

        messageList.setItems(Loader.getInstance().getMessages());
        movieList.setItems(Loader.getInstance().getMovies());

        movieList.getSelectionModel().selectedItemProperty().addListener((observableValue, oleMovie, newMovie) ->
                updateMovie(newMovie));
        messageList.getSelectionModel().selectedItemProperty().addListener((observableValue, message, newMessage) ->
                updateMessage(newMessage));
        messageList.getSelectionModel().selectFirst();
        movieList.getSelectionModel().selectFirst();
    }

    private void updateMessage(Message message) {
        messageDescription.setText("From: " + message.getFrom() + "\n" +
                message.getTitle() + "\n" +
                message.getMessage());
    }

    private void updateMovie(Movie movie){
        title.setText(movie.getName());
        movieDescription.setText(movie.description());
    }

    @FXML
    void handleToolbar(ActionEvent event) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(((Node)event.getSource()).getScene().getWindow());
        FXMLLoader loader = new FXMLLoader();
        if (event.getSource().equals(addMovie)){
            loader.setLocation(getClass().getResource("addmovie.fxml"));
            try {
                dialog.setDialogPane(loader.load());
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get().equals(ButtonType.OK)) {
                AddMovie controller = loader.getController();
                Movie m = controller.getMovie();
                if (m != null) {
                    Loader.getInstance().addMovie(m);
                }
            }
        }else if (event.getSource().equals(addStock)){

        }
    }

}