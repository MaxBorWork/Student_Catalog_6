package by.borisevich.studentCatalog.model;

public class Address {

    private int id;
    private String street;
    private String house;
    private String flat;

    public Address(int id, String street, String house, String flat) {
        this.id = id;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public Address(String street, String house, String flat) {
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public Address() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }
}
