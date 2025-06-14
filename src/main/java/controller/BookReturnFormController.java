package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.ServiceFactory;
import service.custom.impl.IssuedBookServiceImpl;
import util.ServiceType;
import java.net.URL;
import java.util.ResourceBundle;

public class BookReturnFormController implements Initializable {
    public JFXComboBox comboBook;
    public JFXComboBox comboMember;
    public TableView returnBookTable;
    public TableColumn colMemberName;
    public TableColumn colReurnDate;
    public TableColumn colBookName;
    public TableColumn colReturnTime;

    IssuedBookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.ISSUEDBOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxData();
    }

    private void loadComboBoxData() {
        comboBook.getItems().addAll(service.getBookMap().keySet());
        comboMember.getItems().addAll(service.getMemberMap().keySet());
    }

    public void addOnActionBtn(ActionEvent actionEvent) {
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
    }

    public void deleteOnActionBtn(ActionEvent actionEvent) {
    }
}
