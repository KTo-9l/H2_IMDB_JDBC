package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StylistsRepo implements IRepo<Stylists> {
    @Override
    public Integer insert(Stylists stylist) throws SQLException {
        String str = String.format("INSERT INTO stylists (name, isDeleted) VALUES ('%s', %s)",
                stylist.getName(),
                stylist.isDeleted());
        this.executeRequest(str);
        try (ResultSet rs = this.getStatement(this.connectToDB()).executeQuery("SELECT MAX(id) FROM Stylists")) {
            while (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        }
    }
    @Override
    public void delete(Stylists stylists) throws SQLException {
        String str = String.format("UPDATE Stylists SET isDeleted = true WHERE id = %s" , stylists.getId());
        this.executeRequest(str);
    }
    @Override
    public void update(Stylists stylists, int id) throws SQLException {
        String str = String.format("UPDATE stylists SET id = %s, name = '%s' WHERE id = %s" ,
                stylists.getId(),
                stylists.getName(),
                stylists.getId());
        this.executeRequest(str);
    }
    @Override
    public void executeRequest(String request) throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(request);
        stmt.close();
    }
    @Override
    public List<Stylists> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet rs = stmt.executeQuery("SELECT st.id, st.name, st.isDeleted FROM Stylists AS st");

        List<Stylists> stylists = new ArrayList<>();
        while(rs.next()) {
            Stylists tmpStylist = new Stylists(
                    rs.getInt("Stylists.id"),
                    rs.getString("Stylists.name"),
                    rs.getBoolean("Stylists.isDeleted")
            );
            stylists.add(tmpStylist);
        }
        this.closeConnection(stmt);
        return stylists;
    }
    @Override
    public Connection connectToDB() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:beautysaloon", "sa", "");
        if (conn==null) {
            System.out.println("Error with connection with DataBase!");
            System.exit(0);
        }
        return conn;
    }
    @Override
    public Statement getStatement(Connection conn) throws SQLException {
        return conn.createStatement();
    }
    public void closeConnection(Statement stmt) throws SQLException {
        stmt.close();
    }
}
