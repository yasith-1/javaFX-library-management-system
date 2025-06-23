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

public class LoginFormController {
    public JFXPasswordField passwordField;
    public JFXTextField txtEmailORnic;

    MemberServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.MEMBER);

    public void LoginOnActionButton(ActionEvent actionEvent) throws IOException {
        if (txtEmailORnic.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Email OR NIC");
            return;
        } else if (passwordField.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter your Password !");
            return;
        } else {
            Member loginAdmin = new Member(
                    null,
                    null,
                    txtEmailORnic.getText().toString(),
                    txtEmailORnic.getText().toString(),
                    null,
                    null,
                    "T1");

            Member existsAdmin = service.findAdminExists(loginAdmin);
            if (existsAdmin != null) {
//                check password
                if (BCrypt.checkpw(passwordField.getText(), existsAdmin.getPassword())) {
                    // login success redirect to dashboard-------------------------------------------------------------
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashboardForm.fxml"));
                    Scene scene = new Scene(loader.load());

//                   Get controller instance
                    DashboardFormController controller = loader.getController();
                    controller.setAdminName(existsAdmin.getName());  // <-- pass name here

                    Stage stageDashboard = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stageDashboard.setScene(scene);
                    stageDashboard.setTitle("Dashboard");
                    stageDashboard.setResizable(false);
                    stageDashboard.getIcons().add(new Image("/image/stageicon.png"));
                    stageDashboard.show();
//                    -----------------------------------------------------------------------------------------------------
                    return;
                }
                Alert.trigger(AlertType.ERROR, "Incorrect Password try again !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Sorry Admin not found !");
        }
    }

    public void redirectToRegisterOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminSignupForm.fxml"))));
        stage.setTitle("Register Form");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.show();
    }
}
