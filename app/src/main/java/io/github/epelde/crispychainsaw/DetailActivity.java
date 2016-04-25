package io.github.epelde.crispychainsaw;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.drawable.DrawableUtils;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.AutoTransition;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.github.epelde.crispychainsaw.model.domain.Band;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_IMAGE = "io.github.epelde.crispychainsaw.extraImage";
    //private static final String EXTRA_TITLE = "io.github.epelde.crispychainsaw.extraTitle";
    private static final String EXTRA_BAND = "io.github.epelde.crispychainsaw.EXTRA_BAND";

    private CollapsingToolbarLayout collapsingToolbarLayout;
    FragmentManager fm = getSupportFragmentManager();

    LinearLayout buttonLayout;
    LinearLayout wholeLayout;
    LinearLayout songsLayout;
    Animation slide_down,slide_up,fab_toSongs,fab_toDetail;
    FloatingActionButton fabButton,fabButtonSongs;
    boolean start;
    AutoTransition transition;
    Scene scene1,scene2;

    private int lastTopValue = 0;


    private View.OnClickListener toSongsListener,toPropertiesListener;

    public static void navigate(AppCompatActivity activity, View transitionImage, Band band) {
        Intent intent = new Intent(activity, DetailActivity.class);
        //intent.putExtra(EXTRA_IMAGE, band.getImageUrl());
        //intent.putExtra(EXTRA_TITLE, band.getName());
        intent.putExtra(EXTRA_BAND, band);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @TargetApi(Build.VERSION_CODES.M)
    @SuppressWarnings("ConstantConditions")
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initActivityTransitions();
        setContentView(R.layout.activity_detail);

        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //String itemTitle = getIntent().getStringExtra(EXTRA_TITLE);
        Band band = (Band) getIntent().getSerializableExtra(EXTRA_BAND);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(band.getName());
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        final ImageView image = (ImageView) findViewById(R.id.image);
        Picasso.with(this).load(band.getImageUrl()).into(image, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                    public void onGenerated(Palette palette) {
                        applyPalette(palette);
                    }
                });
            }

            @Override
            public void onError() {

            }
        });
        //listaaaaaaaaaaaaaaaaaaaaaa start

        toSongsListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLayout.startAnimation(slide_down);
                songsLayout.startAnimation(slide_up);
                songsLayout.setVisibility(View.VISIBLE);
                buttonLayout.setVisibility(View.INVISIBLE);
                fabButton.setOnClickListener(toPropertiesListener);
                fabButton.startAnimation(fab_toSongs);
               /* CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fabButton.getLayoutParams();
                p.setAnchorId(View.GONE);;
                fabButton.setLayoutParams(p);*/
            }
        };
        toPropertiesListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songsLayout.startAnimation(slide_down);
                buttonLayout.startAnimation(slide_up);
                buttonLayout.setVisibility(View.VISIBLE);
                songsLayout.setVisibility(View.INVISIBLE);
                fabButton.setOnClickListener(toSongsListener);
                fabButton.startAnimation(fab_toDetail);
            }
        };

        slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_out_bottom);

        slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_bottom);

        fab_toSongs = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fabbutton_to_songs);

        fab_toDetail = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fabbutton_to_details);
        buttonLayout = (LinearLayout)findViewById(R.id.layout_botones);
        songsLayout = (LinearLayout)findViewById(R.id.layout_songs);

        initFab(fabButton);
        moveFabInit();


        //ScrollChangeListener(this);
        //listaaaaaaaaaaaaaaaaaaaaaa end

    }

    private void initFab(View v) {

        v = findViewById(R.id.fab);
        v.setOnClickListener(toSongsListener);
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v, "alpha",  1f, .3f);
        fadeOut.setDuration(2000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", .3f, 1f);
        fadeIn.setDuration(2000);

        final AnimatorSet mAnimationSet = new AnimatorSet();

        mAnimationSet.play(fadeIn).after(fadeOut);

        mAnimationSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });
        mAnimationSet.start();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void moveFabInit() {
        //create transition, set properties
        AutoTransition transition = new AutoTransition();
        transition.setDuration(5000);
        transition.setInterpolator(new AccelerateDecelerateInterpolator());

//initialize flag
        start=true;
    }

    @Override public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (NullPointerException e) {
            return false;
        }
    }

    private void initActivityTransitions() {
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Slide transition = new Slide();
            transition.excludeTarget(android.R.id.statusBarBackground, true);
            getWindow().setEnterTransition(transition);
            getWindow().setReturnTransition(transition);
        }*/
    }

    private void applyPalette(Palette palette) {
        int primaryDark = getResources().getColor(R.color.primary_dark);
        int primary = getResources().getColor(R.color.primary);
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary));
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark));
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
