package sample;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UserController implements Initializable {

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
            dialog.initOwner(((Node)(event.getSource())).getScene().getWindow());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("bookTicket.fxml"));

            try{
                dialog.getDialogPane().setContent(loader.load());
            }catch (Exception e){
                //don't do anything
                System.out.println("could not load the file");
                e.printStackTrace();
            }

            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get().equals(ButtonType.OK)){
                //user confirmed the booking
                System.out.println("confirmed");
            } else if (result.isPresent() && result.get().equals(ButtonType.CANCEL)){
                System.out.println("cancelled from the window");
            }
            System.out.println("hudai");
        } else if (event.getSource().equals(contactUs)){
            System.out.println("Contact Us pressed");
        } else if (event.getSource().equals(help)){
            System.out.println("Help pressed");
        }

    }
}
