package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.ServiceFactory;
import service.custom.impl.BookServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookFormController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtTile;
    public JFXTextField txtCopies;
    public JFXComboBox comboCategory;
    public JFXComboBox comboStatus;
    public JFXComboBox comboAuthor;
    public TableColumn colIsbn;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colCategory;
    public TableColumn colCopies;
    public TableColumn colStatus;
    public TableView bookTable;

    BookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutogenarateBookId();
        loadAllComboBoxData();
        loadBookTable();
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

//        validating Input fields------------------

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
                String statusId = service.getStatusMap().get(comboStatus.getValue());
                String gerneId = service.getBookGerneMap().get(comboCategory.getValue());
                String authorId = service.getAuthorMap().get(comboAuthor.getValue());

                Book book = new Book(
                        txtId.getText(),
                        txtTile.getText(),
                        Integer.parseInt(txtCopies.getText()),
                        statusId,
                        gerneId,
                        authorId);

//            Book added or no into database ?-----------------------
                Boolean isAdded = service.addBook(book);
                if (isAdded) {
//                    Book added successfully ...........
                    Notifications.create()
                            .title("Success")
                            .text("Book Added Successfully ")
                            .hideAfter(Duration.seconds(3))
                            .position(Pos.BOTTOM_RIGHT)
                            .showInformation();
                    clearField();
                    setAutogenarateBookId();
                    loadBookTable();
                } else {
//                    Book is not added  ...........
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

    //    check value is a number or no ?-------------
    private Boolean checkIsNumber(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        // Check if the value is only digits and greater than 0
        if (value.matches("\\d+")) {
            int num = Integer.parseInt(value);
            return num > 0;
        }

        // If it contains letters, symbols, or spaces, return false
        return false;
    }

    private void loadBookTable() {
        bookTable.getItems().clear();
        List<Book> bookList = service.getBookList();

        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("gerneId"));
        colCopies.setCellValueFactory(new PropertyValueFactory<>("copies"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusId"));

        ObservableList<Book> observableList = FXCollections.observableArrayList(bookList);
        bookTable.setItems(observableList);

    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearField();
    }
}
