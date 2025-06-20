package controller;

import database.DBConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.ServiceFactory;
import service.custom.DashboardService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public Label bookCountLbl;
    public Label memberCountLbl;
    public Label lblDate;
    public Label lblTime;
    public Label issuedBookCountLbl;
    public Label authorCountLbl;

    DashboardService dashboardService = ServiceFactory.getInstance().getServiceType(ServiceType.DASHBOARD);

    public LineChart<String, Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDatabaseBookStatus();
        loadChartData();
        setDateAndTime();
        setDashboardData();
    }

    private void updateDatabaseBookStatus() {
        Boolean isStatusUpdate = dashboardService.updateBookStatus();
        if (isStatusUpdate) {
            System.out.println("All book status loaded & Updated !");
            return;
        }
        System.out.println("Data not found !");
    }

    private void setDashboardData() {
        bookCountLbl.setText(String.valueOf(dashboardService.getBookCount()));
        authorCountLbl.setText(String.valueOf(dashboardService.getAuthorCount()));
        memberCountLbl.setText(String.valueOf(dashboardService.getMemberCount()));
        issuedBookCountLbl.setText(String.valueOf(dashboardService.getIssuedBookCount()));
    }

    private void loadChartData() {
        lineChart.getData().clear(); // removes old series
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Library Stats");

        series.getData().add(new XYChart.Data<>("Members", dashboardService.getMemberCount()));
        series.getData().add(new XYChart.Data<>("Authors", dashboardService.getAuthorCount()));
        series.getData().add(new XYChart.Data<>("Books", dashboardService.getBookCount()));
        series.getData().add(new XYChart.Data<>("Issued Books", dashboardService.getIssuedBookCount()));

        lineChart.getData().add(series);
    }

    public void addBookOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/bookAddForm.fxml"))));
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Add Book Form");
        stage.show();
    }

    public void manageBookOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/bookManageForm.fxml"))));
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Manage Book Form");
        stage.show();
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

    public void logoutOnActionBtn(ActionEvent actionEvent) {
    }

    public void reloadDasshboardOnActionBtn(ActionEvent actionEvent) {
        loadChartData();
        setDateAndTime();
        setDashboardData();
    }

    public void issuebookOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/bookIssueForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Issue Book Form");
        stage.show();
    }

    public void manageIssuebookOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/issuedBookManageForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Manage Issued Book Form");
        stage.show();
    }

    public void manageCategoryOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/categoryManageForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Manage Book Category");
        stage.show();
    }

    public void manageReturnBookOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/bookReturnForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Manage Return Books");
        stage.show();
    }

    public void manageBookFinesOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/fineManageForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Manage Fines");
        stage.show();
    }

    public void authorFormOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/authorManageForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Manage Authors");
        stage.show();
    }

    public void manageMemberOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/memberManageForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Manage Members");
        stage.show();
    }

    public void delayedReturnOverVuewOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/delayReturnForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Delay Return and Make Fines");
        stage.show();
    }

    public void makeFineOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/fineManageForm.fxml"))));
        stage.setResizable(false);
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Delay Return and Make Fines");
        stage.show();
    }

    public void issueBookReportOnActionBtn(ActionEvent actionEvent) throws JRException {
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

}
