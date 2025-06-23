package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class SignupFormController {
    public JFXTextField txtNic;
    public JFXPasswordField txtPassword;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;

    public void RegisterOnActionButton(ActionEvent actionEvent) {

    }

    public void redirectToLoginOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminLoginForm.fxml"))));
        stage.setTitle("Login Form");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.show();
    }
}
