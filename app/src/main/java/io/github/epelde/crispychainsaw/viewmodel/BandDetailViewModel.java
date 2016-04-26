package io.github.epelde.crispychainsaw.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.v7.graphics.Palette;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import io.github.epelde.crispychainsaw.model.domain.Band;

/**
 * Created by epelde on 21/04/2016.
 */
public class BandDetailViewModel {

    public ObservableField<Band> band;
    public static BandDetailViewModelListener mBandDetailViewModelListener;

    public BandDetailViewModel(@NonNull Band band, @NonNull BandDetailViewModelListener mBandDetailViewModelListener) {
        this.band = new ObservableField<>(band);
        this.mBandDetailViewModelListener = mBandDetailViewModelListener;
    }

    @BindingAdapter({"bind:imageDetailUrl"})
    public static void bindImage(final ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).fit().centerCrop().into(view, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        mBandDetailViewModelListener.applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
    }

    public interface BandDetailViewModelListener {
        public void applyPalette(Palette palette);
    }
}
