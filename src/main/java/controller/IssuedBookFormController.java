package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.IssuedBook;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import service.ServiceFactory;
import service.custom.impl.IssuedBookServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class IssuedBookFormController implements Initializable {

    public JFXComboBox comboBook;
    public JFXComboBox comboMember;
    public JFXTextField txtQty;
    public DatePicker returnDate;

    IssuedBookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.ISSUEDBOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxesData();
    }

    private void loadComboBoxesData() {
        comboBook.getItems().addAll(service.getBookMap().keySet());
        comboMember.getItems().addAll(service.getMemberMap().keySet());

    }

    public void issueBookOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Book !");
            return;
        } else if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a member !");
            return;
        } else if (txtQty.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add book count !");
            return;
        } else if (returnDate.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select return date !");
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
                clearDataFields();
                deductBookQuantity(issuedBook);
                return;
            }
            Alert.trigger(AlertType.ERROR, "Book issued Failed , try again !");
        }
    }

    private void deductBookQuantity(IssuedBook issuedBook) {
        Boolean isQtyUpdated = service.deductBookQty(issuedBook);
        if (isQtyUpdated) {
            Alert.trigger(AlertType.INFORMATION, "Book Quantity updated...");
            return;
        }
        Alert.trigger(AlertType.ERROR, "Quantity updated failed, try again...");
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearDataFields();
    }

    private void clearDataFields() {
        comboBook.setValue(null);
        comboMember.setValue(null);
        returnDate.setValue(null);
    }
}
