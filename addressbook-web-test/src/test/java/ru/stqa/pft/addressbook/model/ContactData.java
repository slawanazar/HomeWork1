package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private String firstName;
    private String address;
    private String mobile;
    private String email;
    private String group;
    private String lastName;

//    public ContactData(int id, String firstName, String lastName, String address, String mobile, String email) {
//        this.id = id;
//        this.firstName = firstName;
//        this.address = address;
//        this.mobile = mobile;
//        this.email = email;
//        this.group = group;
//        this.lastName = lastName;
//    }
//
//    public ContactData(String firstName, String lastName, String address, String mobile, String email, String group) {
//        this.id = Integer.MAX_VALUE;
//        this.firstName = firstName;
//        this.address = address;
//        this.mobile = mobile;
//        this.email = email;
//        this.group = group;
//        this.lastName = lastName;
//    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstName = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastName = lastname;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
//        int result = firstName != null ? firstName.hashCode() : 0;
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}