package com.edazh.criminalintent.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edazh.criminalintent.R;
import com.edazh.criminalintent.databinding.FragmentCrimeListBinding;
import com.edazh.criminalintent.model.Crime;
import com.edazh.criminalintent.viewmodel.CrimeListViewModel;

import java.util.List;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */

public class CrimeListFragment extends Fragment {

    private FragmentCrimeListBinding mBinding;

    private CrimeListViewModel mViewModel;

    private CrimeAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_crime_list, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAdapter = new CrimeAdapter(mCrimeClickCallBack);
        mBinding.crimeList.setAdapter(mAdapter);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            mViewModel = ViewModelProviders.of(this).get(CrimeListViewModel.class);

            mViewModel.getCrimes().observe(this, new Observer<List<Crime>>() {
                @Override
                public void onChanged(@Nullable List<Crime> crimes) {
                    if (crimes == null) {
                        mBinding.setIsLoading(true);
                    } else {
                        mBinding.setIsLoading(false);
                        mAdapter.setCrimeList(crimes);
                    }
                    mBinding.executePendingBindings();
                }
            });
        }
    }

    public static CrimeListFragment newInstance() {

        Bundle args = new Bundle();

        CrimeListFragment fragment = new CrimeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private final CrimeClickCallBack mCrimeClickCallBack = new CrimeClickCallBack() {
        @Override
        public void onClick(Crime crime) {
            // TODO: 2018/1/12 0012 处理点击事件
        }
    };
}

