package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import service.ServiceFactory;
import service.custom.impl.MemberServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;

import java.io.IOException;

public class SignupFormController {
    public JFXTextField txtNic;
    public JFXPasswordField txtPassword;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;

    MemberServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.MEMBER);

    public void RegisterOnActionButton(ActionEvent actionEvent) throws IOException {
        if (txtName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Please Enter your Name !");
            return;
        } else if (txtNic.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Please Enter your NIC !");
            return;
        } else if (txtAddress.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Please Enter your Address !");
            return;
        } else if (txtEmail.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Please Enter your Email !");
            return;
        } else if (!txtEmail.getText().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            Alert.trigger(AlertType.WARNING, "Invalid email format!");
            return;
        } else if (txtPassword.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Please Enter Password !");
            return;
        } else {
            // Secure password hashing
            String hashedPassword = BCrypt.hashpw(txtPassword.getText(), BCrypt.gensalt());

            Member admin = new Member(
                    service.getMemberId().toString(),
                    txtName.getText().toString(),
                    txtNic.getText().toString(),
                    txtEmail.getText().toString(),
                    txtAddress.getText().toString(),
                    hashedPassword,
                    "T1");

            Boolean isAdminAdded = service.addMember(admin);
            if (isAdminAdded) {
                clearInputFields();
                Alert.trigger(AlertType.INFORMATION, "Registered Successfully !");
                redirect(actionEvent);
                return;
            }
            Alert.trigger(AlertType.ERROR, "Registration Failed try again!");
        }
    }

    private void clearInputFields() {
        txtName.setText("");
        txtNic.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
        txtPassword.setText("");
    }

    public void redirectToLoginOnActionBtn(ActionEvent actionEvent) throws IOException {
        redirect(actionEvent);
    }

    private void redirect(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminLoginForm.fxml"))));
        stage.setTitle("Login Form");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.show();
    }
}
