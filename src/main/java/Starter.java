import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.net.URL;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL resource = getClass().getResource("/view/adminLoginForm.fxml");
        assert resource != null;

        FXMLLoader loader = new FXMLLoader(resource);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Login Form");
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.show();
    }
}
