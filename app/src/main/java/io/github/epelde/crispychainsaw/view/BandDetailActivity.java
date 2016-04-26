package io.github.epelde.crispychainsaw.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.databinding.ActiviyBandDetailBinding;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.viewmodel.BandDetailViewModel;

/**
 * Created by epelde on 21/04/2016.
 */
public class BandDetailActivity extends AppCompatActivity implements BandDetailViewModel.BandDetailViewModelListener{

    private static final String EXTRA_BAND = "io.github.epelde.crispychainsaw.EXTRA_BAND";
    private static final String EXTRA_IMAGE = "io.github.epelde.crispychainsaw.extraImage";
    ActiviyBandDetailBinding binding;
    Animation slide_down,slide_up,fab_toSongs,fab_toDetail;
    private View.OnClickListener toSongsListener,toPropertiesListener;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Band band = (Band) getIntent().getSerializableExtra(EXTRA_BAND);
        BandDetailViewModel bandDetailVM = new BandDetailViewModel(band,this);
        binding = DataBindingUtil.setContentView(this, R.layout.activiy_band_detail);
        binding.setBandDetailVM(bandDetailVM);

        ViewCompat.setTransitionName(binding.appBarLayout, EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));


        toSongsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutBotones.startAnimation(slide_down);
                binding.layoutSongs.startAnimation(slide_up);
                binding.layoutSongs.setVisibility(View.VISIBLE);
                binding.layoutBotones.setVisibility(View.INVISIBLE);
                binding.fab.setOnClickListener(toPropertiesListener);
                binding.fab.startAnimation(fab_toSongs);
                binding.fab.setImageResource(R.drawable.ic_favorite_black_24dp);
                ((CoordinatorLayout.LayoutParams)binding.fab.getLayoutParams()).anchorGravity=Gravity.END|Gravity.BOTTOM|Gravity.LEFT;
            }
        };
        toPropertiesListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layoutSongs.startAnimation(slide_down);
                binding.layoutBotones.startAnimation(slide_up);
                binding.layoutBotones.setVisibility(View.VISIBLE);
                binding.layoutSongs.setVisibility(View.INVISIBLE);
                binding.fab.setOnClickListener(toSongsListener);
                binding.fab.startAnimation(fab_toDetail);
                binding.fab.setImageResource(R.drawable.ic_settings_black_24dp);
                ((CoordinatorLayout.LayoutParams)binding.fab.getLayoutParams()).anchorGravity=Gravity.END|Gravity.BOTTOM|Gravity.RIGHT;
            }
        };
        binding.fab.setOnClickListener(toSongsListener);

        slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_out_bottom);

        slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_bottom);

        fab_toSongs = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fabbutton_to_songs);

        fab_toDetail = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fabbutton_to_details);
    }


    public static void navigate(AppCompatActivity activity,View trantistionImage, Band band) {
        Intent intent = new Intent(activity, BandDetailActivity.class);
        //intent.putExtra(EXTRA_IMAGE, band.getImageUrl());
        //intent.putExtra(EXTRA_TITLE, band.getName());
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

    private void updateBackground(FloatingActionButton fab, Palette palette) {
        int lightVibrantColor = palette.getLightVibrantColor(getResources().getColor(android.R.color.white));
        int vibrantColor = palette.getVibrantColor(getResources().getColor(R.color.accent));
        fab.setRippleColor(lightVibrantColor);
        fab.setBackgroundTintList(ColorStateList.valueOf(vibrantColor));
    }
}
