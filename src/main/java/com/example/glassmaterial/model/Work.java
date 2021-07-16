package com.example.glassmaterial.model;


import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Embeddable
public class Work {

    @Id
    @GeneratedValue

    private int srno;
    private String type;
    private int price;


    public Work(String type,int quantity) {
        this.srno=srno;
        this.type = type;
        this.price=price;

    }

    public Work() {
    }

    public int getSrno() {
        return srno;
    }

    public void setSrno(int srno) {
        this.srno = srno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}


