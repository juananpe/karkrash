module eus.ehu.sharetrip {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.apache.logging.log4j;

    opens eus.ehu.template.domain to org.hibernate.orm.core;
    opens eus.ehu.template.ui to javafx.fxml;
    exports eus.ehu.template.ui;
}
