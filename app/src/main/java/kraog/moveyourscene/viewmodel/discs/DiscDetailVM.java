package kraog.moveyourscene.viewmodel.discs;

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
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Disc;
import kraog.moveyourscene.model.domain.Track;
import kraog.moveyourscene.view.bands.BandDetailActivity;
import kraog.moveyourscene.view.discs.DiscDetailActivity;

public class DiscDetailVM {

    public ObservableField<Disc> disc;
    public static ObservableField<Integer> anchorGravity;
    public ObservableField<String> bioCard;
    public ObservableField<List<Track>> trackList;
    public static DiscDetailViewModelListener mDiscDetailViewModelListener;
    public static String cardUp = DiscDetailActivity.CARD_BUTTONS;

    public DiscDetailVM(@NonNull Disc disc, @NonNull DiscDetailViewModelListener mDiscDetailViewModelListener, Context context) {
        this.disc = new ObservableField<>(disc);
        this.mDiscDetailViewModelListener = mDiscDetailViewModelListener;
        this.anchorGravity= new ObservableField<Integer> (Gravity.END|Gravity.BOTTOM|Gravity.RIGHT);
        this.bioCard = new ObservableField<String> (DiscDetailActivity.CARD_BIO);
    }


    /*
        Sets the fab button listener that changes icon and button position with associated animation
     */
     public View.OnClickListener setFabListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatingActionButton fab = (FloatingActionButton) v;
                if(!cardUp.equals(DiscDetailActivity.CARD_BUTTONS)){
                    anchorGravity.set(Gravity.END|Gravity.BOTTOM|Gravity.RIGHT);
                    mDiscDetailViewModelListener.onFabListenerClicked(cardUp);
                    cardUp = DiscDetailActivity.CARD_BUTTONS;
                } else {
                    mDiscDetailViewModelListener.onYTButtonListenerClicked("EaPP8H4H6nc");
                }
            }
        };
    }

    @BindingAdapter({"bind:disc_relatedCard"})
    public static void setDBClickListener(View v, final String relatedCard){

        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        anchorGravity.set(Gravity.END|Gravity.BOTTOM|Gravity.LEFT);
                        mDiscDetailViewModelListener.onButtonListenerClicked(relatedCard);
                        cardUp = relatedCard;
                    }
                }
        );
    }

    /*
        Sets the disc image, extracting the associated color for the floating bar palette
     */
    @BindingAdapter({"bind:disc_imageDetailUrl"})
    public static void bindImage(final ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).fit().centerCrop().into(view, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        mDiscDetailViewModelListener.applyPalette(palette);
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

    public interface DiscDetailViewModelListener {
        public void applyPalette(Palette palette);
        public void onFabListenerClicked(String relatedCard);
        public void onButtonListenerClicked(String relatedCard);
        public void onYTButtonListenerClicked(String uri);
    }
}
