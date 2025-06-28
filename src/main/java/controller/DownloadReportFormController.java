package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.Report;

public class DownloadReportFormController {

    // -------- Open Reports -------- //

    @FXML
    void bookReportActionBtn(ActionEvent event) {
        openReport("book_report.jrxml");
    }

    @FXML
    void categoryReportOnActionBtn(ActionEvent event) {
        openReport("category_report.jrxml");
    }

    @FXML
    void authorReportActionBtn(ActionEvent event) {
        openReport("author_report.jrxml");
    }

    @FXML
    void memberReportActionBtn(ActionEvent event) {
        openReport("members_report.jrxml");
    }

    @FXML
    void issuedBookReportActionBtn(ActionEvent event) {
        openReport("issued_book_report.jrxml");
    }

    @FXML
    void fineReportOnActionBtn(ActionEvent event) {
        openReport("fine_report.jrxml");
    }

    @FXML
    void nonPaidMemberReportOnActionBtn(ActionEvent event) {
        openReport("nonpaidDelay_report.jrxml");
    }

    // -------- Download Reports -------- //
    @FXML
    void downloadBookOnActionBtn(ActionEvent event) {
        downloadReport("book_report.jrxml", "book-report.pdf");
    }

    @FXML
    void downloadCatergoryActionBtn(ActionEvent event) {
        downloadReport("category_report.jrxml", "category-report.pdf");
    }

    @FXML
    void downloadAuthorActionBtn(ActionEvent event) {
        downloadReport("author_report.jrxml", "author-report.pdf");
    }

    @FXML
    void downloadMemberActionBtn(ActionEvent event) {
        downloadReport("members_report.jrxml", "member-report.pdf");
    }

    @FXML
    void downloadIssueBookActionBtn(ActionEvent event) {
        downloadReport("issued_book_report.jrxml", "issueBook-report.pdf");
    }

    @FXML
    void downloadFinectionBtn(ActionEvent event) {
        downloadReport("fine_report.jrxml", "fine-report.pdf");
    }

    @FXML
    void downloadNonPaidActionBtn(ActionEvent event) {
        downloadReport("nonpaidDelay_report.jrxml", "nonpaid-report.pdf");
    }

    // -------- Reusable Private Methods -------- //

    private void openReport(String fileName) {
        Report.openReport(fileName);
    }

    private void downloadReport(String fileName, String outputName) {
        Report.downloadReport(fileName, outputName);
    }
}
