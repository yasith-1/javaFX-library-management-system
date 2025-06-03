package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Book;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.ServiceFactory;
import service.custom.impl.BookServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtTile;
    public JFXTextField txtCopies;
    public JFXComboBox comboCategory;
    public JFXComboBox comboStatus;
    public JFXComboBox comboAuthor;

    BookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutogenarateBookId();
        loadAllComboBoxData();
    }

    private void loadAllComboBoxData() {
        //        service.GetBookGerneMap() <-- book gerne map reference
        comboCategory.getItems().addAll(service.getBookGerneMap().keySet());
        //        service.GetBookGerneMap() <-- book author map reference
        comboAuthor.getItems().addAll(service.getAuthorMap().keySet());
        //        service.GetBookGerneMap() <-- book status map reference
        comboStatus.getItems().addAll(service.getStatusMap().keySet()
        );
    }

    private void setAutogenarateBookId() {
        txtId.setText(service.bookId());
    }

    public void addBookOnActionBtn(ActionEvent actionEvent) {

//        validating Input fields

        if (txtTile.getText().isEmpty()) {
            Notifications.create()
                    .title("Warning")
                    .text("Enter book title!")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();

            return;
        } else if (txtCopies.getText().isEmpty()) {
            Notifications.create()
                    .title("Warning")
                    .text("Enter book copies count !")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();
            return;
        } else if (comboCategory.getValue() == null) {
            Notifications.create()
                    .title("Warning")
                    .text("Select a book category !")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();
            return;
        } else if (comboStatus.getValue() == null) {
            Notifications.create()
                    .title("Warning")
                    .text("Select a book status !")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();
            return;
        } else if (comboAuthor.getValue() == null) {
            Notifications.create()
                    .title("Warning")
                    .text("Select a book author !")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();
            return;
        } else {

//            Check the book copies count is number or another format
            if (checkIsNumber(txtCopies.getText())) {

                //   ----------All data valid now---------
                Integer statusId = Integer.parseInt(service.getStatusMap().get(comboStatus.getValue()));
                String gerneId = service.getBookGerneMap().get(comboCategory.getValue());
                String authorId = service.getAuthorMap().get(comboAuthor.getValue());

                Book book = new Book(
                        txtId.getText(),
                        txtTile.getText(),
                        Integer.parseInt(txtCopies.getText()),
                        statusId,
                        gerneId,
                        authorId);

//            Book added or no into databasse ?-----------------------
                Boolean isAdded = service.addBook(book);
                if (isAdded) {
                    Notifications.create()
                            .title("Success")
                            .text("Book Added Successfully ")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT)
                            .showInformation();
                    clearField();
                    setAutogenarateBookId();
                } else {
                    Notifications.create()
                            .title("Error")
                            .text("Book doessn't Added ... ")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT)
                            .showError();
                    clearField();
                }
            } else {
                Notifications.create()
                        .title("Warning")
                        .text("Invalid Book copies count...")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showWarning();
            }
        }
    }

    private void clearField() {
        txtTile.setText("");
        txtCopies.setText("");
        comboCategory.setValue(null);
        comboStatus.setValue(null);
        comboAuthor.setValue(null);
    }

    private Boolean checkIsNumber(String value) {
        int num = Integer.parseInt(value);
        if ((value.matches("\\d+")) && num > 0) {
            return true;
        }
        return false;
    }
}
