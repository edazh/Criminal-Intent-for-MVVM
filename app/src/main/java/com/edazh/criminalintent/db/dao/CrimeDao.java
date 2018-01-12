package com.edazh.criminalintent.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.edazh.criminalintent.model.Crime;

import java.util.List;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */
@Dao
public interface CrimeDao {

    @Query("select * from crimes where id = :id")
    LiveData<Crime> load(int id);

    @Query("select * from crimes")
    LiveData<List<Crime>> loadAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Crime crime);

    @Update
    void update(Crime crime);

    @Query("delete from crimes where id = :id")
    void deleteCrimeById(int id);

    @Query("delete from crimes")
    void deleteAllCrimes();
}
