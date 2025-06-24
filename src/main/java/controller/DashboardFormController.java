package controller;

import database.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        URL resource = this.getClass().getResource("/view/overview.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);

//        ----------------------------------------------------------------------------------------
        setDateAndTime();
    }

    public Label windowHeaderLbl;

    @FXML
    private Label adminName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane root;


    public void setAdminName(String name) {
        String[] fname = name.split(" ");
        adminName.setText(fname[0].toUpperCase());
    }

    private void setDateAndTime() {
        //        -------------------DATE--------------------
        lblDate.setText(String.valueOf(LocalDate.now()));

        //        -------------------TIME--------------------

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, e -> {
                    LocalTime now = LocalTime.now();
                    lblTime.setText(now.getHour() + " h" + " : " + now.getMinute() + " m" + " : " + now.getSecond() + " s");
                }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void addBookOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Add Books");
        URL resource = this.getClass().getResource("/view/bookAddForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);

    }

    @FXML
    void authorFormOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Manage Authors");
        URL resource = this.getClass().getResource("/view/authorManageForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }

    @FXML
    void delayedReturnOverVuewOnActionBtn(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/delayReturnForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Delay Return and Make Fines");
        stage.show();
    }

    @FXML
    void issueBookReportOnActionBtn(ActionEvent event) throws JRException {
        JasperDesign design = JRXmlLoader.load("src/main/resources/reports/issued_book_report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        try {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
//            JasperExportManager.exportReportToPdfFile(jasperPrint, "issue_book.pdf");
            JasperViewer.viewReport(jasperPrint, false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void issuebookOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Issue Books");
        URL resource = this.getClass().getResource("/view/bookIssueForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }

    @FXML
    void makeFineOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Manage Books Fines");
        URL resource = this.getClass().getResource("/view/fineManageForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }

    @FXML
    void manageBookOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Manage Books");
        URL resource = this.getClass().getResource("/view/bookManageForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }

    @FXML
    void manageCategoryOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Manage Book Categories");
        URL resource = this.getClass().getResource("/view/categoryManageForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }

    @FXML
    void manageIssuebookOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Manage Issued Books");
        URL resource = this.getClass().getResource("/view/issuedBookManageForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }

    @FXML
    void manageMemberOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Manage Members");
        URL resource = this.getClass().getResource("/view/memberManageForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }

    @FXML
    void manageReturnBookOnActionBtn(ActionEvent event) {
        windowHeaderLbl.setText("Manage Return Books");
        URL resource = this.getClass().getResource("/view/bookReturnForm.fxml");

        assert resource != null;

        Parent load = null;
        try {
            load = FXMLLoader.load(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.root.getChildren().clear();
        this.root.getChildren().add(load);
    }
}
