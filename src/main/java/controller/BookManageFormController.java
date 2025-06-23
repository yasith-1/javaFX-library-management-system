package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.impl.BookServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookManageFormController implements Initializable {
    public ComboBox comboCategory;
    public ComboBox comboStatus;
    public ComboBox comboAuthor;
    public JFXTextField txtTile;
    public JFXTextField txtCopies;
    public TableView bookTable;
    public JFXTextField txtSearchField;
    public TableColumn colIsbn;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colCategory;
    public TableColumn colCopies;
    public TableColumn colStatus;
    public Label txtIdLbl;

    BookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadAllComboBoxData();
        loadBookTable();
        fetchTableRowData();
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

    public void updateBookOnActionBtn(ActionEvent actionEvent) {
        //        validating Input fields------------------

        if (txtTile.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter book title !");
            return;
        } else if (txtCopies.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter book copies count !");
            return;
        } else if (comboCategory.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a book category !");
            return;
        } else if (comboStatus.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a book status !");
            return;
        } else if (comboAuthor.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a book author !");
            return;
        } else {

//            Check the book copies count is number or another format
            if (checkIsNumber(txtCopies.getText())) {

                //   ----------All data valid now---------
                String statusId = service.getStatusMap().get(comboStatus.getValue());
                String gerneId = service.getBookGerneMap().get(comboCategory.getValue());
                String authorId = service.getAuthorMap().get(comboAuthor.getValue());

                Book book = new Book(
                        txtIdLbl.getText(),
                        txtTile.getText(),
                        Integer.parseInt(txtCopies.getText()),
                        statusId,
                        gerneId,
                        authorId);

                Boolean isUpdated = service.updateBook(book);
//            Book Update or no into database ?----------------
                if (isUpdated) {
//                    Book added successfully ...........
                    Alert.trigger(AlertType.INFORMATION, "Book Updated Successfully !");
                    clearField();
                    loadBookTable();
                    return;
                }
                //                    Book is not added  ...........
                Alert.trigger(AlertType.ERROR, "Book doessn't Update ... ");
                clearField();
            } else {
                Alert.trigger(AlertType.WARNING, "Invalid Book copies count...");
            }
        }
    }

    public void deleteBookOnActionBtn(ActionEvent actionEvent) {
        if (txtIdLbl.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Book ID must need to continue this operation ...");
            return;
        }

        Book book = new Book(
                txtIdLbl.getText(),
                null,
                null,
                null,
                null,
                null);

        Boolean isDelete = service.deleteBook(book);
//      Check Book is delete or no------------------------
        if (isDelete) {
            clearField();
            loadBookTable();
            Alert.trigger(AlertType.INFORMATION, "Book deleted successfully !");
            return;
        }
        Alert.trigger(AlertType.ERROR, "Book is not deleted , try again ...");
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearField();
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

    public void searchOnActionBtn(ActionEvent actionEvent) {
        if (txtSearchField.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Fill the search field first...");
            return;
        }

        Book book = new Book(
                txtIdLbl.getText(),
                null,
                null,
                null,
                null,
                null);

        Book result = service.searchByBook(book);
        if (result != null) {
            fillFoundedBookData(book);
            txtSearchField.setText("");
            return;
        }
        Alert.trigger(AlertType.ERROR, "Sorry Book not found try again !");
    }

    private void loadBookTable() {
        List<Book> bookList = service.getBookList();
        if (bookList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }

        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("gerneId"));
        colCopies.setCellValueFactory(new PropertyValueFactory<>("copies"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusId"));

        ObservableList<Book> observableList = FXCollections.observableArrayList(bookList);
        bookTable.setItems(observableList);
    }

    private void fetchTableRowData() {
        bookTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                Book selectedBook = (Book) bookTable.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    fillFoundedBookData(selectedBook);
                }
            }
        });
    }

    private void fillFoundedBookData(Book book) {
        txtIdLbl.setText(book.getIsbn());
        txtTile.setText(book.getTitle());
        txtCopies.setText(String.valueOf(book.getCopies()));
        comboCategory.setValue((Object) (book.getGerneId()));
        comboStatus.setValue((Object) book.getStatusId());
        comboAuthor.setValue((Object) book.getAuthorId());
    }
}
