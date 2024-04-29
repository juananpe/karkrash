module eus.ehu.karkrash {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires org.apache.logging.log4j;

    opens eus.ehu.karkrash.model to javafx.base;
    opens eus.ehu.karkrash.domain to org.hibernate.orm.core;

    exports eus.ehu.karkrash.model to org.hibernate.orm.core;
    opens eus.ehu.karkrash.ui to javafx.fxml;
    exports eus.ehu.karkrash.ui;
}
