package com.example.Student_Library_Management_System.Models;

import com.example.Student_Library_Management_System.Enums.CardStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Card")
public class Card
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreationTimestamp      //Auto timestamp the time when an entry is created
    private Date createdOn;         // Its util library will even give date with time till milli seconds--> HH:MM:SS:MS   //Its auto generated

    @UpdateTimestamp     //Sets time when an entry is updated.
    private Date updatedOn;   //Its auto generated

    @Enumerated(value = EnumType.STRING)    // SQL does not understand this enum so we have to convert it in String
    private CardStatus cardStatus;     //Set this attribute

    // It is a child class
    // unidirectional mapping
    @OneToOne
    @JoinColumn
    private Student student;       //This variable is used in the parent class while doing the bidirectional mapping

    public Card() {
    }

    //Plan is to save this card in Db.
    //Before saving I have to set its attributes : Rule 1

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
