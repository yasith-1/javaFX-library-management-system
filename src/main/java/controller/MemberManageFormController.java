package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class MemberManageFormController implements Initializable {
    public JFXTextField txtMemberName;
    public TableColumn colMemberId;
    public TableColumn colMemberName;
    public TableView memberTable;
    public Label txtMemberIdLbl;
    public JFXTextField txtMemberNIC;
    public JFXTextField txtMemberEmail;
    public JFXComboBox comMemberType;
    public JFXTextField txtMemberAddress;
    public TableColumn colMemberNIC;
    public TableColumn colMemberEmail;
    public TableColumn colMemberAddress;
    public TableColumn colMemberType;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    public void addMemberOnActionBtn(ActionEvent actionEvent) {
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
    }

    public void updateMemberOnActionBtn(ActionEvent actionEvent) {
    }

    public void deleteMemberOnActionBtn(ActionEvent actionEvent) {
    }

}
