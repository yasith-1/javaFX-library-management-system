package controller;

import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.DashboardOverviewService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardOverviewController implements Initializable {

    public Label bookCountLbl;
    public Label memberCountLbl;
    public Label issuedBookCountLbl;
    public Label authorCountLbl;

//    DashboardOverviewService service = ServiceFactory.getInstance().getServiceType(ServiceType.DASHBOARD);
    @Inject
    DashboardOverviewService service;

    public LineChart<String, Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updateDatabaseBookStatus();
        loadChartData();
        setDashboardData();
    }

    private void updateDatabaseBookStatus() {
        Boolean isStatusUpdate = service.updateBookStatus();
        if (isStatusUpdate) {
            System.out.println("All book status loaded & Updated !");
            return;
        }
        System.out.println("Data not found !");
    }

    private void setDashboardData() {
        bookCountLbl.setText(String.valueOf(service.getBookCount()));
        authorCountLbl.setText(String.valueOf(service.getAuthorCount()));
        memberCountLbl.setText(String.valueOf(service.getMemberCount()));
        issuedBookCountLbl.setText(String.valueOf(service.getIssuedBookCount()));
    }

    private void loadChartData() {
        lineChart.getData().clear(); // removes old series
        lineChart.setLegendSide(Side.TOP); // Move legend to top to avoid overlap

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Library Stat");

        series.getData().add(new XYChart.Data<>("Members", service.getMemberCount()));
        series.getData().add(new XYChart.Data<>("Authors", service.getAuthorCount()));
        series.getData().add(new XYChart.Data<>("Books", service.getBookCount()));
        series.getData().add(new XYChart.Data<>("Issued Books", service.getIssuedBookCount()));

        lineChart.getData().add(series);
    }

    @FXML
    void logoutOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/adminSignupForm.fxml"))));
        stage.setTitle("Register Form");
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.show();
    }

    @FXML
    void reloadDasshboardOnActionBtn(ActionEvent event) {
        loadChartData();
        setDashboardData();
    }
}
