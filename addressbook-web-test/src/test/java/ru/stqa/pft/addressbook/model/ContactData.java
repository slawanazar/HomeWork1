package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final int id;
    private final String firstName;
    private final String address;
    private final String mobile;
    private final String email;
    private String group;

    public ContactData(int id, String firstName, String address, String mobile, String email, String group) {
        this.id = id;
        this.firstName = firstName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public ContactData(String firstName, String address, String mobile, String email, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getMobile() {
        return mobile;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "ContactData{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
