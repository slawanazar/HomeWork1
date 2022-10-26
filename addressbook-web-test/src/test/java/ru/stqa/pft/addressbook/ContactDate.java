package ru.stqa.pft.addressbook;

public class ContactDate {
    private final String firstName;
    private final String address;
    private final String mobile;
    private final String email;

    public ContactDate(String firstName, String address, String mobile, String email) {
        this.firstName = firstName;
        this.address = address;
        this.mobile = mobile;
        this.email = email;
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
}
