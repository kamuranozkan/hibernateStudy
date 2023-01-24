package com.hb04.bi_onetoone;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Diary04 {

    @Id
    private int id;

    private String name;

    @OneToOne
    @JoinColumn(name="std_id") //joincloumn istediğim ismi vermeme yarar sağlıyor
    private Student04 student ; //Bunu studentta yazsaydım ilişki student üzerinden olurdu.

    //getter-setter
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

    public Student04 getStudent() {
        return student;
    } //size Student04 classtan üretilen bir objeyi döndürüyor

    public void setStudent(Student04 student) {
        this.student = student;
    }

    // toString

    @Override
    public String toString() {
        return "Diary04{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", student=" + student +
                '}';
    }
}
