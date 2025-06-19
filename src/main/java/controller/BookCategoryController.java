package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.ServiceFactory;
import service.custom.impl.CategoryServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookCategoryController implements Initializable {
    public JFXTextField txtCategoryId;
    public JFXTextField txtCategoryName;
    public TableView categoryTable;
    public TableColumn colCategoryId;
    public TableColumn colCategoryName;

    CategoryServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.CATEGORY);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCategorytable();
        setAutoGenaratedId();
    }

    private void setAutoGenaratedId() {
        txtCategoryId.setText(service.getCategoryId());
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField(){
//        txtCategoryId.setText("");
        txtCategoryName.setText("");
    }

    private void loadCategorytable(){
        List<Category> categoryList = service.getCategoryList();
        if (categoryList == null){
            Alert.trigger(AlertType.WARNING,"No available data in table now !");
            return;
        }

        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("gerneId"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<Category> categoriesList = FXCollections.observableArrayList(categoryList);
        categoryTable.setItems(categoriesList);
    }


    public void addCategoryOnActionBtn(ActionEvent actionEvent) {
//        validating Input fields------------------

        if (txtCategoryId.getText().isEmpty()) {
            Notifications.create()
                    .title("Warning")
                    .text("Category Id missing ..")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();

            return;
        } else if (txtCategoryName.getText().isEmpty()) {
            Notifications.create()
                    .title("Warning")
                    .text("Enter Category name ..")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();
            return;
        } else {
//            Validate text fields and ensure those all filled
            Category category = new Category(txtCategoryId.getText(), txtCategoryName.getText());
            Boolean isAdded = service.addBook(category);
            if (isAdded) {
//                    category added successfully ...........
                Notifications.create()
                        .title("Success")
                        .text("Category Added Successfully ")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showInformation();
                clearField();
                setAutoGenaratedId();
                loadCategorytable();
            } else {
//                    category is not added  ...........
                Notifications.create()
                        .title("Error")
                        .text("Category doessn't Added ... ")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showError();
                clearField();
            }
        }
    }

    public void updateCategoryOnActionBtn(ActionEvent actionEvent) {
    }

    public void deleteCategoryOnActionBtn(ActionEvent actionEvent) {
    }
}
