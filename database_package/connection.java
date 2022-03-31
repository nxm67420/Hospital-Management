//Packages
package database_package;

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

//Main()
public class connection {

    // Variables
    public static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_management?autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    /* public static String url = "jdbc:mysql://localhost:3306/";
    public static String ssl = "?autoReconnect=true&useSSL=false";
    public static String dbName = "hospital_management"; */
    private static String user = "root";
    public static String password = "";
    public static String url = "";
    //protected
    public static Connection con;
    
    //MySQL Variables 
    String stmt;
    PreparedStatement statement;
    ResultSet resultSet;

    public connection() {}

    public connection(String url, String userName, String password) {
        this.url = url;
        this.user = userName;
        this.password = password;
        System.out.println("Connection Created");
    }

    //Method()
    public static Connection Connect() {
        try {
            System.out.println("In initializeJdbc");
            // Load the JDBC driver
            // com.mysql.jdbc.Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish Connection
            // jdbc:mysql://localhost:3306/Peoples?autoReconnect=true&useSSL=false
            con = DriverManager.getConnection(DB_URL, user, password);
            System.out.println("Database connected");
        } 
        catch (Exception e) {
            System.out.print("Error : " + e.getMessage());
        }
        return con;
    }

    public static void main(String[] args) {
        System.out.println(Connect().toString() + " is what was returned");
    }
}
