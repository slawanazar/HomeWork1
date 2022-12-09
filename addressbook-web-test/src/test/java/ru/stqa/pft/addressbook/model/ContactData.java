package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")

public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "firstname")
    private String firstName;
    @Transient
    private String address;
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobile;
    @Transient
    private String email;
    @Transient
    private String group;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String secondEmail;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        } else {
            return null;
        }
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withSecondEmail(String secondEmail) {
        this.secondEmail = secondEmail;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
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

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
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

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
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
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(address, that.address) && Objects.equals(mobile, that.mobile)
                && Objects.equals(email, that.email) && Objects.equals(group, that.group) && Objects.equals(lastName, that.lastName) && Objects.equals(homePhone, that.homePhone)
                && Objects.equals(workPhone, that.workPhone) && Objects.equals(allPhones, that.allPhones) && Objects.equals(allEmails, that.allEmails) && Objects.equals(secondEmail, that.secondEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, address, mobile, email, group, lastName, homePhone, workPhone, allPhones, allEmails, secondEmail);
    }
}