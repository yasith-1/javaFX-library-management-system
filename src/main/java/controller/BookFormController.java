package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.Book;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import service.ServiceFactory;
import service.custom.impl.BookServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {
    public JFXTextField txtId;
    public JFXTextField txtTile;
    public JFXTextField txtCopies;
    public JFXComboBox comboCategory;
    public JFXComboBox comboStatus;
    public JFXComboBox comboAuthor;

    BookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutogenarateBookId();
        loadAllComboBoxData();
    }

    private void loadAllComboBoxData() {
        //        service.GetBookGerneMap() <-- book gerne map reference
//        comboCategory.getItems().addAll(service.GetBookGerneMap());
        comboCategory.getItems().addAll(service.getBookGerneMap().keySet());
        //        service.GetBookGerneMap() <-- book author map reference
        comboAuthor.getItems().addAll(service.getAuthorMap().keySet());
        //        service.GetBookGerneMap() <-- book status map reference
        comboStatus.getItems().addAll(service.getStatusMap().keySet()
        );
    }

    private void setAutogenarateBookId() {
        txtId.setText(service.getNextBookId());
    }

    public void comboCategoryOnaction(ActionEvent actionEvent) {
        System.out.println(service.getBookGerneMap().get(comboCategory.getValue()));

    }

    public void comboAuthorOnaction(ActionEvent actionEvent) {
    }

    public void comboStatusOnaction(ActionEvent actionEvent) {
        System.out.println();
    }

    public void addBookOnActionBtn(ActionEvent actionEvent) {

        Integer statusId = Integer.parseInt(service.getStatusMap().get(comboStatus.getValue()));
        String gerneId = service.getBookGerneMap().get(comboCategory.getValue());
        String authorId = service.getAuthorMap().get(comboAuthor.getValue());

        Book book = new Book(
                txtId.getText(),
                txtTile.getText(),
                Integer.parseInt(txtCopies.getText()),
                statusId,
                gerneId,
                authorId);


    }

}
