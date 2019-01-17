package com.bourne.caesar.impextutors.Offline_Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PayDao {

    @Insert
    void insert(PayTable payTable);

    @Update
    void update(PayTable payTable);

    @Delete
    void delete(PayTable payTable);

    @Query("DELETE FROM payment_table")
    void deleteAllNotes();

    @Query("SELECT * FROM payment_table")
    LiveData<List<PayTable>> getAllPayment();

}
