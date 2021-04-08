package com.company;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class validation {
    static void validate(ResultSet rs, Connection conn) throws SQLException {
        //System.out.println("lol");
        while(rs.next()) {
            int oid = rs.getInt("oid");
            int cid = rs.getInt("cid");
            String ticker = rs.getString("ticker");
            int units = rs.getInt("units");

            Statement stmt = conn.createStatement();

            String sql = "SELECT price FROM tickers where ticker=\'"+ticker+"\'";
            ResultSet rs2 = stmt.executeQuery(sql);
            rs2.next();
            int price=rs2.getInt("price");

            String sql2 = "SELECT balance FROM custumers where cid="+cid;
            ResultSet rs3 = stmt.executeQuery(sql2);
            rs3.next();
            int balance=rs3.getInt("balance");

            if(balance>=price*units){
                System.out.println("Order "+oid+" successful");
            }
            else{
                System.out.println("Order "+oid+" failed");
            }
        }
    }
}
