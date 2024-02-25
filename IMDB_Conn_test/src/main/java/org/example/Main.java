package org.example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:beautysaloon", "sa", "");
        if (conn==null) {
            System.out.println("Error with connection with DataBase!");
            System.exit(0);
        }

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Clients AS cl JOIN Stylists AS st ON cl.IDSTYLISTS = st.ID");

        List<Clients> clients = new ArrayList<>();
        List<Stylists> stylists = new ArrayList<>();

        while(rs.next()) {
            Stylists tmpStylist = new Stylists(rs.getInt("Stylists.id"), rs.getString("Stylists.name"));
            clients.add(new Clients(rs.getInt("id"), rs.getString("name"), tmpStylist));
            stylists.add(tmpStylist);
        }
        stmt.close();

        for (Clients client : clients) {
            DBWork.printClient(client); // Print information about clients
        }

        for (Stylists stylist : stylists) {
            DBWork.printStylist(stylist); // Print information about stylists
        }

        for (Clients client : clients) {
            DBWork.printClientStylist(client); // Print information about client-stylist
        }
    }
}