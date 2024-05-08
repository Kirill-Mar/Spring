package web.config.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("************** Starting up! **************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("************** Shutting down! **************");
        System.out.println("Destroying Context...");
        System.out.println("Calling SQL AbandonedConnectionCleanupThread checkedShutdown");

        Enumeration<java.sql.Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            java.sql.Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                System.out.println("Deregistering JDBC driver " + driver);
            } catch (SQLException ex) {
                System.out.println("Error deregistering JDBC driver " + driver);
                ex.printStackTrace();
            }
        }
    }
}
