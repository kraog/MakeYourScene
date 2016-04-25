package io.github.epelde.crispychainsaw.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.databinding.ItemBandBinding;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.viewmodel.BandItemViewModel;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandRecyclerViewAdapter extends RecyclerView.Adapter<BandRecyclerViewAdapter.BandViewHolder> implements BandItemViewModel.BandItemListener{

    private List<Band> bandList;
    private BandRecyclerViewListener brvl;

    public BandRecyclerViewAdapter(List<Band> list, BandRecyclerViewListener brvl) {
        bandList = list;
        this.brvl = brvl;
    }

    @Override
    public BandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBandBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_band, parent, false);
        return new BandViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BandViewHolder holder, int position) {
        holder.getBinding().setBandItemVM(new BandItemViewModel(bandList.get(position),this));
    }

    @Override
    public int getItemCount() {
        return bandList.size();
    }

    public class BandViewHolder extends RecyclerView.ViewHolder {
        private ItemBandBinding binding;

        public BandViewHolder(ItemBandBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemBandBinding getBinding() {
            return this.binding;
        }
    }

    public interface BandRecyclerViewListener{
        public void onItemClickedSuperior(Band band);
    }

    @Override
    public void onItemClicked(Band band){brvl.onItemClickedSuperior(band);}
}
