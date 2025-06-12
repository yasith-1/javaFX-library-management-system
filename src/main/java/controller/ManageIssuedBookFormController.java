package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.IssuedBook;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import service.ServiceFactory;
import service.custom.impl.IssuedBookServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ManageIssuedBookFormController implements Initializable {
    public JFXComboBox comboMember;
    public JFXComboBox comboBook;
    public JFXTextField txtSearchFieldMemberName;
    public JFXTextField txtSearchFieldBook;
    public TableView issueBookTable;
    public JFXTextField txtQty;

    IssuedBookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.ISSUEDBOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxesData();
    }

    private void loadComboBoxesData() {
        comboBook.getItems().addAll(service.getBookMap().keySet());
        comboMember.getItems().addAll(service.getMemberMap().keySet());

    }

    public void updateIssuedBookOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select Book that wants to update !");
            return;
        } else if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select member that wants to update !");
            return;
        } else if (txtQty.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add correct count !");
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
                deductBookQuantity(issuedBook);
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
        }  else if (txtQty.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "missing book quantity!");
            return;
        }else {
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
                renewBookQuantity(issuedBook);
                return;
            }
            Alert.trigger(AlertType.ERROR, "Book is not deleted ...");
        }
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearDataFields();
    }

    public void searchOnActionBtn(ActionEvent actionEvent) {
    }

    private void renewBookQuantity(IssuedBook issuedBook){
        Boolean isRenewedQty = service.renewBookQty(issuedBook);
        if (isRenewedQty){
            Alert.trigger(AlertType.INFORMATION, "Book Quantity Renewed...");
            return;
        }
        Alert.trigger(AlertType.ERROR, "Quantity renew failed, try again...");
    }

    private void deductBookQuantity(IssuedBook issuedBook) {
        Boolean isQtyUpdated = service.deductBookQty(issuedBook);
        if (isQtyUpdated) {
            Alert.trigger(AlertType.INFORMATION, "Book Quantity updated...");
            return;
        }
        Alert.trigger(AlertType.ERROR, "Quantity updated failed, try again...");
    }

    private void clearDataFields() {
        comboBook.setValue(null);
        comboMember.setValue(null);
        txtQty.setText("");
    }


}
