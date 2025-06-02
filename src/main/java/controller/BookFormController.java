package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import service.ServiceFactory;
import service.custom.impl.BookServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtTile;
    public JFXTextField txtCopies;
    public JFXComboBox comboCategory;
    public JFXComboBox comboStatus;
    public JFXComboBox comboAuthor;

    HashMap<String, String> categoryMap = new HashMap<>();
    BookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutogenarateBookId();
        loadAllComboBoxData();
    }

    private void loadAllComboBoxData() {
        //        service.GetBookGerneMap() <-- book gerne map reference
        comboCategory.getItems().addAll(service.GetBookGerneMap().keySet());
        //        service.GetBookGerneMap() <-- book author map reference
        comboAuthor.getItems().addAll(service.GetAuthorMap().keySet());
        //        service.GetBookGerneMap() <-- book status map reference
        comboStatus.getItems().addAll(service.GetStatusMap().keySet());
    }

    private void setAutogenarateBookId() {
        txtId.setText(service.getNextBookId());
    }

    public void comboCategoryOnaction(ActionEvent actionEvent) {
        System.out.println(comboCategory.getValue());
    }

    public void comboStatusOnaction(ActionEvent actionEvent) {
    }


    public void comboAuthorOnaction(ActionEvent actionEvent) {
    }
}
