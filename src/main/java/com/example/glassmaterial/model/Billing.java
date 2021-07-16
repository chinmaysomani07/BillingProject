package com.example.glassmaterial.model;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;

public class Billing {

    private String material;
    private int sizeinmm;
    private double length;
    private double sut1;
    private double breadth;
    private double sut2;
    private int quantity;



    @Embedded
    private Work work;

    public Billing(String material, int sizeinmm, double length, double sut1, double breadth, double sut2, int quantity, Work work) {
        this.material = material;
        this.sizeinmm = sizeinmm;
        this.length = length;
        this.sut1 = sut1;
        this.breadth = breadth;
        this.sut2 = sut2;
        this.quantity = quantity;
        this.work=work;
    }

    public Billing() {
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getSizeinmm() {
        return sizeinmm;
    }

    public void setSizeinmm(int sizeinmm) {
        this.sizeinmm = sizeinmm;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getSut1() {
        return sut1;
    }

    public void setSut1(double sut1) {
        this.sut1 = sut1;
    }

    public double getBreadth() {
        return breadth;
    }

    public void setBreadth(double breadth) {
        this.breadth = breadth;
    }

    public double getSut2() {
        return sut2;
    }

    public void setSut2(double sut2) {
        this.sut2 = sut2;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Work getWork() {
        return work;
    }

    public void setWork(Work work) {
        this.work = work;
    }
}
