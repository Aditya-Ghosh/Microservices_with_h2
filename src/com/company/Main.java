package com.company;
import java.sql.*;

public class Main {


    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "sa";

    public static void main(String[] args) {
	// write your code here
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // STEP 3: Execute a query
            //System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT oid, cid, ticker, units FROM orders";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
//            while(rs.next()) {
//                // Retrieve by column name
//                int oid  = rs.getInt("oid");
//                int cid = rs.getInt("cid");
//                String ticker = rs.getString("ticker");
//                int units = rs.getInt("units");
//
//                // Display values
//                System.out.print("ID: " + oid);
//                System.out.print(", Age: " + cid);
//                System.out.print(", ticker: " + ticker);
//                System.out.println(", units: " + units);
//            }
            validation.validate(rs,conn);
            // STEP 5: Clean-up environment
            rs.close();
        } catch(Exception se) {
            // Handle errors for JDBC
            se.printStackTrace();
        }// Handle errors for Class.forName
        finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException ignored) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("All records read");
    }
}
