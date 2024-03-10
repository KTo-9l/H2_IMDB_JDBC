package org.example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        IRepo<Clients> clientsRepo = new ClientsRepo();
        IRepo<Stylists> stylistsRepo = new StylistsRepo();

        List<Clients> clients;
        List<Stylists> stylists;

        stylistsRepo.insert(new Stylists(null, "Bob", false));
        stylistsRepo.insert(new Stylists(null, "Bruce", false));
        stylistsRepo.insert(new Stylists(null, "Iris", false));
        stylists = stylistsRepo.getList();

        clientsRepo.insert(new Clients(null, "Lamin", stylists.get(1), false));
        clientsRepo.insert(new Clients(null, "Goobka", stylists.get(0), false));
        clientsRepo.insert(new Clients(null, "Phantom", stylists.get(2), false));
        clients = clientsRepo.getList();

        DBWork.printClients(clients); // Print information about clients
        DBWork.printStylists(stylists); // Print information about stylists
        DBWork.printClientsStylists(clients); // Print information about client-stylist
    }
}