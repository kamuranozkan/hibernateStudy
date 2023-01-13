package com.hb01.annotation;

import javax.persistence.*;

@Entity
@Table(name="t_student01")
public class Student01 {

    @Id
    //@Column(name="std_id")
    private int id;

    @Column(name="student_name",length = 100,nullable = false,unique = false)
    private String name;

   // @Transient
    private int grade;

   // @Lob
    //private byte[]image;


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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student01{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
