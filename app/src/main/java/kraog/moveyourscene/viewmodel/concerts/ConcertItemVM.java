package kraog.moveyourscene.viewmodel.concerts;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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



    @BindingAdapter({"bind:concert_dateFormatted"})
    public static void bindFormattedDate(TextView view, Date date) {
        SimpleDateFormat sdfDay = new SimpleDateFormat("EEEE");
        SimpleDateFormat sdfDayMonth = new SimpleDateFormat("d MMM");
        SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

        view.setText(Html.fromHtml("<big>"+sdfDay.format(date)+"</big><br><b><big>" + sdfDayMonth.format(date)+"</b></big> " +sdfYear.format(date)));
    }
    @BindingAdapter({"bind:concert_hourFormatted"})
    public static void bindFormattedHour(Button view, Date date) {
        SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");
        view.setText(sdfHour.format(date));
    }
    @BindingAdapter({"bind:concert_moneyFormatted"})
    public static void bindFormattedMoney(Button view, Float money) {
        if (money!=null){
            view.setText(money.toString());
        }
    }
    @BindingAdapter({"bind:concert_locationFormatted"})
    public static void bindFormattedLocation(Button view, String local) {
        view.setText(local);
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
