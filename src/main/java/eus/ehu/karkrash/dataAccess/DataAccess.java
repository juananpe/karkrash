package eus.ehu.karkrash.dataAccess;

import eus.ehu.karkrash.configuration.Config;
import eus.ehu.karkrash.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;


/**
 * Implements the Data Access utility to the objectDb database
 */
public class DataAccess {

  private Config config = Config.getInstance();

  protected EntityManager db;
  protected EntityManagerFactory emf;

  public DataAccess() {

    this.open();

  }

  public void open() {
    open(false);
  }


  public void open(boolean initializeMode) {


    System.out.println("Opening DataAccess instance => isDatabaseLocal: " +
            config.isDataAccessLocal() + " getDatabBaseOpenMode: " + config.getDataBaseOpenMode());


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
    String fileName = config.getDatabaseName();

    fileName = fileName + ";drop";
    System.out.println("Deleting the DataBase");

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
    // Creating some offices
    Office office1 = new Office();
    office1.setName("Donostia");
    office1.setTown("San Sebastián");
    office1.setAddress("123 Main Street");
    office1.setPhone("+34900001111");
    office1.setEmail("donostia@karkrash.com");

    Office office2 = new Office();
    office2.setName("Bilbao");
    office2.setTown("Bilbao");
    office2.setAddress("456 Side Avenue");
    office2.setPhone("+34900112233");
    office2.setEmail("bilbao@karkrash.com");

    // Persist offices
    db.persist(office1);
    db.persist(office2);

    // Creating some vehicle types
    VehicleType type1 = new VehicleType();
    type1.setBrand("Audi");
    type1.setModel("A4");

    VehicleType type2 = new VehicleType();
    type2.setBrand("Renault");
    type2.setModel("Clio");

    // Persist vehicle types
    db.persist(type1);
    db.persist(type2);

    // Creating some vehicles
    Vehicle vehicle1 = new Vehicle();
    vehicle1.setLicensePlate("1234ABC");
    vehicle1.setState(VehicleState.AVAILABLE);
    vehicle1.setVehicleType(type1);

    Vehicle vehicle2 = new Vehicle();
    vehicle2.setLicensePlate("5678DEF");
    vehicle2.setState(VehicleState.AVAILABLE);
    vehicle2.setVehicleType(type2);

    // Persist vehicles
    db.persist(vehicle1);
    db.persist(vehicle2);

    // Creating some tariffs
    Tariff tariff1 = new Tariff();
    tariff1.setCode("T001");
    tariff1.setPricePerDay(50.0);

    LocalDate localStartDate = LocalDate.of(2024, 4, 28);
    Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    LocalDate localEndDate = LocalDate.of(2024, 5, 20);
    Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    tariff1.setStartDate(startDate); // Use appropriate date
    tariff1.setEndDate(endDate); // Use appropriate date
    tariff1.setPriceExtraKm(0.10);
    tariff1.setMaxKmPerDay(100);
    tariff1.setOffice(office1);

    Tariff tariff2 = new Tariff();
    tariff2.setCode("T002");
    tariff2.setPricePerDay(30.0);

    localStartDate = LocalDate.of(2024, 6, 28);
    startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    localEndDate = LocalDate.of(2024, 7, 20);
    endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    tariff2.setStartDate(startDate); // Use appropriate date
    tariff2.setEndDate(endDate); // Use appropriate date
    tariff2.setPriceExtraKm(0.05);
    tariff2.setMaxKmPerDay(150);
    tariff2.setOffice(office2);

    // Persist tariffs
    db.persist(tariff1);
    db.persist(tariff2);

    // Creating some clients
    Client client1 = new Client();
    client1.setDni("12345678A");
    client1.setUsername("john_doe");
    client1.setPassword("secret");
    client1.setCreditCard("1111222233334444");
    client1.setEmail("john.doe@example.com");
    client1.setName("John Doe");

    Client client2 = new Client();
    client2.setDni("87654321B");
    client2.setUsername("jane_doe");
    client2.setPassword("supersecret");
    client2.setCreditCard("5555666677778888");
    client2.setEmail("jane.doe@example.com");
    client2.setName("Jane Doe");

    // Persist clients
    db.persist(client1);
    db.persist(client2);
  }

  public void close() {
    db.close();
    System.out.println("DataBase is closed");
  }

  public List<Office> getOffices() {
    return db.createQuery("SELECT o FROM Office o", Office.class).getResultList();
  }

  public List<String> getModels() {
    return db.createQuery("SELECT DISTINCT v.vehicleType.model FROM Vehicle v", String.class).getResultList();
  }

  public List<String> getBrands() {
    return db.createQuery("SELECT DISTINCT v.vehicleType.brand FROM Vehicle v", String.class).getResultList();
  }
}
