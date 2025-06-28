package util;

import alert.Alert;
import alert.AlertType;
import database.DBConnection;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.InputStream;
import java.io.File;
import java.sql.SQLException;

public class Report {

    public static void openReport(String jasperFxmlFileName) {
        try {
            // Load report from resources as InputStream (works in JAR/EXE)
            InputStream reportStream = Report.class.getResourceAsStream("/reports/" + jasperFxmlFileName);
            if (reportStream == null) {
                Alert.trigger(AlertType.WARNING, "Report file not found: " + jasperFxmlFileName);
                return;
            }

            JasperDesign design = JRXmlLoader.load(reportStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, null, DBConnection.getInstance().getConnection());

            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            Alert.trigger(AlertType.WARNING, "Failed to open the report. Please try again later.");
        }
    }

    public static void downloadReport(String jasperFxmlFileName, String pdfName) {
        try {
            InputStream reportStream = Report.class.getResourceAsStream("/reports/" + jasperFxmlFileName);
            if (reportStream == null) {
                Alert.trigger(AlertType.WARNING, "Report file not found: " + jasperFxmlFileName);
                return;
            }

            JasperDesign design = JRXmlLoader.load(reportStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport, null, DBConnection.getInstance().getConnection());

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF Report");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

            String defaultFileName = (pdfName != null && !pdfName.isBlank()) ? pdfName : "report.pdf";
            fileChooser.setInitialFileName(defaultFileName);

            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
                Alert.trigger(AlertType.INFORMATION, "Report downloaded successfully!");
            }

        } catch (SQLException | JRException e) {
            e.printStackTrace();
            Alert.trigger(AlertType.WARNING, "Failed to download the report!");
        }
    }
}
