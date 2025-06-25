package controller;

import com.jfoenix.controls.JFXTextField;
import dto.IssuedBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.ServiceFactory;
import service.custom.impl.IssuedBookServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageIssuedBookFormController implements Initializable {
    public ComboBox comboMember;
    public ComboBox comboBook;
    public JFXTextField txtSearchFieldMemberName;
    public JFXTextField txtSearchFieldBook;
    public TableView issueBookTable;
    public JFXTextField txtQty;
    public TableColumn colMemberId;
    public TableColumn colBookId;
    public TableColumn colQuantity;
    public TableColumn colIssueDate;
    public TableColumn colIssueTime;
    public TableColumn colReturnDate;

    IssuedBookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.ISSUEDBOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtQty.setText("1");
        loadComboBoxesData();
        loadTable();
        fetchTableRowData();
    }

    private void loadComboBoxesData() {
        comboBook.getItems().addAll(service.getBookMap().keySet());
        comboMember.getItems().addAll(service.getMemberMap().keySet());

    }

    public void updateIssuedBookOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Missing Book , Search OR Select from table again !");
            return;
        } else if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Missing member , Search OR Select from table again !");
            return;
        } else if (txtQty.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Missing Quantity , Search OR Select from table again !");
            return;
        } else {
            //            All field filled , not empty
            IssuedBook issuedBook = new IssuedBook(
                    String.valueOf(service.getMemberMap().get(comboMember.getValue())),
                    String.valueOf(service.getBookMap().get(comboBook.getValue())),
                    Integer.parseInt(txtQty.getText()),
                    null,
                    null,
                    null);

            Boolean issueBookRecordIsAdded = service.updateIssueBookRecord(issuedBook);
            if (issueBookRecordIsAdded) {
                Alert.trigger(AlertType.INFORMATION, "Book Updated Successfully !");
                clearDataFields();
                loadTable();
                return;
            }
            Alert.trigger(AlertType.ERROR, "Book is not updated ...");
        }
    }

    public void deleteIssuedBookOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select Book that wants to Delete !");
            return;
        } else if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select member that wants to Delete !");
            return;
        } else if (txtQty.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "missing book quantity!");
            return;
        } else {
            //            All field filled , not empty
            IssuedBook issuedBook = new IssuedBook(
                    String.valueOf(service.getMemberMap().get(comboMember.getValue())),
                    String.valueOf(service.getBookMap().get(comboBook.getValue())),
                    Integer.parseInt(txtQty.getText()),
                    null,
                    null,
                    null);

            Boolean issueBookRecordIsAdded = service.deleteIssueBookRecord(issuedBook);
            if (issueBookRecordIsAdded) {
                Alert.trigger(AlertType.INFORMATION, "Book Deleted Successfully !");
                clearDataFields();
                loadTable();
                return;
            }
            Alert.trigger(AlertType.ERROR, "Book is not deleted ...");
        }
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearDataFields();
    }

    public void searchOnActionBtn(ActionEvent actionEvent) {

        if (txtSearchFieldMemberName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Member name to search field");
            return;
        } else if (txtSearchFieldBook.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Book name to search field");
            return;
        } else {
            String memberId = service.getMemberMap().get(txtSearchFieldMemberName.getText());
            String bookId = service.getBookMap().get(txtSearchFieldBook.getText());

            IssuedBook issuedBook = new IssuedBook(
                    memberId,
                    bookId,
                    null,
                    null,
                    null,
                    null);
            IssuedBook isFoundIuedBook = service.searchIssuedBook(issuedBook);
            if (isFoundIuedBook != null) {
//        Issued Book available
                setFoundedIssuedBookData(isFoundIuedBook);
                return;
            }
//       Issued Book not found
            Notifications.create()
                    .title("Error")
                    .text("Issue Book Not found")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showError();
        }
    }

    private void setFoundedIssuedBookData(IssuedBook issuedBook) {
        comboMember.setValue((Object) issuedBook.getMemberId());
        comboBook.setValue((Object) issuedBook.getIsbn());
        txtQty.setText(String.valueOf(issuedBook.getQty()));
    }

    private void loadTable() {
        List<IssuedBook> issuedBookList = service.getIssuedBookList();
        if (issuedBookList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colIssueTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        if (service.getIssuedBookList() != null) {
            ObservableList<IssuedBook> observableArrayList = FXCollections.observableArrayList(issuedBookList);
            issueBookTable.setItems(observableArrayList);
            return;
        }

        Notifications.create()
                .title("Warning")
                .text("No records available yet !")
                .hideAfter(Duration.seconds(3))
                .position(Pos.BOTTOM_RIGHT)
                .showWarning();

    }

    private void fetchTableRowData() {
        issueBookTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                IssuedBook selectedBook = (IssuedBook) issueBookTable.getSelectionModel().getSelectedItem();
                if (selectedBook != null) {
                    setFoundedIssuedBookData(selectedBook);
                }
            }
        });
    }

    private void clearDataFields() {
        if (!comboBook.getItems().contains("Book")) {
            comboBook.getItems().add(0, "Book");
        }
        comboBook.setValue("Book");

        if (!comboMember.getItems().contains("Member")) {
            comboMember.getItems().add(0, "Member");
        }
        comboMember.setValue("Member");

        txtQty.setText("");
    }


}
