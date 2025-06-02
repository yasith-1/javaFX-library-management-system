package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.Initializable;
import service.ServiceFactory;
import service.custom.impl.BookServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtTile;
    public JFXTextField txtCopies;
    public JFXComboBox comboAuthor;
    public JFXComboBox comboCategory;
    public JFXComboBox comboStatus;

    BookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtId.setText(service.getNextBookId());
    }
}
