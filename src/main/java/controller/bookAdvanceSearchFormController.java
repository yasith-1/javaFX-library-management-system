package controller;

import alert.Alert;
import alert.AlertType;
import dto.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceFactory;
import service.custom.impl.BookServiceImpl;
import util.ServiceType;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class bookAdvanceSearchFormController implements Initializable {

    public ComboBox comboTitle;
    public ComboBox comboAuthor;
    public ComboBox comboCategory;
    public TableView tableBooDetail;
    public TableColumn colBookName;
    public TableColumn colAuthorName;
    public TableColumn colCategoryName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxData();
    }

    private void loadComboBoxData() {
        //        service.GetBookGerneMap() <-- book gerne map reference
        comboCategory.getItems().addAll(service.getBookGerneMap().keySet());
        //        service.GetBookGerneMap() <-- book author map reference
        comboAuthor.getItems().addAll(service.getAuthorMap().keySet());
        //        service.GetBookGerneMap() <-- book status map reference
        comboTitle.getItems().addAll(service.getBookMap().keySet());
    }

    // Assuming you have a service to fetch the data
    BookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    public void searchOnActionBtn(ActionEvent actionEvent) {

        if (comboTitle.getValue() == null && comboAuthor.getValue() == null && comboCategory.getValue() == null) {
            Alert.trigger(AlertType.WARNING, " Please fill at least one field to search.");
            return;
        }
        // Assuming you have a method to fetch the search results based on the selected values
        String title = comboTitle.getValue() != null ? comboTitle.getValue().toString() : null;
        String author = comboAuthor.getValue() != null ? comboAuthor.getValue().toString() : null;
        String category = comboCategory.getValue() != null ? comboCategory.getValue().toString() : null;

        // Call the service to get the search results
         List<Book> searchResults = service.searchBooks(title, author, category);
         if (searchResults != null){
             loadBookTable(searchResults);
             return;
         }
         Alert.trigger( AlertType.WARNING, "No results found for the given search criteria.");
    }

    private void loadBookTable(List<Book> searchResults) {
        List<Book> bookList = searchResults;
        if (bookList == null) {
            Alert.trigger(AlertType.WARNING, "No available data in table now !");
            return;
        }

        colBookName.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthorName.setCellValueFactory(new PropertyValueFactory<>("authorId"));
        colCategoryName.setCellValueFactory(new PropertyValueFactory<>("gerneId"));

        ObservableList<Object> observableList = FXCollections.observableArrayList(bookList);
        tableBooDetail.setItems(observableList);
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        if (!comboTitle.getItems().contains("Select Book")) {
            comboTitle.getItems().add(0, "Select Book");
        }
        comboTitle.setValue("Select Book");

        if (!comboAuthor.getItems().contains("Select Author")) {
            comboAuthor.getItems().add(0, "Select Author");
        }
        comboAuthor.setValue("Select Author");

        if (!comboCategory.getItems().contains("Select Category")) {
            comboCategory.getItems().add(0, "Select Category");
        }
        comboCategory.setValue("Select Category");
    }
}
