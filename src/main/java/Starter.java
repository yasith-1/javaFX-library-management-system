import com.google.inject.Guice;
import com.google.inject.Injector;
import config.AppModule;
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
        Injector injector = Guice.createInjector(new AppModule());

        URL resource = getClass().getResource("/view/adminLoginForm.fxml");
        assert resource != null;

        FXMLLoader loader = new FXMLLoader(resource);
        loader.setControllerFactory(injector::getInstance);
        Parent root = loader.load();

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Login Form");
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.show();
    }
}
