package com.edazh.criminalintent.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edazh.criminalintent.R;
import com.edazh.criminalintent.databinding.FragmentCrimeBinding;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */

public class CrimeFragment extends Fragment {

    private FragmentCrimeBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime, container, false);
        return mBinding.getRoot();
    }

    public static CrimeFragment newInstance() {

        Bundle args = new Bundle();

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
