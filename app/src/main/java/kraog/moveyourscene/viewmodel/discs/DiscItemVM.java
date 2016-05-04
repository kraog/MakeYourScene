package kraog.moveyourscene.viewmodel.discs;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Disc;

public class DiscItemVM {

    public ObservableField<Disc> disc;
    private DiscItemListener bil;

    public DiscItemVM(@NonNull Disc disc, DiscItemListener bil) {
        this.disc = new ObservableField<Disc>(disc);
        this.bil = bil;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void bindImage(ImageView view, String url) {
        Picasso.with(view.getContext()).load(url).fit().centerCrop().into(view);
    }

    public interface DiscItemListener{
        public void onItemClicked(View v, Disc disc);
    }

    public View.OnClickListener getOnClickListener(){
        return new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                bil.onItemClicked(v,DiscItemVM.this.disc.get());
            }
        };

    }
}
