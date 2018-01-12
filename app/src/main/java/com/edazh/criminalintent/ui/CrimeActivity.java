package com.edazh.criminalintent.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.edazh.criminalintent.R;
import com.edazh.criminalintent.SingleFragmentActivity;

public class CrimeActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return CrimeFragment.newInstance();
    }
}
