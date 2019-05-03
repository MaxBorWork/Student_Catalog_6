package by.borisevich.studentCatalog.model;

public class Student {

    private int id;
    private String surname;
    private String name;
    private String secondName;
    private int groupNum;
    private String city;
    private Address address;

    public Student(int id, String surname, String name, String secondName, int groupNum, String city, Address address) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.groupNum = groupNum;
        this.city = city;
        this.address = address;
    }

    public Student(String surname, String name, String secondName, int groupNum, String city, Address address) {
        this.surname = surname;
        this.name = name;
        this.secondName = secondName;
        this.groupNum = groupNum;
        this.city = city;
        this.address = address;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getGroupNum() {
        return groupNum;
    }

    public void setGroupNum(int groupNum) {
        this.groupNum = groupNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
