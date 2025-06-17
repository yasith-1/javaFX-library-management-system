package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReturnBook;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import service.ServiceFactory;
import service.custom.impl.IssuedBookServiceImpl;
import service.custom.impl.ReturnBookServiceImpl;
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
    public JFXTextField txtSearchField;

    ReturnBookServiceImpl service = ServiceFactory.getInstance().getServiceType(ServiceType.ISSUEDBOOK);

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
            Notifications.create()
                    .title("Warning")
                    .text("Select book that return !")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();
            return;
        } else if (comboMember.getValue()==null) {
            Notifications.create()
                    .title("Warning")
                    .text("Select member !")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showWarning();
            return;
        }else {
//            All Ok
            ReturnBook returnBook = new ReturnBook(comboMember.getValue().toString(),
                    comboBook.getValue().toString(),
                    LocalDate.now(), LocalTime.now());

            Boolean isAdded = service.addReturnRecord(returnBook);
            if (isAdded){
                Notifications.create()
                        .title("Added")
                        .text("Return Book Restocked Sucessfully !")
                        .hideAfter(Duration.seconds(3))
                        .position(Pos.BOTTOM_RIGHT)
                        .showInformation();
                return;
            }
            Notifications.create()
                    .title("Error")
                    .text("Return Book Restocked Failed !")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_RIGHT)
                    .showError();
        }
    }

    public void clearOnActionBtn(ActionEvent actionEvent) {
    }

    public void deleteOnActionBtn(ActionEvent actionEvent) {
    }

    public void searchOnActionBtn(ActionEvent actionEvent) {

    }
}
