package kraog.moveyourscene.view.concerts;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.databinding.DataBindingUtil;
import android.location.Address;
import android.location.Geocoder;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.BandDetailActivityBinding;
import kraog.moveyourscene.databinding.ConcertDetailActivityBinding;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Concert;
import kraog.moveyourscene.util.Funciones;
import kraog.moveyourscene.viewmodel.bands.BandDetailVM;
import kraog.moveyourscene.viewmodel.concerts.ConcertDetailVM;

/**
 * Created by epelde on 21/04/2016.
 */
public class ConcertDetailActivity extends AppCompatActivity implements ConcertDetailVM.ConcertDetailViewModelListener, OnMapReadyCallback {

    private static final String EXTRA_CONCERT = "kraog.moveyourscene.EXTRA_CONCERT";
    private static final String EXTRA_IMAGE = "kraog.moveyourscene.EXTRA_IMAGE";
    public static final String CARD_BIO = "kraog.moveyourscene.CARD_BIO";
    public static final String CARD_MAP = "kraog.moveyourscene.CARD_MAP";
    ConcertDetailActivityBinding binding;
    ConcertDetailVM concertDetailVM;
    MapView mapView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Concert concert = (Concert) getIntent().getSerializableExtra(EXTRA_CONCERT);
        concertDetailVM = new ConcertDetailVM(concert,this,this,this.getApplicationContext());
        binding = DataBindingUtil.setContentView(this, R.layout.concert_detail_activity);
        binding.setConcertDetailVM(concertDetailVM);

        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.mys_profile).setTag(CARD_BIO));
        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.map_location).setTag(CARD_MAP));

        ViewCompat.setTransitionName(binding.appBarLayout, EXTRA_IMAGE);
        supportPostponeEnterTransition();

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapView = binding.layoutMapComponent.mapObjectId;
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);


        binding.collapsingToolbar.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }



    public static void navigate(AppCompatActivity activity,View trantistionImage, Concert concert) {
        Intent intent = new Intent(activity, ConcertDetailActivity.class);
        intent.putExtra(EXTRA_CONCERT, concert);
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
        supportStartPostponedEnterTransition();
    }


    @Override
    public void onButtonListenerClicked(String relatedCard) {
        showCard(cardRelated(relatedCard));
    }



    private void showCard(LinearLayout card){
       /* binding.layoutButtonsComponent.layoutButtons.startAnimation(slide_down);
        binding.layoutButtonsComponent.layoutButtons.setVisibility(View.INVISIBLE);
        card.startAnimation(slide_up);*/
        card.setVisibility(View.VISIBLE);
    }
    public LinearLayout cardRelated(String relatedCard) {
        switch (relatedCard) {
            case CARD_BIO: {
                return binding.layoutBioComponent.biolayout;
            }
            case CARD_MAP: {
                return binding.layoutMapComponent.maplayout;
            }
            default: return null;
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.setMyLocationEnabled(true);
        Geocoder dc = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = new ArrayList<Address>();
        try {
            addresses = dc.getFromLocationName("Bilbao",5);
        } catch (Exception ex){
            //// TODO: 01/06/2016  
        }


        LatLng bilbao = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
        googleMap.addMarker(new MarkerOptions().position(bilbao).title("Marker in Bilbao"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(bilbao,12));

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
