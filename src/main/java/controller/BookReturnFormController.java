package controller;

import com.jfoenix.controls.JFXTextField;
import dto.ReturnBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.impl.ReturnBookServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class BookReturnFormController implements Initializable {
    public TableView returnBookTable;
    public TableColumn colMemberName;
    public TableColumn colReurnDate;
    public TableColumn colBookName;
    public TableColumn colReturnTime;
    public JFXTextField txtMemberName;
    public JFXTextField txtBookName;
    public DatePicker returenedDateSelector;
    public ComboBox comboMember;
    public ComboBox comboBook;

    ReturnBookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.RETURNBOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadReturnBookTable();
        loadComboBoxData();
        fetchTableRowData();
    }

    private void loadComboBoxData() {
        comboBook.getItems().addAll(service.getBookMap().keySet());
        comboMember.getItems().addAll(service.getMemberMap().keySet());
    }

    public void addBookReturnOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select book that return !");
            return;
        } else if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select Member that return book !");
            return;
        }else if (returenedDateSelector.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select Book returning Date !");
            return;
        } else {
//            All Ok
            String memberId = service.getMemberMap().get(comboMember.getValue().toString());
            String bookId = service.getBookMap().get(comboBook.getValue().toString());

            ReturnBook returnBook = new ReturnBook(

                    memberId,
                    bookId,
                    returenedDateSelector.getValue(),
                    LocalTime.now());

            Boolean isAdded = service.addReturnRecord(returnBook);
            if (isAdded) {
                loadReturnBookTable();
                Alert.trigger(AlertType.INFORMATION, "Return Book Restocked Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Return Book Restocked Failed !");
        }
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        comboBook.setValue(null);
        comboMember.setValue(null);
        txtBookName.setText("");
        txtMemberName.setText("");
    }

    public void deleteOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select book that return !");
            return;
        } else if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select member !");
            return;
        } else {
//            All Ok
            ReturnBook returnBook = new ReturnBook(
                    service.getMemberMap().get(comboMember.getValue().toString()),
                    service.getBookMap().get(comboBook.getValue().toString()),
                    null,
                    null);

            Boolean isDeleted = service.deleteReturnRecord(returnBook);
            if (isDeleted) {
                loadReturnBookTable();
                Alert.trigger(AlertType.INFORMATION, "Return Book Deleted Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Return Book delete Failed !");
        }
    }

    public void searchOnActionBtn(ActionEvent actionEvent) {
        if (txtBookName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Book Name !");
            return;
        } else if (txtMemberName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Member Name !");
            return;
        } else {
            String bookId = service.getBookMap().get(txtBookName.getText());
            String memberId = service.getMemberMap().get(txtMemberName.getText());

            ReturnBook returnBook = new ReturnBook(memberId, bookId, null, null);
            ReturnBook FoundReturnBook = service.searchReturnRecord(returnBook);
            if (FoundReturnBook != null) {
                setReturnBookData(FoundReturnBook);
                return;
            }
            Alert.trigger(AlertType.WARNING, "Sorry Return Book not found !");
        }
    }

    private void setReturnBookData(ReturnBook returnBook) {
        comboMember.setValue(returnBook.getMemberId());
        comboBook.setValue(returnBook.getIsbn());
    }

    private void loadReturnBookTable() {
        List<ReturnBook> allReturnBookList = service.getAllReturnBookList();
        if (allReturnBookList == null){
            Alert.trigger(AlertType.WARNING,"No available data in table now !");
            return;
        }

        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colReurnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        colReturnTime.setCellValueFactory(new PropertyValueFactory<>("returnTime"));

        ObservableList<ReturnBook> observableReturnBookList = FXCollections.observableArrayList(allReturnBookList);
        returnBookTable.setItems(observableReturnBookList);
    }


    private void fetchTableRowData() {
        returnBookTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                ReturnBook selectedBook = (ReturnBook) returnBookTable.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    fillFoundedBookData(selectedBook);
                }
            }
        });
    }

    private void fillFoundedBookData(ReturnBook returnBook) {
        comboMember.setValue(returnBook.getMemberId());
        comboBook.setValue(returnBook.getIsbn());
    }
}
