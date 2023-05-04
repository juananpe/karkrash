package eus.ehu.template.dataAccess;

import eus.ehu.template.configuration.Config;
import eus.ehu.template.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


/**
 * Implements the Data Access utility to the objectDb database
 */
public class DataAccess {

  protected EntityManager db;
  protected EntityManagerFactory emf;

  public DataAccess() {

    this.open();

  }

  public void open() {
    open(false);
  }


  public void open(boolean initializeMode) {

    Config config = Config.getInstance();

    System.out.println("Opening DataAccess instance => isDatabaseLocal: " +
            config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());

    String fileName = config.getDatabaseName();
    if (initializeMode) {
      fileName = fileName + ";drop";
      System.out.println("Deleting the DataBase");
    }

    if (config.isDataAccessLocal()) {
      final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
              .configure() // configures settings from hibernate.cfg.xml
              .build();
      try {
        emf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
      } catch (Exception e) {
        // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
        // so destroy it manually.
        StandardServiceRegistryBuilder.destroy(registry);
      }

      db = emf.createEntityManager();
      System.out.println("DataBase opened");
    }
  }



  public void reset() {
    db.getTransaction().begin();
//    db.createQuery("DELETE FROM Table").executeUpdate();
    db.getTransaction().commit();
  }

  public void initializeDB() {

    this.reset();

    try {

      db.getTransaction().begin();

      generateTestingData();

      db.getTransaction().commit();
      System.out.println("The database has been initialized");


    } catch (Exception e) {
      e.printStackTrace();
    }
  }



  private void generateTestingData() {
    // create domain entities and persist them
  }



  public void close() {
    db.close();
    System.out.println("DataBase is closed");
  }

}
