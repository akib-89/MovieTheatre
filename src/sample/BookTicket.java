package sample;

import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import sample.data.Booking;
import sample.data.Loader;
import sample.data.Movie;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BookTicket {
    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox<Movie> movie;
    @FXML
    private ComboBox<String> hall;
    @FXML
    private ComboBox<String> time;
    @FXML
    private Spinner<Integer> amount;

    @FXML
    private DatePicker datePicker;

    private BooleanBinding isValid;

    public BooleanBinding getIsValidBinding(){
        return isValid;
    }

    public final boolean getIsValid(){
        return isValid.get();
    }

    public void initialize(){
        movie.getItems().addAll(Loader.getInstance().getMovies());


        isValid = new BooleanBinding() {
            {
                bind(nameTextField.textProperty(),
                        movie.getSelectionModel().selectedItemProperty(),
                        hall.getSelectionModel().selectedItemProperty(),
                        time.getSelectionModel().selectedItemProperty(),
                        datePicker.valueProperty());
            }
            @Override
            protected boolean computeValue() {
                return !nameTextField.getText().trim().isEmpty()
                        && movie.getSelectionModel().getSelectedItem() != null
                        && hall.getSelectionModel().getSelectedItem() != null
                        && time.getSelectionModel().getSelectedItem() != null
                        && datePicker.getValue() != null;
            }
        };
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker datePicker) {
                return new DateCell(){
                    @Override
                    public void updateItem(LocalDate localDate, boolean b) {
                        super.updateItem(localDate, b);
                        LocalDate today = LocalDate.now();
                        setDisable(b || localDate.compareTo(today) < 0);
                    }
                };
            }
        });
    }

    public boolean bookTicket(){
        String name = nameTextField.getText();
        int number = amount.getValue();
        Movie movie = this.movie.getSelectionModel().getSelectedItem();

        LocalDate date = datePicker.getValue();
        String timeString = time.getSelectionModel().getSelectedItem().toLowerCase();
        LocalTime time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("hh:mm a"));

        Booking booking = new Booking(name,number,time.atDate(date),movie,hall.getSelectionModel().getSelectedItem());
        if (booking.book()){
            Loader.getInstance().addBooking(booking);
            return true;
        }else{
            return false;
        }

    }


}
