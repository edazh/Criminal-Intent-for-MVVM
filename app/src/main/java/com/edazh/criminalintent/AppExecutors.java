package com.edazh.criminalintent;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */

public class AppExecutors {
    private final Executor mMainThread;

    private final Executor mDiskIO;

    private final Executor mNetworkIO;

    private AppExecutors(Executor mainThread, Executor diskIO, Executor networlIO) {
        mMainThread = mainThread;
        mDiskIO = diskIO;
        mNetworkIO = networlIO;
    }

    public AppExecutors() {
        this(new MainThreadExecutor(), Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3));
    }

    public Executor diskIO() {
        return mDiskIO;
    }

    public Executor networkIO() {
        return mNetworkIO;
    }

    public Executor mainThread(){
        return mMainThread;
    }


    private static class MainThreadExecutor implements Executor {

        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}
