package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepo implements IRepo<Clients> {
    @Override
    public Integer insert(Clients client) throws SQLException {
        String str = String.format("INSERT INTO clients (name, idStylists, isDeleted) VALUES ('%s', '%s', %s)",
                client.getName(),
                client.getIdStylists().getId(),
                client.isDeleted());
        this.executeRequest(str);
        try (ResultSet rs = this.getStatement(this.connectToDB()).executeQuery("SELECT MAX(id) FROM Clients")) {
            while (rs.next()) {
                return rs.getInt(1);
            }
            return -1;
        }
    }
    @Override
    public void delete(Clients client) throws SQLException {
        String str = String.format("UPDATE Clients SET isDeleted = true WHERE id = %s" , client.getId());
        this.executeRequest(str);
    }
    @Override
    public void update(Clients client, int id) throws SQLException {
        String str = String.format("UPDATE Clients SET id = %s, name = '%s', idStylists = %s WHERE id = %s" ,
                client.getId(),
                client.getName(),
                client.getIdStylists().getId(),
                id);
        this.executeRequest(str);
    }
    @Override
    public void executeRequest(String request) throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(request);
        stmt.close();
    }
    @Override
    public List<Clients> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet rs = stmt.executeQuery("SELECT cl.id, cl.name, cl.idStylists, st.id, st.name, st.isDeleted, cl.isDeleted FROM Clients AS cl JOIN Stylists AS st ON cl.IDSTYLISTS = st.ID");

        List<Clients> clients = new ArrayList<>();
        while(rs.next()) {
            clients.add(new Clients(
                    rs.getInt("id"),
                    rs.getString("name"),
                    new Stylists(
                            rs.getInt("Stylists.id"),
                            rs.getString("Stylists.name"),
                            rs.getBoolean("Stylists.ISDELETED")),
                    rs.getBoolean("Clients.isDeleted")));
        }
        this.closeConnection(stmt);
        return clients;
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
