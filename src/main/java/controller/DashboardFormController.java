package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.DashboardService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public Label bookCountLbl;
    public Label memberCountLbl;
    public Label dateTimeLbl;
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

    private void updateDatabaseBookStatus(){
        Boolean isStatusUpdate = dashboardService.updateBookStatus();
        if (isStatusUpdate){
            System.out.println("All data Loaded & Updated !");
            return;
        }
        System.out.println("Data not loaded something went wrong !");
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
        LocalDateTime now = LocalDateTime.now();
        // 12-hour format with AM/PM
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy  hh:mm:ss a");
        String dateTimeNow = now.format(formatter);
        dateTimeLbl.setText(dateTimeNow);
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
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/bookCategoryManageForm.fxml"))));
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
}
