package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Author;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.impl.AuthorServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AuthorManageFormController implements Initializable {
    public JFXTextField txtAuthorId;
    public JFXTextField txtAuthorName;
    public TableView authorTable;
    public TableColumn colAuthorName;
    public TableColumn colAuthorId;

    AuthorServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.AUTHOR);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutoGenaratedId();
        loadAuthorTable();
        fetchTableRowData();

    }

    private void setAutoGenaratedId() {
        txtAuthorId.setText(service.getAuthoryId());
    }

    public void addAuthorOnActionBtn(ActionEvent actionEvent) {
        if (txtAuthorId.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Author Id missing ..");
            return;
        } else if (txtAuthorName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Author name ..");
            return;
        } else {
//            Validate text fields and ensure those all filled
            Author author = new Author(txtAuthorId.getText(), txtAuthorName.getText());
            Boolean isAdded = service.addAuthor(author);
            if (isAdded) {
//                    Author added successfully ...........
                clearField();
                setAutoGenaratedId();
                loadAuthorTable();
                Alert.trigger(AlertType.INFORMATION, "Author Added Successfully !");
            } else {
//                    Author is not added  ...........
                clearField();
                Alert.trigger(AlertType.ERROR, "Author doesn't Added ..");

            }
        }
    }

    public void updateAuthorOnActionBtn(ActionEvent actionEvent) {
        if (txtAuthorId.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Author Id missing ..");
            return;
        } else if (txtAuthorName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Select Auhor from table that you want to update !");
            return;
        } else {
//            Validate text fields and ensure those all filled
            Author author = new Author(txtAuthorId.getText(), txtAuthorName.getText());
            Boolean isUpdated= service.updateAuthor(author);
            if (isUpdated) {
//                    Author updated successfully ...........
                clearField();
                loadAuthorTable();
                Alert.trigger(AlertType.INFORMATION, "Author updated Successfully !");
            } else {
//                    Author is not updated  ...........
                clearField();
                Alert.trigger(AlertType.ERROR, "Author doesn't updated ..");

            }
        }
    }

    public void deleteAuthorOnActionBtn(ActionEvent actionEvent) {
        if (txtAuthorId.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Author Id missing ..");
            return;
        } else if (txtAuthorName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Select Auhor from table that you want to delete !");
            return;
        } else {
//            Validate text fields and ensure those all filled
            Author author = new Author(txtAuthorId.getText(), txtAuthorName.getText());
            Boolean isDeleted= service.deleteAuthor(author);
            if (isDeleted) {
//                    Author Delete successfully ...........
                clearField();
                loadAuthorTable();
                Alert.trigger(AlertType.INFORMATION, "Author Deleted Successfully !");
            } else {
//                    Author is not Delete  ...........
                clearField();
                Alert.trigger(AlertType.ERROR, "Author doesn't Delete ..");
            }
        }
    }


    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField() {
        txtAuthorName.setText("");
    }

    private void loadAuthorTable() {
        List<Author> authorList = service.getAuthorList();
        if (authorList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }

        colAuthorId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAuthorName.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<Author> categoriesList = FXCollections.observableArrayList(authorList);
        authorTable.setItems(categoriesList);
    }

    private void fetchTableRowData() {
        authorTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                Author selectedAuthor = (Author) authorTable.getSelectionModel().getSelectedItem();
                if (selectedAuthor != null) {
                    setFoundedData(selectedAuthor);
                }
            }
        });
    }

    private void setFoundedData(Author author) {
        txtAuthorId.setText(author.getId());
        txtAuthorName.setText(author.getName());
    }
}
