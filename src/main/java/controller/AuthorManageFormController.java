package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import dto.Author;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.AuthorService;
import alert.Alert;
import alert.AlertType;
import util.Report;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AuthorManageFormController implements Initializable {
    public JFXTextField txtAuthorId;
    public JFXTextField txtAuthorName;
    public TableView authorTable;
    public TableColumn colAuthorName;
    public TableColumn colAuthorId;

//    AuthorServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.AUTHOR);
    @Inject
    AuthorService service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAutoGenaratedId();
        loadAuthorTable();
        fetchTableRowData();

    }

    //      This method is used to set the auto-generated author ID in the text field.
    private void setAutoGenaratedId() {
        String newId = service.getAuthoryId();
        if (newId != null) {
            txtAuthorId.setText(newId);
        } else {
            txtAuthorId.setText("");
            Alert.trigger(AlertType.WARNING, "Something went wrong, ID not generated.");
        }
    }

    //      This method is used to add a new author when the action button is clicked.
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

    //      This method is used to update an existing author when the action button is clicked.
    public void updateAuthorOnActionBtn(ActionEvent actionEvent) {
        if (txtAuthorId.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Author Id not found ..");
            return;
        } else if (txtAuthorName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Select Auhor from table that you want to update !");
            return;
        } else {
//            Validate text fields and ensure those all filled
            Author author = new Author(txtAuthorId.getText(), txtAuthorName.getText());
            Boolean isUpdated = service.updateAuthor(author);
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

    //      This method is used to delete an existing author when the action button is clicked.
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
            Boolean isDeleted = service.deleteAuthor(author);
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


    //      This method is used to clear the author name field when the action button is clicked.
    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField() {
        txtAuthorName.setText("");
    }

    //      This method is used to load the author table with data from the service.
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

    //      This method is used to set the data of the selected author in the text fields.
    private void setFoundedData(Author author) {
        txtAuthorId.setText(author.getId());
        txtAuthorName.setText(author.getName());
    }

    //      This method is used to open the author report when the action button is clicked.
    public void authorReportActionBtn(ActionEvent actionEvent) {
        Report.openReport("author_report.jrxml");
    }
}
