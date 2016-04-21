package io.github.epelde.crispychainsaw.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.databinding.ActivityBandListBinding;
import io.github.epelde.crispychainsaw.viewmodel.BandListViewModel;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBandListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_band_list);
        binding.setBandListVM(new BandListViewModel());
    }
}
