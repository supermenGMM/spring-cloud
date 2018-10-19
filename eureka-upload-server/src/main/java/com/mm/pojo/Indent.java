package com.mm.pojo;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "indent")
public class Indent {
    @Id
    @GeneratedValue
    private int id;
    @Length(max = 100,min = 2)
    private String name;
    private int num;
    private String picurl;
    
    @Column(name = "product_id")
    private int productId;

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Indent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", picurl='" + picurl + '\'' +
                ", productId=" + productId +
                '}';
    }

    public Indent() {

    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }
}
