package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dto.Fine;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.ServiceFactory;
import service.custom.impl.FineServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class FineFormController implements Initializable {
    public Label txtFineIdLbl;
    public JFXTextArea txtReason;
    public JFXTextField txtAmount;
    public JFXComboBox comboMember;
    public JFXComboBox comboBook;
    public JFXComboBox comboFineStatus;
    public JFXTextField txtSearchFieldBookName;
    public TableView fineTable;
    public TableColumn colMemberName;
    public TableColumn colBookName;
    public TableColumn colPaidDate;
    public TableColumn colPaidTIme;
    public TableColumn colAmount;
    public TableColumn colPayStatus;
    public JFXTextField txtSearchFieldMemberName;

    FineServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.FINE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutogenaratedFineId();
        loadAllComboboxData();
    }

    private void setAutogenaratedFineId() {
        txtFineIdLbl.setText(service.fineId());
    }

    private void loadAllComboboxData() {
        comboMember.getItems().addAll(service.getMemberMap().keySet());
        comboBook.getItems().addAll(service.getBookMap().keySet());
        comboFineStatus.getItems().addAll(service.getFineStatusMap().keySet());
    }

    public void addFineOnActionBtn(ActionEvent actionEvent) {
        if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Member !");
            return;
        } else if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Book !");
            return;
        } else if (comboFineStatus.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a fine status !");
            return;
        } else if (txtReason.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Reason !");
            return;
        } else if (txtAmount.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Fine amount !");
            return;
        } else {
//            All validated
            String memberId = service.getMemberMap().get(comboMember.getValue());
            String bookId = service.getBookMap().get(comboBook.getValue());
            Integer statusId = service.getFineStatusMap().get(comboFineStatus.getValue());

            Fine fine = new Fine(
                    txtFineIdLbl.getText(),
                    txtReason.getText(),
                    LocalDate.now(),
                    LocalTime.now(),
                    Double.valueOf(txtAmount.getText()),
                    memberId,
                    bookId,
                    statusId);

            Boolean isFineAdded = service.addFine(fine);
            if (isFineAdded){
                Alert.trigger(AlertType.INFORMATION,"Fine Added Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR,"Fine adding failed ..");
        }
    }

    public void updateFineOnActionBtn(ActionEvent actionEvent) {
        if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Member !");
            return;
        } else if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Book !");
            return;
        } else if (comboFineStatus.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a fine status !");
            return;
        } else if (txtReason.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Reason !");
            return;
        } else if (txtAmount.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Fine amount !");
            return;
        } else {
//            All validated
            String memberId = service.getMemberMap().get(comboMember.getValue());
            String bookId = service.getBookMap().get(comboBook.getValue());
            Integer statusId = service.getFineStatusMap().get(comboFineStatus.getValue());

            Fine fine = new Fine(
                    txtFineIdLbl.getText(),
                    txtReason.getText(),
                    LocalDate.now(),
                    LocalTime.now(),
                    Double.valueOf(txtAmount.getText()),
                    memberId,
                    bookId,
                    statusId);

            Boolean isFineUpdated = service.updateFine(fine);
            if (isFineUpdated){
                Alert.trigger(AlertType.INFORMATION,"Fine Updated Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR,"Fine Update failed ..");
        }
    }

    public void deleteFineOnActionBtn(ActionEvent actionEvent) {
        if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Member !");
            return;
        } else if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Book !");
            return;
        } else if (comboFineStatus.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a fine status !");
            return;
        } else if (txtReason.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Reason !");
            return;
        } else if (txtAmount.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Fine amount !");
            return;
        } else {
//            All validated
            String memberId = service.getMemberMap().get(comboMember.getValue());
            String bookId = service.getBookMap().get(comboBook.getValue());
            Integer statusId = service.getFineStatusMap().get(comboFineStatus.getValue());

            Fine fine = new Fine(
                    txtFineIdLbl.getText(),
                    txtReason.getText(),
                    LocalDate.now(),
                    LocalTime.now(),
                    Double.valueOf(txtAmount.getText()),
                    memberId,
                    bookId,
                    statusId);

            Boolean isFineDeleted = service.deleteFine(fine);
            if (isFineDeleted){
                Alert.trigger(AlertType.INFORMATION,"Fine Deleted Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR,"Fine Delete failed ..");
        }
    }

    public void searchOnActionBtn(ActionEvent actionEvent) {
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
    }
}
