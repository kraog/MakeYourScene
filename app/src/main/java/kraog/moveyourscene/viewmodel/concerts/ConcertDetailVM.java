package kraog.moveyourscene.viewmodel.concerts;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import kraog.moveyourscene.model.domain.Concert;
import kraog.moveyourscene.model.domain.Disc;
import kraog.moveyourscene.model.domain.Track;
import kraog.moveyourscene.view.concerts.ConcertDetailActivity;
import kraog.moveyourscene.view.discs.DiscDetailActivity;

public class ConcertDetailVM {

    public ObservableField<Concert> concert;
    public ObservableField<String> bioCard;
    public static ConcertDetailViewModelListener mConcertDetailViewModelListener;
    public static String cardUp = DiscDetailActivity.CARD_BUTTONS;

    public ConcertDetailVM(@NonNull Concert concert, @NonNull ConcertDetailViewModelListener mConcertDetailViewModelListener, Context context) {
        this.concert = new ObservableField<>(concert);
        this.mConcertDetailViewModelListener = mConcertDetailViewModelListener;
        this.bioCard = new ObservableField<String> (ConcertDetailActivity.CARD_BIO);
    }


    public TabLayout.OnTabSelectedListener getTabListener(){

        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    @BindingAdapter({"bind:concertRelatedCard"})
    public static void setDBClickListener(View v, final String relatedCard){

        v.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mConcertDetailViewModelListener.onButtonListenerClicked(relatedCard);
                        cardUp = relatedCard;
                    }
                }
        );
    }

    /*
        Sets the disc image, extracting the associated color for the floating bar palette
     */
    @BindingAdapter({"bind:concert_imageDetailUrl"})
    public static void bindImage(final ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).fit().centerCrop().into(view, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) view.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        mConcertDetailViewModelListener.applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
    }

    public interface ConcertDetailViewModelListener {
        public void applyPalette(Palette palette);
        public void onButtonListenerClicked(String relatedCard);
    }
}
