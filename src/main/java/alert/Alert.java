package alert;

import javafx.geometry.Pos;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class Alert {
    public static void trigger(AlertType type, String text) {
        switch (type) {
            case INFORMATION:
                Notifications.create()
                        .title("Success")
                        .text(text)
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showInformation();
                return;

            case WARNING:
                Notifications.create()
                        .title("Warning")
                        .text(text)
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showWarning();
                return;

            case ERROR:
                Notifications.create()
                        .title("Error")
                        .text(text)
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showError();
                return;
        }
    }
}
