package kraog.moveyourscene.viewmodel.bands;

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
import android.widget.LinearLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import kraog.moveyourscene.R;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.util.Funciones;
import kraog.moveyourscene.view.bands.BandDetailActivity;

/**
 * Created by epelde on 21/04/2016.
 */
public class BandDetailVM {

    public ObservableField<Band> band;
    public static ObservableField<Integer> anchorGravity;
    public ObservableField<String> bioCard = new ObservableField<String> (BandDetailActivity.CARD_BIO);
    public ObservableField<String> concertCard = new ObservableField<String> (BandDetailActivity.CARD_CONCERT);
    public ObservableField<String> discCard = new ObservableField<String> (BandDetailActivity.CARD_DISC);
    public static BandDetailViewModelListener mBandDetailViewModelListener;
    public static String cardUp = BandDetailActivity.CARD_BUTTONS;

    public BandDetailVM(@NonNull Band band, @NonNull BandDetailViewModelListener mBandDetailViewModelListener, Context context) {
        this.band = new ObservableField<>(band);
        this.mBandDetailViewModelListener = mBandDetailViewModelListener;
        this.anchorGravity= new ObservableField<Integer> (Gravity.END|Gravity.BOTTOM|Gravity.RIGHT);
    }


    /*
        Sets the fab button listener that changes icon and button position with associated animation
     */
     public View.OnClickListener setFabListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButton fab = (FloatingActionButton) v;
                if(!cardUp.equals(BandDetailActivity.CARD_BUTTONS)){
                    anchorGravity.set(Gravity.END|Gravity.BOTTOM|Gravity.RIGHT);
                    mBandDetailViewModelListener.onFabListenerClicked(cardUp);
                    cardUp = BandDetailActivity.CARD_BUTTONS;
                } else {
                    mBandDetailViewModelListener.onYTButtonListenerClicked("EaPP8H4H6nc");
                }
            }
        };
    }

    @BindingAdapter({"bind:relatedCard"})
    public static void setDBClickListener(View v, final String relatedCard){

        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        anchorGravity.set(Gravity.END|Gravity.BOTTOM|Gravity.LEFT);
                        mBandDetailViewModelListener.onButtonListenerClicked(relatedCard);
                        cardUp = relatedCard;
                    }
                }
        );
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
        public void onFabListenerClicked(String relatedCard);
        public void onButtonListenerClicked(String relatedCard);
        public void onYTButtonListenerClicked(String uri);
    }
}
