package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.data.Loader;

public class AdminApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @Override
    public void init() throws Exception {
        Loader.getInstance().read();
    }

    @Override
    public void stop() throws Exception {
        Loader.getInstance().write();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
