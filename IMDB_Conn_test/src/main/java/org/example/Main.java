package org.example;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        IRepo<Clients> clientsRepo = new ClientsRepo();
        IRepo<Stylists> stylistsRepo = new StylistsRepo();

        List<Clients> clients = clientsRepo.getList();
        List<Stylists> stylists = stylistsRepo.getList();

        for (Clients client : clients) {
            DBWork.printClient(client); // Print information about clients
        }

        for (Stylists stylist : stylists) {
            DBWork.printStylist(stylist); // Print information about stylists
        }

        for (Clients client : clients) {
            DBWork.printClientStylist(client); // Print information about client-stylist
        }

        clientsRepo.update(new Clients(1, "Lee", new Stylists(2, "Bruce")), 1);
//        clientsRepo.delete(clients.get(2));
//        clientsRepo.insert(new Clients(3, "Someone", new Stylists(4, "Testovik")));
        for (Clients client : clients) {
            DBWork.printClient(client); // Print information about clients
        }

    }
}