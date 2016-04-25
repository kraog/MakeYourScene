package io.github.epelde.crispychainsaw.view;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.databinding.ActiviyBandDetailBinding;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.viewmodel.BandDetailViewModel;

/**
 * Created by epelde on 21/04/2016.
 */
public class BandDetailActivity extends AppCompatActivity {

    private static final String EXTRA_BAND = "io.github.epelde.crispychainsaw.view.EXTRA_BAND";

    public static void start(Context context, Band band) {
        Intent starter = new Intent(context, BandDetailActivity.class);
        starter.putExtra(EXTRA_BAND, band);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActiviyBandDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activiy_band_detail);
        Band band = (Band) getIntent().getSerializableExtra(EXTRA_BAND);
        binding.setBandDetailVM(new BandDetailViewModel(band));
    }
}
