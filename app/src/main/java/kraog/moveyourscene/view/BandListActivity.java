package kraog.moveyourscene.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.BandListActivityBinding;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.view.adapter.BandRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;
import kraog.moveyourscene.viewmodel.bands.BandListVM;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListActivity extends AppCompatActivity implements BandRecyclerViewAdapter.BandRecyclerViewListener, BandListVM.BandListViewModelListener, MenuRecyclerViewAdapter.MenuRecyclerViewListener {

    private static final String EXTRA_BAND_FILTER = "kraog.moveyourscene.EXTRA_BAND_FILTER";
    BandListActivityBinding binding;


    public static void navigate(AppCompatActivity activity,View trantistionImage, Band bandFilter) {
        Intent intent = new Intent(activity, BandListActivity.class);
        intent.putExtra(EXTRA_BAND_FILTER, bandFilter);
        ActivityCompat.startActivity(activity,intent,null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Band bandFilter = (Band)intent.getSerializableExtra(EXTRA_BAND_FILTER);
        binding = DataBindingUtil.setContentView(this, R.layout.band_list_activity);
        binding.setBandListVM(new BandListVM(this,this,this,bandFilter));
        initToolbar();
        initTabs();
        initDrawer();

    }


    @Override
    public void onItemClickedSuperior(View view,Band band) {
        BandDetailActivity.navigate(this,view.findViewById(R.id.image),band);
    }


    /*
    * Initiates the toolbar
    */
    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.metal_fb);
        }
    }

    /*
     * generates the necessary tabs
     */
    private void initTabs()
    {
        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.ic_favorite_black_24dp));
        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.ic_home_black_24dp));
        binding.tabs.addTab(binding.tabs.newTab().setIcon(R.drawable.ic_settings_black_24dp));
        binding.tabs.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    private void initDrawer(){
        ActionBarDrawerToggle mDrawerToggle;
        mDrawerToggle = new ActionBarDrawerToggle(this,binding.drawerLayout,binding.toolbar,R.string.menu_drawer_open,R.string.menu_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);

                Animation fab_toSongs = AnimationUtils.loadAnimation(binding.getRoot().getContext(),
                        R.anim.fadein);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        binding.drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
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


    @Override
    public void onMenuItemClicked(MenuDrawerItem.Activity_Related relatedAct) {
        switch (relatedAct){
            case BAND_LIST:initDrawer();break;
            case CONCERT_LIST:initDrawer();break;
            case DISC_LIST:initDrawer();break;
        }
    }
}
