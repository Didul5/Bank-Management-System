package ASimulatorSystem;

import java.sql.*;

public class Conn {
    
    // Define the JDBC connection parameters
    Connection c;
    Statement s;

    public Conn() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem", "root", "mam_pustika");

            // Create a statement to execute SQL queries
            s = c.createStatement();

        } catch (Exception e) {
            // Print any connection or SQL errors
            e.printStackTrace();
        }
    }
}

