package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import util.Report;

public class downloadReportFormController {

//    Open reports

    @FXML
    void bookReportActionBtn(ActionEvent event) {
        Report.openReport("book_report.jrxml");
    }

    @FXML
    void categoryReportOnActionBtn(ActionEvent event) {
        Report.openReport("category_report.jrxml");
    }

    @FXML
    void authorReportActionBtn(ActionEvent event) {
        Report.openReport("author_report.jrxml");
    }

    @FXML
    void memberReportActionBtn(ActionEvent event) {
        Report.openReport("members_report.jrxml");
    }

    @FXML
    void issuedBookReportActionBtn(ActionEvent event) {
        Report.openReport("issued_book_report.jrxml");
    }

    @FXML
    void fineReportOnActionBtn(ActionEvent event) {
        Report.openReport("fine_report.jrxml");
    }

    @FXML
    void nonPaidMemberReportOnActionBtn(ActionEvent event) {
        Report.openReport("nonpaidDelay_report.jrxml");
    }


    //    Download reports

    public void downloadBookOnActionBtn(ActionEvent actionEvent) {
        Report.downloadReport("book_report.jrxml", "book-report.pdf");
    }

    public void downloadCatergoryActionBtn(ActionEvent actionEvent) {
        Report.downloadReport("category_report.jrxm", "category-report.pdf");
    }

    public void downloadAuthorActionBtn(ActionEvent actionEvent) {
        Report.downloadReport("author_report.jrxml", "author-report.pdf");
    }

    public void downloadMemberActionBtn(ActionEvent actionEvent) {
        Report.downloadReport("members_report.jrxml", "member-report.pdf");
    }

    public void downloadIssueBookActionBtn(ActionEvent actionEvent) {
        Report.downloadReport("issued_book_report.jrxml", "issueBook-report.pdf");
    }

    public void downloadFinectionBtn(ActionEvent actionEvent) {
        Report.downloadReport("fine_report.jrxml", "fine-report.pdf");
    }

    public void downloadNonPaidActionBtn(ActionEvent actionEvent) {
        Report.downloadReport("nonpaidDelay_report.jrxml", "nonpaid-report.pdf");
    }
}
