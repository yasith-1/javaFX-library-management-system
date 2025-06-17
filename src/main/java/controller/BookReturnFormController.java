package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReturnBook;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.ServiceFactory;
import service.custom.impl.ReturnBookServiceImpl;
import util.Alert;
import util.AlertType;
import util.ServiceType;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class BookReturnFormController implements Initializable {
    public JFXComboBox comboBook;
    public JFXComboBox comboMember;
    public TableView returnBookTable;
    public TableColumn colMemberName;
    public TableColumn colReurnDate;
    public TableColumn colBookName;
    public TableColumn colReturnTime;
    public JFXTextField txtMemberId;
    public JFXTextField txtBookId;

    ReturnBookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.RETURNBOOK);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadComboBoxData();
    }

    private void loadComboBoxData() {
        comboBook.getItems().addAll(service.getBookMap().keySet());
        comboMember.getItems().addAll(service.getMemberMap().keySet());
    }

    public void addBookReturnOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null){
            Alert.trigger(AlertType.WARNING,"Select book that return !");
            return;
        } else if (comboMember.getValue()==null) {
            Alert.trigger(AlertType.WARNING,"Select member !");
            return;
        }else {
//            All Ok
            String memberId = service.getMemberMap().get(comboMember.getValue().toString());
            String bookId = service.getBookMap().get(comboBook.getValue().toString());

            ReturnBook returnBook = new ReturnBook(

                    memberId,
                    bookId,
                    LocalDate.now(),
                    LocalTime.now());

            Boolean isAdded = service.addReturnRecord(returnBook);
            if (isAdded){
                Alert.trigger(AlertType.INFORMATION,"Return Book Restocked Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR,"Return Book Restocked Failed !");
        }
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
        comboBook.setValue(null);
        comboMember.setValue(null);
    }

    public void deleteOnActionBtn(ActionEvent actionEvent) {
        if (comboBook.getValue() == null){
            Alert.trigger(AlertType.WARNING,"Select book that return !");
            return;
        } else if (comboMember.getValue()==null) {
            Alert.trigger(AlertType.WARNING,"Select member !");
            return;
        }else {
//            All Ok
            ReturnBook returnBook = new ReturnBook(
                    comboMember.getValue().toString(),
                    comboBook.getValue().toString(),
                    null,
                   null);

            Boolean isDeleted = service.deleteReturnRecord(returnBook);
            if (isDeleted){
                Alert.trigger(AlertType.INFORMATION,"Return Book Deleted Successfully !");
                return;
            }
            Alert.trigger(AlertType.ERROR,"Return Book delete Failed !");
        }
    }

    public void searchOnActionBtn(ActionEvent actionEvent) {
        if (txtBookId.getText().isEmpty()){
            Alert.trigger(AlertType.WARNING,"Enter Book Name !");
            return;
        } else if (txtMemberId.getText().isEmpty()) {
            Alert.trigger(AlertType.WARNING,"Enter Member Name !");
            return;
        }
    }
}
