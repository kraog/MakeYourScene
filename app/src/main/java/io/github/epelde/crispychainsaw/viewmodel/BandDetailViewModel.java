package io.github.epelde.crispychainsaw.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import io.github.epelde.crispychainsaw.model.domain.Band;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandDetailViewModel {

    public ObservableField<Band> band;

    public BandDetailViewModel(@NonNull Band band) {
        this.band = new ObservableField<Band>(band);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void bindImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).fit().centerCrop().into(view);
    }
}
