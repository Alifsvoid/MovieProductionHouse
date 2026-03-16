module com.spring26.section2.group13.movieproductionhouse {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.spring26.section2.group13.movieproductionhouse to javafx.fxml;
    exports com.spring26.section2.group13.movieproductionhouse;
}