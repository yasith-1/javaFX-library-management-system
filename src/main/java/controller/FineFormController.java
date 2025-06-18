package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.ServiceFactory;
import service.custom.impl.FineServiceImpl;
import util.ServiceType;

import java.net.URL;
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
    }

    public void updateFineOnActionBtn(ActionEvent actionEvent) {
    }

    public void deleteFineOnActionBtn(ActionEvent actionEvent) {
    }

    public void searchOnActionBtn(ActionEvent actionEvent) {
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
    }
}
