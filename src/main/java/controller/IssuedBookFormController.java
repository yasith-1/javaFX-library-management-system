package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import service.ServiceFactory;
import service.custom.impl.IssuedBookServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class IssuedBookFormController implements Initializable {

    public JFXComboBox comboBook;

    IssuedBookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.ISSUEDBOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxesData();
    }

    private void loadComboBoxesData(){
        comboBook.getItems().addAll(service.getBookMap().keySet());
    }

    public void issueBookOnActionBtn(ActionEvent actionEvent) {
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
    }
}
