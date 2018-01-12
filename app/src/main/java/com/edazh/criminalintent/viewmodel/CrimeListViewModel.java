package com.edazh.criminalintent.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.edazh.criminalintent.CrimeApp;
import com.edazh.criminalintent.model.Crime;

import java.util.List;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */

public class CrimeListViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<Crime>> mObservableCrimes;

    public CrimeListViewModel(@NonNull Application application) {
        super(application);

        mObservableCrimes = new MediatorLiveData<>();
        mObservableCrimes.setValue(null);

        LiveData<List<Crime>> crimes = ((CrimeApp) application).getRepository().loadAllCrimes();

        mObservableCrimes.addSource(crimes, new Observer<List<Crime>>() {
            @Override
            public void onChanged(@Nullable List<Crime> crimes) {
                mObservableCrimes.setValue(crimes);
            }
        });
    }

    public LiveData<List<Crime>> getCrimes() {
        return mObservableCrimes;
    }
}
