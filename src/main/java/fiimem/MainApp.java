package fiimem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApp {
    //private static final String DB_FILE = "FIIMEM.db";

    private static final String USER = "FIIMEM";
    private static final String PASSWORD = "FIIMEM";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    
    private static Connection fiimemDBConnection;

    public static void createNewDatabase() {
        
        try (Connection conn = DriverManager.getConnection(URL,USER,PASSWORD)) {
            if (conn != null) {
                System.out.printf("[debug][createNewDatabase] Database \"%s\" created!\n", "fiimem");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getDBConnection() {
        if (fiimemDBConnection == null) {
            try {
                fiimemDBConnection = DriverManager.getConnection(URL,USER,PASSWORD);
            } catch (SQLException e) {
                System.out.printf("[error][getDBConnection] %s\n", e.getMessage());
            }
        }
        return fiimemDBConnection;
    }

    public static void main(String[] args) {

        SpringApplication.run(MainApp.class, args);
    }
}
