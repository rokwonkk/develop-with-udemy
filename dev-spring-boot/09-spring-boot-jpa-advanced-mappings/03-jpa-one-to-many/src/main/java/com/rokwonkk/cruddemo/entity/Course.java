package com.rokwonkk.cruddemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="course")
public class Course {

    //define our fields

    // define constructors

    // define getter setters

    // define toString

    // annotate fields

    private int id;

    private String title;

    private Instructor instructor;

    public Course() {
    }



}
