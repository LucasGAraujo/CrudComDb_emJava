package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {
    private static final String url = "jdbc:mysql://localhost:3306/java_crud";
    private static final String user = "root";
    private static final String password = "12345";
    public static Connection createConnection() {

        try  {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Found");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not Found" + e.getMessage());
        }
        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connection OK");
            return connection;
        } catch(SQLException e){
            System.out.println("Connection NOT OK" + e.getMessage());
            return null;
        }
    }
}
