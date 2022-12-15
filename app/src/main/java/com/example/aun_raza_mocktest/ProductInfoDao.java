package com.example.aun_raza_mocktest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductInfoDao {
    @Insert
    void insertProductInfo(ProductInfo productInfo);

    @Query("SELECT * FROM productInfo WHERE productId = :productInfoId")
    ProductInfo getProductInfo(int productInfoId);

    @Query("SELECT * FROM productInfo")
    LiveData<List<ProductInfo>> getAllProductInfo();
}
