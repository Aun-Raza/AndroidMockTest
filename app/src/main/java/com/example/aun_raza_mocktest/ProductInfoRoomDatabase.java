package com.example.aun_raza_mocktest;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProductInfo.class}, version = 1)
public abstract class ProductInfoRoomDatabase extends RoomDatabase {
    public abstract ProductInfoDao ProductInfoDao();
    private static ProductInfoRoomDatabase INSTANCE;

    static ProductInfoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProductInfoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    ProductInfoRoomDatabase.class,
                                    "ProductInfoDB")
                                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
