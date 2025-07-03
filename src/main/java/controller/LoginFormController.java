package controller;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import config.AppModule;
import dto.Member;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import service.custom.MemberService;
import alert.Alert;
import alert.AlertType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {
    public JFXPasswordField passwordField;
    public JFXTextField txtEmailORnic;

    private Injector injector;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        injector = Guice.createInjector(new AppModule());
    }

    //    MemberServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.MEMBER);
    @Inject
    MemberService service;

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

        URL resource = this.getClass().getResource("/view/adminSignupForm.fxml");

        assert resource != null;

        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory(injector::getInstance);
        Parent load = loader.load();

        stage.setScene(new Scene(load));
        stage.setTitle("Register Form");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.show();
    }
}
