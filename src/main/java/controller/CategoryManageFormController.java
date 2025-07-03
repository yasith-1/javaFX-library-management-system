package controller;

import com.google.inject.Inject;
import com.jfoenix.controls.JFXTextField;
import dto.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.CategoryService;
import alert.Alert;
import alert.AlertType;
import util.Report;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryManageFormController implements Initializable {
    public JFXTextField txtCategoryId;
    public JFXTextField txtCategoryName;
    public TableView categoryTable;
    public TableColumn colCategoryId;
    public TableColumn colCategoryName;

//    CategoryServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.CATEGORY);
    @Inject
    CategoryService service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadCategorytable();
        setAutoGenaratedId();
        fetchTableRowData();
    }

    private void setAutoGenaratedId() {
        txtCategoryId.setText(service.getCategoryId());
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        clearField();
    }

    private void clearField() {
//        txtCategoryId.setText("");
        txtCategoryName.setText("");
    }

    public void addCategoryOnActionBtn(ActionEvent actionEvent) {
        if (txtCategoryId.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Category Id missing ..");
            return;
        } else if (txtCategoryName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Category name ..");
            return;
        } else {
//            Validate text fields and ensure those all filled
            Category category = new Category(txtCategoryId.getText(), txtCategoryName.getText());
            Boolean isAdded = service.addCategory(category);
            if (isAdded) {
//                    category added successfully ...........
                clearField();
                setAutoGenaratedId();
                loadCategorytable();
                Alert.trigger(AlertType.INFORMATION, "Category Added Successfully !");
            } else {
//                    category is not added  ...........
                clearField();
                Alert.trigger(AlertType.ERROR, "Category doesn't Added ..");

            }
        }
    }

    public void updateCategoryOnActionBtn(ActionEvent actionEvent) {
        if (txtCategoryId.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Category Id missing ..");
            return;
        } else if (txtCategoryName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Category name ..");
            return;
        } else {
//            Validate text fields and ensure those all filled
            Category category = new Category(txtCategoryId.getText(), txtCategoryName.getText());
            Boolean isAdded = service.updateCategory(category);
            if (isAdded) {
//                    category updated successfully ...........
                clearField();
                loadCategorytable();
                Alert.trigger(AlertType.INFORMATION, "Category Updated Successfully !");
            } else {
//                    category is not updated  ...........
                clearField();
                Alert.trigger(AlertType.ERROR, "Category doesn't update ..");

            }
        }
    }

    public void deleteCategoryOnActionBtn(ActionEvent actionEvent) {
        if (txtCategoryId.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Category Id missing ..");
            return;
        } else if (txtCategoryName.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING, "Enter Category name ..");
            return;
        } else {
//            Validate text fields and ensure those all filled
            Category category = new Category(txtCategoryId.getText(), txtCategoryName.getText());
            Boolean isAdded = service.deleteCategory(category);
            if (isAdded) {
//                    category Deleted successfully ...........
                clearField();
                loadCategorytable();
                Alert.trigger(AlertType.INFORMATION, "Category Deleted Successfully !");
            } else {
//                    category is not Deleted  ...........
                clearField();
                Alert.trigger(AlertType.ERROR, "Category doesn't delete ..");

            }
        }
    }

    private void loadCategorytable() {
        List<Category> categoryList = service.getCategoryList();
        if (categoryList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }

        colCategoryId.setCellValueFactory(new PropertyValueFactory<>("gerneId"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<Category> categoriesList = FXCollections.observableArrayList(categoryList);
        categoryTable.setItems(categoriesList);
    }

    private void fetchTableRowData() {
        categoryTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // double-click
                Category selectedCategory = (Category) categoryTable.getSelectionModel().getSelectedItem();
                if (selectedCategory != null) {
                    setFoundedData(selectedCategory);
                }
            }
        });
    }

    private void setFoundedData(Category category) {
        txtCategoryId.setText(category.getGerneId());
        txtCategoryName.setText(category.getName());
    }

    public void categoryReportOnActionBtn(ActionEvent actionEvent) {
        Report.openReport("category_report.jrxm");
    }
}
