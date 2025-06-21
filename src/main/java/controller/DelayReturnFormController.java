package controller;

import dto.DelayReturn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.impl.DelayReturnServiceImpl;
import util.*;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class DelayReturnFormController implements Initializable {
    public TableView membersTable;
    public ComboBox comboMembers;
    public TableView dellayReturnOverviewTable;
    public TableColumn colMemberId;
    public TableColumn colIssueDate;
    public TableColumn colMemberName;
    public TableColumn colDateToReturn;
    public TableColumn colReturnedDate;
    public TableColumn colDelayedDays;
    public TableColumn colMId;
    public TableColumn colMName;
    public TableColumn colBookId;
    public TableColumn colReturnTime;

    DelayReturnServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.DELAYEDRETURN);
    HashMap<String, String> memberMap;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        memberMap = service.getMemberMap();
        setComboboxData();
        loadMemberTable();
    }

    private void setComboboxData() {
        List<String> delayReturnMembersList = service.delayReturnedMembersNameList();
        if (delayReturnMembersList != null) {
            comboMembers.setValue("Select Option");
            comboMembers.setItems(FXCollections.observableArrayList(delayReturnMembersList));
            return;
        }
        Alert.trigger(AlertType.WARNING, "Still not found Delay return members !");
    }

    private void loadMemberTable() {
        List<DelayReturn> delayReturnMembersList = service.getDelayReturnMembersList();
        if (delayReturnMembersList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }

        colMId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colMName.setCellValueFactory(new PropertyValueFactory<>("memberName"));

        ObservableList<DelayReturn> delayReturns = FXCollections.observableArrayList(delayReturnMembersList);
        membersTable.setItems(delayReturns);
    }

    public void makeFineOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage oldStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        oldStage.close();

        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/fineManageForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Delay Return and Make Fines");
        stage.show();
    }

    public void memberSelectionComboBoxOnAction(ActionEvent actionEvent) {
        String memberId = memberMap.get(comboMembers.getValue());
        loadDelayReturnOverviewTable(memberId);
    }

    private void loadDelayReturnOverviewTable(String memberId) {
        List<DelayReturn> delayReturnOverviewList = service.delayReturnedOverviewList(memberId);
        if (delayReturnOverviewList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }

        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("memberName"));
        colIssueDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        colDateToReturn.setCellValueFactory(new PropertyValueFactory<>("dateToReturn"));
        colReturnedDate.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
        colReturnTime.setCellValueFactory(new PropertyValueFactory<>("returnedTime"));
        colDelayedDays.setCellValueFactory(new PropertyValueFactory<>("delayedDays"));

        ObservableList<DelayReturn> delayReturnsOverviews = FXCollections.observableArrayList(delayReturnOverviewList);
        dellayReturnOverviewTable.setItems(delayReturnsOverviews);
    }
}
