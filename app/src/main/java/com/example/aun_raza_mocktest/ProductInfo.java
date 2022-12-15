package com.example.aun_raza_mocktest;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "productInfo")
public class ProductInfo {
    @NonNull
    @PrimaryKey
    private int productId;
    private String productName;
    private double productPrice;

    public ProductInfo(int productId, String productName, double productPrice) {
        setProductId(productId);
        setProductName(productName);
        setProductPrice(productPrice);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
