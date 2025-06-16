package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.IssuedBook;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.ServiceFactory;
import service.custom.impl.IssuedBookServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class IssuedBookFormController implements Initializable {

    public JFXComboBox comboBook;
    public JFXComboBox comboMember;
    public JFXTextField txtQty;
    public DatePicker returnDate;
    public TableView issueBookTable;
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
        loadTable();
        loadComboBoxesData();
    }

    private void loadComboBoxesData() {
        comboBook.getItems().addAll(service.getBookMap().keySet());
        comboMember.getItems().addAll(service.getMemberMap().keySet());

    }

    public void addIssueBookOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Book !");
            return;
        } else if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a member !");
            return;
        } else if (txtQty.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add book count !");
            return;
        } else if (returnDate.getValue() == null ) {
            Alert.trigger(AlertType.WARNING, "Select return date !");
            return;
        } else if (returnDate.getValue().isBefore(LocalDate.now())) {
            Alert.trigger(AlertType.WARNING, "Select Valid return date !");
            return;
        } else {
//            All field filled , not empty
            IssuedBook issuedBook = new IssuedBook(
                    String.valueOf(service.getMemberMap().get(comboMember.getValue())),
                    String.valueOf(service.getBookMap().get(comboBook.getValue())),
                    Integer.parseInt(txtQty.getText()),
                    LocalDate.now(),
                    LocalTime.now(),
                    returnDate.getValue());

            Boolean issueBookRecordIsAdded = service.addIssueBookRecord(issuedBook);
            if (issueBookRecordIsAdded) {
                Alert.trigger(AlertType.INFORMATION, "Book Issued Successfully !");
                loadTable();
                clearDataFields();
                return;
            }
            Alert.trigger(AlertType.ERROR, "This Book already borrwed this user ...");
        }
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearDataFields();
    }

    private void clearDataFields() {
        comboBook.setValue(null);
        comboMember.setValue(null);
        returnDate.setValue(null);
    }

    private void loadTable() {
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colIssueTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        List<IssuedBook> issuedBookList = service.getIssuedBookList();

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
}
