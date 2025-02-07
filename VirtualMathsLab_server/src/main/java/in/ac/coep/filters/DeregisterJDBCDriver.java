package in.ac.coep.filters;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class DeregisterJDBCDriver {
    
    @PreDestroy
    public void cleanup() { System.out.println("cleaning process");
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistered JDBC driver: " + driver);
            } catch (Exception e) {
                System.err.println("Error deregistering JDBC driver: " + e);
            }
        }
    }
}
