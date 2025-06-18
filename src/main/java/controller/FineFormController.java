package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class FineFormController {
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

    public void clearOnActionBtn(ActionEvent actionEvent) {
    }

    public void addFineOnActionBtn(ActionEvent actionEvent) {
    }

    public void updateFineOnActionBtn(ActionEvent actionEvent) {
    }

    public void deleteFineOnActionBtn(ActionEvent actionEvent) {
    }

    public void searchOnActionBtn(ActionEvent actionEvent) {
    }
}
