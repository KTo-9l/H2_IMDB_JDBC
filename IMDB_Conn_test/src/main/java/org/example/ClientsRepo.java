package org.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientsRepo implements IRepo<Clients> {
    @Override
    public void insert(Clients client) throws SQLException {
        StylistsRepo stylistsRepo = new StylistsRepo();
        stylistsRepo.insert(client.getIdStylists());
        String str = String.format("INSERT INTO clients (id, name, idStylists) VALUES (%s, '%s', %s)",
                client.getId(),
                client.getName(),
                client.getIdStylists().getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void delete(Clients client) throws SQLException {
        String str = String.format("DELETE  FROM clients where id = %s" , client.getId());
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public void update(Clients client, int id) throws SQLException {
        String str = String.format("UPDATE clients SET id = %s, name = '%s', idStylists = %s WHERE id = %s" ,
                client.getId(),
                client.getName(),
                client.getIdStylists().getId(),
                id);
        Statement stmt = this.getStatement(this.connectToDB());
        stmt.execute(str);
        stmt.close();
    }
    @Override
    public List<Clients> getList() throws SQLException {
        Statement stmt = this.getStatement(this.connectToDB());
        ResultSet rs = stmt.executeQuery("SELECT cl.id, cl.name, cl.idStylists, st.id, st.name FROM Clients AS cl JOIN Stylists AS st ON cl.IDSTYLISTS = st.ID");

        List<Clients> clients = new ArrayList<>();
        while(rs.next()) {
            clients.add(new Clients(rs.getInt("id"),
                    rs.getString("name"),
                    new Stylists(rs.getInt("Stylists.id"), rs.getString("Stylists.name"))));
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
