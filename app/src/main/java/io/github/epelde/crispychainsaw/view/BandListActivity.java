package io.github.epelde.crispychainsaw.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.databinding.ActivityBandListBinding;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.model.domain.Header;
import io.github.epelde.crispychainsaw.model.domain.MenuDrawerItem;
import io.github.epelde.crispychainsaw.view.adapter.BandRecyclerViewAdapter;
import io.github.epelde.crispychainsaw.view.adapter.MenuRecyclerViewAdapter;
import io.github.epelde.crispychainsaw.viewmodel.BandListViewModel;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListActivity extends AppCompatActivity implements BandRecyclerViewAdapter.BandRecyclerViewListener, BandListViewModel.BandListViewModelListener, MenuRecyclerViewAdapter.MenuRecyclerViewListener {

    ActivityBandListBinding binding;
    String TITLES[] = {"Home","Events","Mail","Shop","Travel"};
    int ICONS[] = {R.drawable.ic_add_black,R.drawable.ic_favorite_black_24dp,R.drawable.ic_menu_black_24dp,R.drawable.ic_home_black_24dp,R.drawable.ic_settings_black_24dp};

    String NAME = "Akash Bangad";
    String EMAIL = "akash.bangad@android4devs.com";
    int PROFILE = R.drawable.euskobands_ico;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_band_list);
        binding.setBandListVM(new BandListViewModel(this,this,this));
        initToolbar();

        DrawerLayout Drawer;
        ActionBarDrawerToggle mDrawerToggle;

       /* RecyclerView menuRecycler;
        MenuRecyclerViewAdapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;
        menuRecycler = (RecyclerView) findViewById(R.id.RecyclerView);

        menuRecycler.setHasFixedSize(true);

        mAdapter = new MenuRecyclerViewAdapter(new Header(),new ArrayList<MenuDrawerItem>());       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)

        menuRecycler.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);

        menuRecycler.setLayoutManager(mLayoutManager);*/


        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,binding.toolbar,R.string.app_name,R.string.favourite){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State


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
    public void onNavigationItemSelectecCompleted(String message, MenuItem menuItem) {
        Snackbar.make(this.getCurrentFocus(),menuItem.getTitle() + " pressed" , Snackbar.LENGTH_LONG).show();
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
