package kraog.moveyourscene.view.bands;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.BandDetailActivityBinding;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.util.Funciones;
import kraog.moveyourscene.viewmodel.bands.BandDetailVM;

/**
 * Created by epelde on 21/04/2016.
 */
public class BandDetailActivity extends AppCompatActivity implements BandDetailVM.BandDetailViewModelListener{

    private static final String EXTRA_BAND = "kraog.moveyourscene.EXTRA_BAND";
    private static final String EXTRA_IMAGE = "kraog.moveyourscene.EXTRA_IMAGE";
    public static final String CARD_BIO = "kraog.moveyourscene.CARD_BIO";
    public static final String CARD_CONCERT = "kraog.moveyourscene.CARD_CONCERT";
    public static final String CARD_DISC = "kraog.moveyourscene.CARD_DISC";
    public static final String CARD_BUTTONS = "kraog.moveyourscene.CARD_BUTTONS";
    BandDetailActivityBinding binding;
    public static Animation fab_toOrigin,fab_toDetail,slide_down,slide_up;
    BandDetailVM bandDetailVM;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Band band = (Band) getIntent().getSerializableExtra(EXTRA_BAND);
        bandDetailVM = new BandDetailVM(band,this,this.getApplicationContext());
        binding = DataBindingUtil.setContentView(this, R.layout.band_detail_activity);
        binding.setBandDetailVM(bandDetailVM);

        ViewCompat.setTransitionName(binding.appBarLayout, EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        fab_toDetail = AnimationUtils.loadAnimation(this.getApplicationContext(),
                R.anim.fabbutton_to_detail);

        fab_toOrigin = AnimationUtils.loadAnimation(this.getApplicationContext(),
                R.anim.fabbutton_to_origin);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_out_bottom);

        slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_bottom);
    }



    public static void navigate(AppCompatActivity activity,View trantistionImage, Band band) {
        Intent intent = new Intent(activity, BandDetailActivity.class);
        intent.putExtra(EXTRA_BAND, band);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, trantistionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }


    /**
     * Support library version of {@link android.app.Activity#postponeEnterTransition()} that works
     * only on API 21 and later.
     */
    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }

    @Override
    public void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        binding.collapsingToolbar.setContentScrimColor(palette.getMutedColor(primary));
        binding.collapsingToolbar.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
        updateBackground((FloatingActionButton) findViewById(R.id.fab), palette);
        supportStartPostponedEnterTransition();
    }

    @Override
    public void onFabListenerClicked(String relatedCard) {
        binding.fab.setImageResource(R.drawable.youtube42);
        binding.fab.startAnimation(fab_toOrigin);
        cardRelated(relatedCard).startAnimation(slide_down);
        cardRelated(relatedCard).setVisibility(View.INVISIBLE);
        binding.layoutButtonsComponent.layoutButtons.startAnimation(slide_up);
        binding.layoutButtonsComponent.layoutButtons.setVisibility(View.VISIBLE);
    }

    @Override
    public void onButtonListenerClicked(String relatedCard) {
        showCard(cardRelated(relatedCard));
    }

    @Override
    public void onYTButtonListenerClicked(String uri) {
        Funciones.watchYoutubeVideo(this,uri);
    }


    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.black));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.colorPrimaryDark));
        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }
    private void showCard(LinearLayout card){
        binding.fab.setImageResource(R.drawable.nut_icon);
        binding.fab.startAnimation(fab_toDetail);
        binding.layoutButtonsComponent.layoutButtons.startAnimation(slide_down);
        binding.layoutButtonsComponent.layoutButtons.setVisibility(View.INVISIBLE);
        card.startAnimation(slide_up);
        card.setVisibility(View.VISIBLE);
    }
    public LinearLayout cardRelated(String relatedCard) {
        switch (relatedCard) {
            case CARD_BIO: {
                return binding.layoutDiscsComponent.layoutDiscs;
            }
            case CARD_CONCERT: {
                return binding.layoutDiscsComponent.layoutDiscs;
            }
            case CARD_DISC: {
                return binding.layoutDiscsComponent.layoutDiscs;
            }
            default: return null;
        }
    }
}
