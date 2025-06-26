package util;

import alert.Alert;
import alert.AlertType;
import database.DBConnection;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.io.File;
import java.sql.SQLException;

public class Report {
    public static void openReport(String jasperFxmlFileName) {
        try {
            // Load and compile the report design
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/" + jasperFxmlFileName);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
                    DBConnection.getInstance().getConnection()
            );

            // Show the report in JasperViewer
            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace(); // Optional: helpful during development
            Alert.trigger(AlertType.WARNING, "Failed to open the report. Please try again later.");
        }
    }


    public static void downloadReport(String jasperFxmlFileName, String pdfName) {
        try {
            // Load and compile the report
            JasperDesign design = JRXmlLoader.load("src/main/resources/reports/" + jasperFxmlFileName);
            JasperReport jasperReport = JasperCompileManager.compileReport(design);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
                    DBConnection.getInstance().getConnection()
            );

            // Let user choose the destination file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF Report");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("PDF Files", "*.pdf")
            );

            // Set initial file name (default to "report.pdf" if null or empty)
            String defaultFileName = (pdfName != null && !pdfName.isBlank()) ? pdfName : "report.pdf";
            fileChooser.setInitialFileName(defaultFileName);

            // Show save dialog
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
                Alert.trigger(AlertType.INFORMATION, "Report Downloaded successfully !");
            }

        } catch (SQLException | JRException e) {
            e.printStackTrace(); // Useful for debugging
            Alert.trigger(AlertType.WARNING, "Failed to download the report!");
        }
    }
}
