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

    HashMap<String,String> categoryMap = new HashMap<>();
    BookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setText(service.getNextBookId());
        categoryMap.put("Horror","C001");
        categoryMap.put("Romance","C002");

        comboCategory.getItems().addAll(categoryMap.keySet());
    }


    public void comboCategoryOnaction(ActionEvent actionEvent) {
        System.out.println(comboCategory.getValue());
    }

    public void comboStatusOnaction(ActionEvent actionEvent) {
    }



    public void comboAuthorOnaction(ActionEvent actionEvent) {
    }
}
