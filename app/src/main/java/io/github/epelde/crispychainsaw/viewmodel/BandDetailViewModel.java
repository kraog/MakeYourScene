package io.github.epelde.crispychainsaw.viewmodel;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.graphics.Palette;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.model.domain.Band;

/**
 * Created by epelde on 21/04/2016.
 */
public class BandDetailViewModel {

    public ObservableField<Band> band;
    public ObservableField<Integer> anchorGravity= new ObservableField<Integer> (Gravity.END|Gravity.BOTTOM|Gravity.RIGHT);
    public static BandDetailViewModelListener mBandDetailViewModelListener;
    Animation fab_toSongs,fab_toDetail;
    public boolean detail = true;

    public BandDetailViewModel(@NonNull Band band, @NonNull BandDetailViewModelListener mBandDetailViewModelListener, Context context) {
        this.band = new ObservableField<>(band);
        this.mBandDetailViewModelListener = mBandDetailViewModelListener;
        fab_toSongs = AnimationUtils.loadAnimation(context,
                R.anim.fabbutton_to_songs);

        fab_toDetail = AnimationUtils.loadAnimation(context,
                R.anim.fabbutton_to_details);
    }


    /*
        Sets the fab button listener that changes icon and button position with associated animation
     */
     public View.OnClickListener setFabListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButton fab = (FloatingActionButton) v;
                if(detail){
                    fab.startAnimation(fab_toSongs);
                    fab.setImageResource(R.drawable.ic_favorite_black_24dp);
                    anchorGravity.set(Gravity.END|Gravity.BOTTOM|Gravity.LEFT);
                } else {
                    fab.startAnimation(fab_toDetail);
                    fab.setImageResource(R.drawable.ic_favorite_black_24dp);
                    anchorGravity.set(Gravity.END|Gravity.BOTTOM|Gravity.RIGHT);
                }
                mBandDetailViewModelListener.onFabListenerClicked(detail);
                detail = !detail;
            }
        };
    }

    /*
        Sets the band image, extracting the associated color for the floating bar palette
     */
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

    /*
        Sets the fab button orientation
     */
    @BindingAdapter({"bind:anchorGravity"})
    public static void setAnchorGravity(final ImageView view, Integer anchorGravity){
        ((CoordinatorLayout.LayoutParams)view.getLayoutParams()).anchorGravity=anchorGravity;
    }

    public interface BandDetailViewModelListener {
        public void applyPalette(Palette palette);
        public void onFabListenerClicked(boolean detail);
    }
}
