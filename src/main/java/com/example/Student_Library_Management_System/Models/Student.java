package com.example.Student_Library_Management_System.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "Student")
public class Student
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Id count will be automatically generated and incremented
    private int id;   // Id parameter is not required to be entered by Postman

    private String name;

    @Column(unique = true)
    private String email;

    private String mobileNo;

    private int age;

    private String country;

    //Plain syntax for bidirectional mapping
    //Name of variable of the Parent Entity that you have written in child class foreign key attr.
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Card card;
    /*
        Steps to find that variable
        1. Go the child class (In this case)
        2. Out of all the attributes select the foreign key attribute that is helping you connect
        with parent class
        (Ref :  @OneToOne
                @JoinColumn
                private Student studentVariableName;
        )
        3. Choose the variable name of the parentEnty (reference : studentVariableName)
     */


    public Student() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }
}
