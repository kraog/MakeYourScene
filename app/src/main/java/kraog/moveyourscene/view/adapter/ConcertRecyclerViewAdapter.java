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
import kraog.moveyourscene.databinding.ConcertItemBinding;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Concert;
import kraog.moveyourscene.viewmodel.bands.BandItemVM;
import kraog.moveyourscene.viewmodel.concerts.ConcertItemVM;

/**
 * Created by epelde on 20/04/2016.
 */
public class ConcertRecyclerViewAdapter extends RecyclerView.Adapter<ConcertRecyclerViewAdapter.ConcertViewHolder> implements ConcertItemVM.ConcertItemListener{

    private List<Concert> concertList;
    private ConcertRecyclerViewListener brvl;

    public ConcertRecyclerViewAdapter(List<Concert> list, ConcertRecyclerViewListener brvl) {
        concertList = list;
        this.brvl = brvl;
    }

    @Override
    public ConcertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConcertItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.band_item, parent, false);
        return new ConcertViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ConcertViewHolder holder, int position) {
        holder.getBinding().setConcertItemVM(new ConcertItemVM(concertList.get(position),this));
    }

    @Override
    public int getItemCount() {
        return concertList.size();
    }

    public class ConcertViewHolder extends RecyclerView.ViewHolder {
        private ConcertItemBinding binding;

        public ConcertViewHolder(ConcertItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.getRoot().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        // run scale animation and make it bigger

                        ViewCompat.setElevation(ConcertViewHolder.this.binding.getRoot(), 30);
                    } else {
                        // run scale animation and make it smaller

                        ViewCompat.setElevation(ConcertViewHolder.this.binding.getRoot(), 0);
                    }
                }
            });
        }

        public ConcertItemBinding getBinding() {
            return this.binding;
        }
    }

    public interface ConcertRecyclerViewListener{
        public void onItemClickedSuperior(View v, Concert concert);
    }

    @Override
    public void onItemClicked(View v, Concert concert){brvl.onItemClickedSuperior(v,concert);}
}
