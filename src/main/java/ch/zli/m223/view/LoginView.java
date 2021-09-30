package ch.zli.m223.view;

import ch.zli.m223.Util;
import ch.zli.m223.model.Model;
import ch.zli.m223.rest.dao.UserDAO;
import ch.zli.m223.rest.dao.impl.UserDAOImpl;
import ch.zli.m223.rest.data.User;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * @author P. Gatzka
 * @version 29.09.2021
 * Project: addressbookclient
 */
public class LoginView extends VBox {
    TextField inputUsername;
    PasswordField inputPassword;
    ProgressIndicator progressIndicator;

    public LoginView() {
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

        Label login = new Label("Login");
        login.setFont(new Font("System Bold", 22));
        vBox.getChildren().add(login);
        vBox.getChildren().add(new Label("Username"));
        inputUsername = new TextField();
        vBox.getChildren().add(inputUsername);
        vBox.getChildren().add(new Label("Password"));
        inputPassword = new PasswordField();
        vBox.getChildren().add(inputPassword);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        Button btn_login = new Button("Login");
        btn_login.setOnAction(this::actionLogin);
        hBox.getChildren().add(btn_login);
        Button btn_register = new Button("Register");
        btn_register.setOnAction(this::actionRegister);
        hBox.getChildren().add(btn_register);
        progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false);
        hBox.getChildren().add(progressIndicator);
        vBox.getChildren().add(hBox);
        anchorPane.getChildren().add(vBox);
    }

    private void actionRegister(ActionEvent actionEvent) {
        getScene().setRoot(new RegisterView());
    }

    private void actionLogin(ActionEvent actionEvent) {
        progressIndicator.setVisible(true);
        String username = inputUsername.getText();
        String password = inputPassword.getText();
        String hashedPassword = Util.hash(password);

        User user = new User(username, hashedPassword);
        UserDAO userDAO = new UserDAOImpl();
        String[] container = userDAO.login(user);
        if (container == null) {
            progressIndicator.setVisible(false);
            return;
        }
        String token = container[0];
        Long id = Long.valueOf(container[1]);
        Model.getInstance().setToken(token);
        Model.getInstance().setUser(userDAO.findById(id));
        getScene().setRoot(new MainView());
    }
}
