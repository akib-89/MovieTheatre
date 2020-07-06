package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.data.Movie;
import sample.data.Scrapper;

public class AddMovie {
    @FXML
    private TextField searchBox;
    @FXML
    private Button searchButton;
    @FXML
    private ListView<Movie> movieList;
    @FXML
    private TextArea details;

    public void initialize(){
        searchButton.setDisable(true);
        movieList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Movie>() {
            @Override
            public void changed(ObservableValue<? extends Movie> observableValue, Movie movie, Movie newMovie) {
                String string = newMovie.getName() + "\n" +
                        newMovie.description();
                details.setText(string);
            }
        });
    }

    public Movie getMovie(){
        System.out.println("get movies called");

        if (movieList.getSelectionModel().getSelectedItem() != null){
            return movieList.getSelectionModel().getSelectedItem();
        }else {
            return null;
        }
    }

    @FXML
    public void handleSearchBox(){
        String text = searchBox.getText();
        boolean disable = text.isEmpty() | text.trim().isEmpty();
        searchButton.setDisable(disable);
    }
    @FXML
    public void handleButtonPressed(ActionEvent event){
        searchButton.setDisable(true);
        details.setText("Searching..");
        Runnable task = new Runnable() {
            @Override
            public void run() {
                ObservableList<Movie> list = Scrapper.find(searchBox.getText());

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        movieList.setItems(list);
                        searchBox.setText("");
                        details.setText("Search finished! \n" +
                                "Result shown in the list if you can't " +
                                "find what are you looking then be more specific" +
                                " with your keyword");
                    }
                });

            }
        };
        new Thread(task).start();
    }
}
