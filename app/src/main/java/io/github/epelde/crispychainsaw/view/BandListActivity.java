package io.github.epelde.crispychainsaw.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.databinding.ActivityBandListBinding;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.view.adapter.BandRecyclerViewAdapter;
import io.github.epelde.crispychainsaw.viewmodel.BandListViewModel;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListActivity extends AppCompatActivity implements BandRecyclerViewAdapter.BandRecyclerViewListener, BandListViewModel.BandListViewModelListener {

    ActivityBandListBinding binding;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_band_list);
        binding.setBandListVM(new BandListViewModel(this));
        initToolbar();
    }


    @Override
    public void onItemClickedSuperior(View view,Band band) {
        BandDetailActivity.navigate(this,view.findViewById(R.id.image),band);
    }


    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onNavigationItemselectecCompleted(String message) {
        binding.drawerLayout.closeDrawers();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
