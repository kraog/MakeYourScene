package kraog.moveyourscene.viewmodel.concerts;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Concert;


public class ConcertItemVM {

    public ObservableField<Concert> concert;
    private ConcertItemListener bil;

    public ConcertItemVM(@NonNull Concert concert, ConcertItemListener bil) {
        this.concert = new ObservableField<Concert>(concert);
        this.bil = bil;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void bindImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).fit().centerCrop().into(view);
    }

    public interface ConcertItemListener{
        public void onItemClicked(View v, Concert concert);
    }

    public View.OnClickListener getOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bil.onItemClicked(v,ConcertItemVM.this.concert.get());
            }
        };

    }
}
