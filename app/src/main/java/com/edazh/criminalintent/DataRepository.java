package com.edazh.criminalintent;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.edazh.criminalintent.db.AppDatabase;
import com.edazh.criminalintent.model.Crime;

import java.util.List;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */

public class DataRepository {
    private static DataRepository sInstance;

    private final AppDatabase mDatabase;

    private final AppExecutors mExecutors;

    private final MediatorLiveData<List<Crime>> mObservableCrime;

    private DataRepository(AppDatabase database, AppExecutors executors) {
        mDatabase = database;
        mExecutors = executors;

        mObservableCrime = new MediatorLiveData<>();

        mObservableCrime.addSource(mDatabase.crimeDao().loadAll(), new Observer<List<Crime>>() {
            @Override
            public void onChanged(@Nullable List<Crime> crimes) {
                if (mDatabase.getDatabaseCreated().getValue() != null) {
                    mObservableCrime.postValue(crimes);
                }
            }
        });
    }

    public static DataRepository getInstance(final AppDatabase database, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database, executors);
                }
            }
        }
        return sInstance;
    }


    public LiveData<Crime> loadCrime(int id) {
        return mDatabase.crimeDao().load(id);
    }

    public LiveData<List<Crime>> loadAllCrimes() {
        return mDatabase.crimeDao().loadAll();
    }

    public void insertCrime(final Crime crime) {
        Runnable insertThread = new Runnable() {
            @Override
            public void run() {
                mDatabase.crimeDao().insert(crime);
            }
        };
        mExecutors.diskIO().execute(insertThread);
    }

    public void updateCrime(final Crime crime) {
        Runnable updateThread = new Runnable() {
            @Override
            public void run() {
                mDatabase.crimeDao().update(crime);
            }
        };
        mExecutors.diskIO().execute(updateThread);
    }

    public void deleteCrime(final int crimeId) {
        Runnable deleteThread = new Runnable() {
            @Override
            public void run() {
                mDatabase.crimeDao().deleteCrimeById(crimeId);
            }
        };
        mExecutors.diskIO().execute(deleteThread);
    }

    public void deleteAllCrimes() {
        Runnable deleteAllThread = new Runnable() {
            @Override
            public void run() {
                mDatabase.crimeDao().deleteAllCrimes();
            }
        };
        mExecutors.diskIO().execute(deleteAllThread);
    }
}
