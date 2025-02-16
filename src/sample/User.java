package sample;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class User implements Initializable {

    @FXML
    private AnchorPane sidePanel;

    @FXML
    private ToggleButton hamburger;

    @FXML
    private Label home;

    @FXML
    private Label shows;

    @FXML
    private Label bookTicket;

    @FXML
    private Label buyMovie;

    @FXML
    private Label contactUs;

    @FXML
    private Label help;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
    @FXML
    void handleHamburger() {
        //this method only does the transition nothing else
        //infact the button hamburger only does the transition
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(sidePanel);
        if (hamburger.isSelected()){
            transition.setToX(0);
        }else{
            transition.setToX(-200);
        }
        transition.play();

    }
    @FXML
    public void handleMenuClicked(MouseEvent event){
        if (event.getSource().equals(home)){
            System.out.println("Home selected");
        } else if (event.getSource().equals(shows)){
            System.out.println("Shows selected");
        } else if (event.getSource().equals(bookTicket) || event.getSource().equals(buyMovie)){
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.initOwner(((Node)event.getSource()).getScene().getWindow());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("bookTicket.fxml"));
            try {
                dialog.setDialogPane(loader.load());
            }catch (IOException exception){
                System.out.println("exception found");
                exception.printStackTrace();
            }
            BookTicket controller = loader.getController();
            dialog.getDialogPane().lookupButton(ButtonType.OK)
                    .disableProperty().bind(controller.getIsValidBinding().not());
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get().equals(ButtonType.OK)){
                if (controller.bookTicket()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Successful!!");
                    alert.setContentText("Your booking to the show is confirmed\n" +
                            "Enjoy your movie");
                    alert.showAndWait();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText("Error!!");
                    alert.setContentText("There might be a enough booking to the schedule\n" +
                            "Please try again in another schedule");
                    alert.showAndWait();
                }
          }
        } else if (event.getSource().equals(contactUs)){
            System.out.println("Contact Us pressed");
        } else if (event.getSource().equals(help)){
            System.out.println("Help pressed");
        }

    }
}
