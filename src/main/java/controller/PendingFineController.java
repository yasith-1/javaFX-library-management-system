package controller;

import com.google.inject.Inject;
import dto.PendingFine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.PendingFineService;
import alert.Alert;
import alert.AlertType;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PendingFineController implements Initializable {
    public TableColumn colMemberId;
    public TableColumn colBookId;
    public TableColumn colMemberName;
    public TableColumn colReturnDelayDays;
    public TableView pendingFineTable;
    public TableColumn colBookName;

    //    PendingFineServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.PENDINGFINE);
    @Inject
    PendingFineService service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPendingFineTable();
    }

    private void loadPendingFineTable() {
        List<PendingFine> pendingFineList = service.getPendingFineList();
        if (pendingFineList == null) {
            Alert.trigger(AlertType.WARNING, "No data available pending fines till now !");
            return;
        }

        colMemberId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookIsbn"));
        colMemberName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colReturnDelayDays.setCellValueFactory(new PropertyValueFactory<>("delayedDays"));

        ObservableList<PendingFine> pendingFineObservableList = FXCollections.observableArrayList(pendingFineList);
        pendingFineTable.setItems(pendingFineObservableList);
    }

    public void reloadTableOnActionButton(ActionEvent actionEvent) {
        loadPendingFineTable();
    }
}
