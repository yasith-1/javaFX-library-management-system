package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import dto.Member;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.MemberService;
import alert.Alert;
import alert.AlertType;
import util.Report;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MemberManageFormController implements Initializable {
    public JFXTextField txtMemberName;
    public TableColumn colMemberId;
    public TableColumn colMemberName;
    public TableView memberTable;
    public Label txtMemberIdLbl;
    public JFXTextField txtMemberNIC;
    public JFXTextField txtMemberEmail;
    public JFXTextField txtMemberAddress;
    public TableColumn colMemberNIC;
    public TableColumn colMemberEmail;
    public TableColumn colMemberAddress;
    public TableColumn colMemberType;
    public ComboBox comMemberType;


//    MemberServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.MEMBER);
    @Inject
    MemberService service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutoGenaratedId();
        loadComboboxData();
        loadMemberTable();
        fetchTableRowData();
    }

    private void setAutoGenaratedId() {
        txtMemberIdLbl.setText(service.getMemberId());
    }

    private void loadComboboxData() {
        comMemberType.getItems().addAll(service.getMemberMap().keySet());
    }

    public void addMemberOnActionBtn(ActionEvent actionEvent) {
        if (txtMemberName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Member Name");
            return;
        } else if (txtMemberEmail.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Member Email");
            return;
        } else if (txtMemberAddress.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Add Member Home Town");
            return;
        } else if (comMemberType.getValue() == null) {
            Alert.trigger(AlertType.WARNING, "Select Member Type");
            return;
        } else {
//            All validated
            String memberTypeId = service.getMemberMap().get(comMemberType.getValue());
            Member member = new Member(
                    txtMemberIdLbl.getText(),
                    txtMemberName.getText(),
                    txtMemberNIC.getText(),
                    txtMemberEmail.getText(),
                    txtMemberAddress.getText(),
                    null,
                    memberTypeId);

            Boolean isAddedMember = service.addMember(member);
            if (isAddedMember) {
                setAutoGenaratedId();
                loadMemberTable();
                clearTextField();
                Alert.trigger(AlertType.INFORMATION, "Member Added Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Member doesn't Added !");
        }
    }


    public void updateMemberOnActionBtn(ActionEvent actionEvent) {
        if (txtMemberName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Double click table row that you want to update !");
            return;
        } else {
//            All validated
            String memberTypeId = service.getMemberMap().get(comMemberType.getValue());
            Member member = new Member(
                    txtMemberIdLbl.getText(),
                    txtMemberName.getText(),
                    txtMemberNIC.getText(),
                    txtMemberEmail.getText(),
                    txtMemberAddress.getText(),
                    null,
                    memberTypeId);

            Boolean isUpdatedMember = service.updateMember(member);
            if (isUpdatedMember) {
                loadMemberTable();
                clearTextField();
                Alert.trigger(AlertType.INFORMATION, "Member Updated Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Member doesn't Updated !");
        }
    }

    public void deleteMemberOnActionBtn(ActionEvent actionEvent) {
        if (txtMemberName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Double click table row that you want to Delete !");
            return;
        } else {
//            All validated
            String memberTypeId = service.getMemberMap().get(comMemberType.getValue());
            Member member = new Member(
                    txtMemberIdLbl.getText(),
                    null,
                    txtMemberNIC.getText(),
                    null,
                    null,
                    null,
                    null);

            Boolean isDeletedMember = service.deleteMember(member);
            if (isDeletedMember) {
                loadMemberTable();
                clearTextField();
                Alert.trigger(AlertType.INFORMATION, "Member Deleted Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR, "Member doesn't Deleted !");
        }
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearTextField();
    }

    private void loadMemberTable() {
        List<Member> memberList = service.getMembersList();
        if (memberList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }

        colMemberId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMemberNIC.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colMemberEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMemberAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMemberType.setCellValueFactory(new PropertyValueFactory<>("typeId"));

        ObservableList<Member> members = FXCollections.observableArrayList(memberList);
        memberTable.setItems(members);
    }

    private void fetchTableRowData() {
        memberTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                Member selectedMember = (Member) memberTable.getSelectionModel().getSelectedItem();
                if (selectedMember != null) {
                    setFoundedData(selectedMember);
                }
            }
        });
    }

    private void setFoundedData(Member member) {
        txtMemberIdLbl.setText(member.getId());
        txtMemberName.setText(member.getName());
        txtMemberNIC.setText(member.getNic());
        txtMemberEmail.setText(member.getEmail());
        txtMemberAddress.setText(member.getAddress());
        comMemberType.setValue(member.getTypeId());
    }

    private void clearTextField() {
        txtMemberName.setText("");
        txtMemberNIC.setText("");
        txtMemberEmail.setText("");
        txtMemberAddress.setText("");

        if (!comMemberType.getItems().contains("Select Member Type")) {
            comMemberType.getItems().add(0, "Select Member Type");
        }
        comMemberType.setValue("Select Member Type");
    }

    public void memberReportActionBtn(ActionEvent actionEvent) {
        Report.openReport("members_report.jrxml");
    }
}
