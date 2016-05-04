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
import kraog.moveyourscene.databinding.DiscItemBinding;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Disc;
import kraog.moveyourscene.viewmodel.bands.BandItemVM;
import kraog.moveyourscene.viewmodel.discs.DiscItemVM;


public class DiscRecyclerViewAdapter extends RecyclerView.Adapter<DiscRecyclerViewAdapter.DiscViewHolder> implements DiscItemVM.DiscItemListener{

    private List<Disc> discList;
    private DiscRecyclerViewListener brvl;

    public DiscRecyclerViewAdapter(List<Disc> list, DiscRecyclerViewListener brvl) {
        this.discList = list;
        this.brvl = brvl;
    }

    @Override
    public DiscViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        DiscItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.disc_item, parent, false);
        return new DiscViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(DiscViewHolder holder, int position) {
        holder.getBinding().setDiscItemVM(new DiscItemVM(discList.get(position),this));
    }

    @Override
    public int getItemCount() {
        return discList.size();
    }

    public class DiscViewHolder extends RecyclerView.ViewHolder {
        private DiscItemBinding binding;

        public DiscViewHolder(DiscItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.getRoot().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        // run scale animation and make it bigger

                        ViewCompat.setElevation(DiscViewHolder.this.binding.getRoot(), 30);
                    } else {
                        // run scale animation and make it smaller

                        ViewCompat.setElevation(DiscViewHolder.this.binding.getRoot(), 0);
                    }
                }
            });
        }

        public DiscItemBinding getBinding() {
            return this.binding;
        }
    }

    public interface DiscRecyclerViewListener{
        public void onItemClickedSuperior(View v, Disc disc);
    }

    @Override
    public void onItemClicked(View v, Disc disc){brvl.onItemClickedSuperior(v,disc);}
}
