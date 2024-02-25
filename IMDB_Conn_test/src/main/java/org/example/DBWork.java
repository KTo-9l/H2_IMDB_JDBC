package org.example;

public class DBWork {
    public static void printStylist(Stylists stylist) {
        System.out.println(stylist.getId() + " " + stylist.getName());
    }
    public static void printClient(Clients client) {
        System.out.println(client.getId() + " " + client.getName() + " " + client.getIdStylists().getName());
    }
    public static void printClientStylist(Clients client) {
        System.out.print(client.getId() + " " + client.getName() + " ");
        printStylist(client.getIdStylists());
    }
}
