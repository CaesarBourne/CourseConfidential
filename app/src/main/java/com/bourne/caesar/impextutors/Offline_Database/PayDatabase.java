package com.bourne.caesar.impextutors.Offline_Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

@Database(entities = {PayTable.class}, version = 1)
public abstract class PayDatabase extends RoomDatabase {

    public abstract PayDao payDao();
    private static PayDatabase payDatabaseInstance;

    public static synchronized PayDatabase getPayDatabaseInstance(Context context){
        payDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), PayDatabase.class,"pay_database")
                .addCallback(payDatabaseCallback)
                .fallbackToDestructiveMigration()
                .build();
        return payDatabaseInstance;
    }

    private static RoomDatabase.Callback payDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };
}
