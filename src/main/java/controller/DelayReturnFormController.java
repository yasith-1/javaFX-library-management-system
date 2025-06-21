package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DelayReturnFormController {
    public TableView membersTable;
    public ComboBox comboMembers;
    public TableView dellayReturnOverviewTable;
    public TableColumn colMemberId;
    public TableColumn colIssueDate;
    public TableColumn colMemberName;
    public TableColumn colDateToReturn;
    public TableColumn colReturnedDate;
    public TableColumn colReturnedTime;
    public TableColumn colDelayedDays;

    public void makeFineOnActionBtn(ActionEvent actionEvent) {
    }
}
