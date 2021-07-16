package com.example.glassmaterial.model;
import javax.persistence.*;

@Entity
@Table(name="Material")
public class Material {
    @Id
    @GeneratedValue

    private int srno;
    private String type;
    private int sizeinmm;
    private double price;



    public Material(int srno, String type, int sizeinmm, double price, Work work) {
        this.srno = srno;
        this.type = type;
        this.sizeinmm = sizeinmm;
        this.price = price;
    }

    public Material() {
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

    public int getSizeinmm() {
        return sizeinmm;
    }

    public void setSizeinmm(int sizeinmm) {
        this.sizeinmm = sizeinmm;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
