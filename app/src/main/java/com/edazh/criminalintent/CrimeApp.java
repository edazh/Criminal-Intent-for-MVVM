package com.edazh.criminalintent;

import android.app.Application;

import com.edazh.criminalintent.db.AppDatabase;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */

public class CrimeApp extends Application {
    private AppExecutors mExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mExecutors = new AppExecutors();
    }

    public AppDatabase getAppDatabase() {
        return AppDatabase.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getAppDatabase(), mExecutors);
    }
}
