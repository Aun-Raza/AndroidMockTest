package com.example.aun_raza_mocktest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductInfoRepository {
    private LiveData<List<ProductInfo>> allProductInfo;
    private ProductInfoDao productInfoDao;

    public ProductInfoRepository(Application application) {
        ProductInfoRoomDatabase db = ProductInfoRoomDatabase.getDatabase(application);
        productInfoDao = db.ProductInfoDao();
        allProductInfo = productInfoDao.getAllProductInfo();
    }

    public LiveData<List<ProductInfo>> getAllProductInfo() {
        return allProductInfo;
    }

    public void insertProductInfo(ProductInfo productInfo) {
        new insertProductInfoAsyncTask(productInfoDao).execute(productInfo);
    }

    public ProductInfo getProductInfo(int productId) {
        try {
            return new getProductInfoAsyncTask(productInfoDao).execute(productId).get();
        } catch (Exception ex) {
            return null;
        }
    }

    private static class insertProductInfoAsyncTask extends AsyncTask<ProductInfo, Void, Void> {
        private ProductInfoDao productInfoDao;

        public insertProductInfoAsyncTask(ProductInfoDao dao) {
            productInfoDao = dao;
        }

        @Override
        protected Void doInBackground(ProductInfo... params) {
            productInfoDao.insertProductInfo(params[0]);
            return null;
        }
    }

    private static class getProductInfoAsyncTask extends AsyncTask<Integer, Void, ProductInfo> {
        private ProductInfoDao productInfoDao;

        public getProductInfoAsyncTask(ProductInfoDao dao) {
            productInfoDao = dao;
        }

        @Override
        protected ProductInfo doInBackground(Integer... params) {
            ProductInfo productInfo = productInfoDao.getProductInfo(params[0]);
            return productInfo;
        }
    }
}
