package io.github.epelde.crispychainsaw.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.databinding.BandItemBinding;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.viewmodel.BandDetailViewModel;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandRecyclerViewAdapter extends RecyclerView.Adapter<BandRecyclerViewAdapter.BandViewHolder> {
    private List<Band> bandList;

    public BandRecyclerViewAdapter(List<Band> list) {
        bandList = list;
    }

    @Override
    public BandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BandItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.band_item, parent, false);
        return new BandViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BandViewHolder holder, int position) {
        holder.getBinding().setBandDetailVM(new BandDetailViewModel(bandList.get(position)));
    }

    @Override
    public int getItemCount() {
        return bandList.size();
    }

    public class BandViewHolder extends RecyclerView.ViewHolder {
        private BandItemBinding binding;

        public BandViewHolder(BandItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public BandItemBinding getBinding() {
            return this.binding;
        }
    }
}
