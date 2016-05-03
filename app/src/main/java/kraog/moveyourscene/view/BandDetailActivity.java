package kraog.moveyourscene.view;

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

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.BandDetailActivityBinding;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.viewmodel.bands.BandDetailVM;

/**
 * Created by epelde on 21/04/2016.
 */
public class BandDetailActivity extends AppCompatActivity implements BandDetailVM.BandDetailViewModelListener{

    private static final String EXTRA_BAND = "kraog.moveyourscene.EXTRA_BAND";
    private static final String EXTRA_IMAGE = "kraog.moveyourscene.extraImage";
    BandDetailActivityBinding binding;
    Animation slide_down,slide_up;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Band band = (Band) getIntent().getSerializableExtra(EXTRA_BAND);
        BandDetailVM bandDetailVM = new BandDetailVM(band,this,this.getApplicationContext());
        binding = DataBindingUtil.setContentView(this, R.layout.band_detail_activity);
        binding.setBandDetailVM(bandDetailVM);

        ViewCompat.setTransitionName(binding.appBarLayout, EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
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
    public void onFabListenerClicked(boolean detail) {
        if(detail){
            binding.layoutBotones.startAnimation(slide_down);
            binding.layoutSongs.startAnimation(slide_up);
            binding.layoutSongs.setVisibility(View.VISIBLE);
            binding.layoutBotones.setVisibility(View.INVISIBLE);
        } else {
            binding.layoutBotones.startAnimation(slide_up);
            binding.layoutSongs.startAnimation(slide_down);
            binding.layoutSongs.setVisibility(View.INVISIBLE);
            binding.layoutBotones.setVisibility(View.VISIBLE);
        }
    }

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.accent));
        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }
}
