package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    public ImageView logoutBtnView;
    public Label issuedBookCountLbl;
    public Label authorCountLbl;
    DashboardService dashboardService = ServiceFactory.getInstance().getServiceType(ServiceType.DASHBOARD);

    public LineChart<String, Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadChartData();
        setDateAndTime();
        setDashboardData();
    }

    private void setDashboardData() {
        bookCountLbl.setText(String.valueOf(dashboardService.getBookCount()));
        authorCountLbl.setText(String.valueOf(dashboardService.getAuthorCount()));
        memberCountLbl.setText(String.valueOf(dashboardService.getMemberCount()));
        issuedBookCountLbl.setText(String.valueOf(dashboardService.getIssudedBookCount()));
    }

    private void loadChartData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Library Stats");

        series.getData().add(new XYChart.Data<>("Members", dashboardService.getMemberCount()));
        series.getData().add(new XYChart.Data<>("Books", dashboardService.getBookCount()));

        lineChart.getData().add(series);
    }

    public void addBookOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/addBookForm.fxml"))));
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Add Book Form");
        stage.show();
    }

    public void manageBookOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/manageBookForm.fxml"))));
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Add Book Form");
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
}
