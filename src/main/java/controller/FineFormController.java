package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dto.Fine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.impl.FineServiceImpl;
import alert.Alert;
import alert.AlertType;
import util.Report;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class FineFormController implements Initializable {
    public Label txtFineIdLbl;
    public JFXTextArea txtReason;
    public JFXTextField txtAmount;
    public JFXTextField txtSearchFieldBookName;
    public TableView fineTable;
    public TableColumn colMemberName;
    public TableColumn colBookName;
    public TableColumn colAmount;
    public JFXTextField txtSearchFieldMemberName;
    public TableColumn colFineId;
    public TableColumn colReason;
    public TableColumn colTime;
    public TableColumn colDate;
    public TableColumn colStatus;
    public ComboBox comboMember;
    public ComboBox comboBook;
    public ComboBox comboFineStatus;

    FineServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.FINE);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutogenaratedFineId();
        loadAllComboboxData();
        loadFineTable();
        fetchTableRowData();
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
            String statusId = service.getFineStatusMap().get(comboFineStatus.getValue());

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
            if (isFineAdded) {
                setAutogenaratedFineId();
                loadFineTable();
                Alert.trigger(AlertType.INFORMATION, "Fine Added Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Fine adding failed ..");
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
            String statusId = service.getFineStatusMap().get(comboFineStatus.getValue());

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
            if (isFineUpdated) {
                loadFineTable();
                Alert.trigger(AlertType.INFORMATION, "Fine Updated Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Fine Update failed ..");
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
            String statusId = service.getFineStatusMap().get(comboFineStatus.getValue());

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
            if (isFineDeleted) {
                loadFineTable();
                Alert.trigger(AlertType.INFORMATION, "Fine Deleted Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Fine Delete failed ..");
        }
    }

//    public void searchOnActionBtn(ActionEvent actionEvent) {
//        if (txtSearchFieldMemberName.getText().isEmpty()) {
//            Alert.trigger(AlertType.WARNING, "Enter a Member !");
//            return;
//        } else if (txtSearchFieldBookName.getText().isEmpty()) {
//            Alert.trigger(AlertType.WARNING, "Enter a  Book !");
//            return;
//        } else {
////            All validated
//            String memberId = service.getMemberMap().get(txtSearchFieldMemberName.getText());
//            String bookId = service.getBookMap().get(txtSearchFieldBookName.getText());
//
//            Fine fine = new Fine(
//                    txtFineIdLbl.getText(),
//                    null,
//                    null,
//                    null,
//                    null,
//                    memberId == null ? txtSearchFieldMemberName.getText() : memberId,
//                    bookId == null ? txtSearchFieldBookName.getText() : bookId,
//                    null);
//
//            Fine foundedFine = service.searchFine(fine);
//            if (foundedFine != null) {
////                call data set method
//                setFoundedData(foundedFine);
//                return;
//            }
//            Alert.trigger(AlertType.ERROR, "Fine not found try again !");
//        }
//    }

    private void setFoundedData(Fine fine) {
        txtFineIdLbl.setText(fine.getId());
        comboMember.setValue(fine.getMemberId());
        comboBook.setValue(fine.getBookIsbn());
        comboFineStatus.setValue(fine.getStatusId());
        txtReason.setText(fine.getReason());
        txtAmount.setText(fine.getAmount().toString());
    }

    private void loadFineTable() {
        List<Fine> finesList = service.getAllFinesList();
        if (finesList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }

        colFineId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookIsbn"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("paidDate"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("paidTime"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusId"));

        ObservableList<Fine> observableFineList = FXCollections.observableArrayList(finesList);
        fineTable.setItems(observableFineList);

    }

    private void fetchTableRowData() {
        fineTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                Fine selectedFine = (Fine) fineTable.getSelectionModel().getSelectedItem();
                if (selectedFine != null) {
                    setFoundedData(selectedFine);
                }
            }
        });
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearDate();
    }

    private void clearDate() {
        if (!comboMember.getItems().contains("Select Member")) {
            comboMember.getItems().add(0, "Select Member");
        }
        comboMember.setValue("Select Member");

        if (!comboBook.getItems().contains("Select Book")) {
            comboBook.getItems().add(0, "Select Book");
        }
        comboBook.setValue("Select Book");

        if (!comboFineStatus.getItems().contains("Select Status")) {
            comboFineStatus.getItems().add(0, "Select Status");
        }
        comboFineStatus.setValue("Select Status");

        txtSearchFieldBookName.setText("");
        txtSearchFieldMemberName.setText("");
        txtReason.setText("");
        txtAmount.setText("");
    }

    public void setFineOnActionBtn(ActionEvent actionEvent) {
        if (comboMember.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Member !");
            return;
        } else if (comboBook.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select a Book !");
            return;
        } else {
            String memberId = service.getMemberMap().get(comboMember.getValue());
            String bookId = service.getBookMap().get(comboBook.getValue());
            Double totalFineAmount = service.getTotalFineAmount(memberId, bookId);
            if (totalFineAmount != null) {
                if (totalFineAmount == 0.0) {
                    Alert.trigger(AlertType.INFORMATION, "No need to pay fine , you return book earlier");
                    txtAmount.setText(totalFineAmount.toString());
                    return;
                }
                txtAmount.setText(totalFineAmount.toString());
                return;
            }
            Alert.trigger(AlertType.ERROR, "Sorry fine amount not Found !");
        }
    }

    public void checkPendingFinesOnActionButton(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/pendingFinePayemtHolders.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Check Pending Fine Details");
        stage.show();
    }

    public void fineReportOnActionBtn(ActionEvent actionEvent) {
        Report.openReport("fine_report.jrxml");
    }
}
