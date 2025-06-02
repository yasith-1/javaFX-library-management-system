import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"))));
        stage.setTitle("Admin Dashboard");
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.show();
//        Parent root = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
//        Scene scene = new Scene(root);
//
//        // ✅ Add your CSS file (must be in /resources/css/application.css)
//        scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
//
//        stage.setScene(scene);
//        stage.setTitle("Admin Dashboard");
//        stage.getIcons().add(new Image(getClass().getResource("/image/stageicon.png").toExternalForm()));
//        stage.show();
    }
}
