module com.spring26.section2.group13.movieproductionhouse {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens com.spring26.section2.group13.movieproductionhouse to javafx.fxml;
    exports com.spring26.section2.group13.movieproductionhouse;

    opens com.spring26.section2.group13.movieproductionhouse.alif to javafx.fxml;
    exports com.spring26.section2.group13.movieproductionhouse.alif;
}