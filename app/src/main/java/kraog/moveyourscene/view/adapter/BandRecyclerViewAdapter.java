package kraog.moveyourscene.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.BandItemBinding;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.viewmodel.bands.BandItemVM;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandRecyclerViewAdapter extends RecyclerView.Adapter<BandRecyclerViewAdapter.BandViewHolder> implements BandItemVM.BandItemListener{

    private List<Band> bandList;
    private BandRecyclerViewListener brvl;

    public BandRecyclerViewAdapter(List<Band> list, BandRecyclerViewListener brvl) {
        bandList = list;
        this.brvl = brvl;
    }

    @Override
    public BandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BandItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.band_item, parent, false);
        return new BandViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BandViewHolder holder, int position) {
        holder.getBinding().setBandItemVM(new BandItemVM(bandList.get(position),this));
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

            this.binding.getRoot().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        // run scale animation and make it bigger

                        ViewCompat.setElevation(BandViewHolder.this.binding.getRoot(), 30);
                    } else {
                        // run scale animation and make it smaller

                        ViewCompat.setElevation(BandViewHolder.this.binding.getRoot(), 0);
                    }
                }
            });
        }

        public BandItemBinding getBinding() {
            return this.binding;
        }
    }

    public interface BandRecyclerViewListener{
        public void onItemClickedSuperior(View v,Band band);
    }

    @Override
    public void onItemClicked(View v, Band band){brvl.onItemClickedSuperior(v,band);}
}
