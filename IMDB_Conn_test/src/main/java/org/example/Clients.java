package org.example;

public class Clients {
    private Integer id;
    private String name;
    private Stylists idStylists;
    private boolean isDeleted;
    public Clients(Integer id, String name, Stylists idStylists, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.idStylists = idStylists;
        this.isDeleted = isDeleted;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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
    public boolean isDeleted() {
        return isDeleted;
    }
    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
