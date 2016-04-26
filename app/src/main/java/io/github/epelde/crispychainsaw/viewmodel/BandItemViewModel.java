package io.github.epelde.crispychainsaw.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.github.epelde.crispychainsaw.model.domain.Band;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandItemViewModel {

    public ObservableField<Band> band;
    private BandItemListener bil;

    public BandItemViewModel(@NonNull Band band, BandItemListener bil) {
        this.band = new ObservableField<Band>(band);
        this.bil = bil;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void bindImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).fit().centerCrop().into(view);
    }

    public interface BandItemListener{
        public void onItemClicked(View v,Band band);
    }

    public View.OnClickListener getOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bil.onItemClicked(v,BandItemViewModel.this.band.get());
            }
        };

    }
}
