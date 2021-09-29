package ch.zli.m223;

import ch.zli.m223.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class AddressbookClient extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new LoginView()));
        stage.setTitle("Addressbook Client 1.0");
        stage.show();
    }
}
