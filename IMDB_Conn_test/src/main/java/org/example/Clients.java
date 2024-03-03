package org.example;

public class Clients {
    private int id;
    private String name;
    private Stylists idStylists;

    public Clients(int id, String name, Stylists idStylists) {
        this.id = id;
        this.name = name;
        this.idStylists = idStylists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stylists getIdStylists() {
        return idStylists;
    }

    public void setIdStylists(Stylists idStylists) {
        this.idStylists = idStylists;
    }
}
