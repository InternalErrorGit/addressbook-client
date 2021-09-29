package ch.zli.m223.view;

import ch.zli.m223.Util;
import ch.zli.m223.rest.dao.UserDAO;
import ch.zli.m223.rest.dao.impl.UserDAOImpl;
import ch.zli.m223.rest.data.User;
import com.ja.security.PasswordHash;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Objects;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class RegisterView extends VBox {
    TextField inputUsername;
    PasswordField inputPassword;
    PasswordField inputRepeatPassword;
    ProgressIndicator progressIndicator;

    public RegisterView() {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(960, 580);
        getChildren().add(anchorPane);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 15, 15, 15));
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setLayoutX(355);
        vBox.setLayoutY(158);
        vBox.setPrefWidth(250);
        vBox.setSpacing(5);
        vBox.setStyle("-fx-border-color: #000000; -fx-background-color: #DDDDDD");

        Label login = new Label("Register");
        login.setFont(new Font("System Bold", 22));
        vBox.getChildren().add(login);
        vBox.getChildren().add(new Label("Username"));
        inputUsername = new TextField();
        vBox.getChildren().add(inputUsername);
        vBox.getChildren().add(new Label("Password"));
        inputPassword = new PasswordField();
        vBox.getChildren().add(inputPassword);
        vBox.getChildren().add(new Label("Repeat Password"));
        inputRepeatPassword = new PasswordField();
        vBox.getChildren().add(inputRepeatPassword);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        Button btn_register = new Button("Register");
        hBox.getChildren().add(btn_register);
        btn_register.setOnAction(this::actionRegister);
        Button btn_login = new Button("Login");
        btn_login.setOnAction(this::actionLogin);
        hBox.getChildren().add(btn_login);
        progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false);
        hBox.getChildren().add(progressIndicator);
        vBox.getChildren().add(hBox);
        anchorPane.getChildren().add(vBox);
    }

    private void actionRegister(ActionEvent actionEvent) {
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        String repeatPassword = inputRepeatPassword.getText();

        try {
            progressIndicator.setVisible(true);
            String hashedPassword = Util.hash(password);
            String hashedRepeatPassword = Util.hash(repeatPassword);


            if (!Objects.equals(hashedRepeatPassword, hashedPassword))
                return;
            User user = new User();
            user.setUsername(username);
            user.setPassword(hashedPassword);
            UserDAO userDAO = new UserDAOImpl();
            userDAO.create(user);
            actionLogin(actionEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actionLogin(ActionEvent actionEvent) {
        getScene().setRoot(new LoginView());
    }
}
