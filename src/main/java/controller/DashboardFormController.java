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
import java.util.ResourceBundle;

public class DashboardFormController implements Initializable {

    public Label bookCountLbl;
    public Label memberCountLbl;
    DashboardService dashboardService = ServiceFactory.getInstance().getServiceType(ServiceType.DASHBOARD);

    public LineChart<String, Number> lineChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadChartData();
        setDashboardData();
    }

    private void setDashboardData(){
        bookCountLbl.setText(String.valueOf(dashboardService.getBookCount()));
        memberCountLbl.setText(String.valueOf(dashboardService.getMemberCount()));
    }

    private void loadChartData() {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Library Stats");

        // Static data — replace with dynamic data if needed
        series.getData().add(new XYChart.Data<>("Members", dashboardService.getBookCount()));
        series.getData().add(new XYChart.Data<>("Books", dashboardService.getMemberCount()));

        lineChart.getData().add(series);
    }

    public void addBookOnActionBtn(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/addBookForm.fxml"))));
        stage.getIcons().add(new Image("/image/stageicon.png"));
        stage.setTitle("Add Book Form");
        stage.show();
    }
}
