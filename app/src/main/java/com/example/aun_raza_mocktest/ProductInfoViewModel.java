package com.example.aun_raza_mocktest;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductInfoViewModel extends AndroidViewModel {
    private LiveData<List<ProductInfo>> allProductInfo;
    ProductInfoRepository repository;

    public ProductInfoViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductInfoRepository(application);
        allProductInfo = repository.getAllProductInfo();
    }

    public void insertProductInfo(ProductInfo productInfo) {
        repository.insertProductInfo(productInfo);
    }

    public ProductInfo findProductInfo(int productInfoId) {
        return repository.getProductInfo(productInfoId);
    }

    public LiveData<List<ProductInfo>> getAllProductInfo() {
        return allProductInfo;
    }
}
