package com.edazh.criminalintent.ui;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.edazh.criminalintent.R;
import com.edazh.criminalintent.databinding.CrimeItemBinding;
import com.edazh.criminalintent.model.Crime;

import java.util.List;
import java.util.Objects;

/**
 * Created by edazh on 2018/1/12 0012.
 * e-mail:edazh@qq.com
 */

public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeViewHodler> {

    private List<Crime> mCrimeList;

    @Nullable
    private final CrimeClickCallBack mCallBack;

    public CrimeAdapter(@Nullable CrimeClickCallBack callBack) {
        mCallBack = callBack;
    }

    public void setCrimeList(final List<Crime> crimeList) {
        if (mCrimeList == null) {
            mCrimeList = crimeList;
            notifyItemRangeInserted(0, crimeList.size());
        } else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mCrimeList.size();
                }

                @Override
                public int getNewListSize() {
                    return crimeList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mCrimeList.get(oldItemPosition).getId() == crimeList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Crime oldCrime = mCrimeList.get(oldItemPosition);
                    Crime newCrime = crimeList.get(newItemPosition);
                    return newCrime.getId() == oldCrime.getId()
                            && Objects.equals(newCrime.getTitle(), oldCrime.getTitle())
                            && Objects.equals(newCrime.getDate(), oldCrime.getDate())
                            && newCrime.isSolved() == oldCrime.isSolved();
                }
            });
            mCrimeList = crimeList;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @Override
    public CrimeViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        CrimeItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.crime_item, parent, false);
        binding.setCallback(mCallBack);
        return new CrimeViewHodler(binding);
    }

    @Override
    public void onBindViewHolder(CrimeViewHodler holder, int position) {
        holder.binding.setCrime(mCrimeList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mCrimeList == null ? 0 : mCrimeList.size();
    }

    static class CrimeViewHodler extends RecyclerView.ViewHolder {

        final CrimeItemBinding binding;

        public CrimeViewHodler(CrimeItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
