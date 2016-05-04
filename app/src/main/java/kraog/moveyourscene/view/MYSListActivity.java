package kraog.moveyourscene.view;

import android.support.design.widget.TabLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import kraog.moveyourscene.R;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;

/**
 * Created by Gorka on 04/05/2016.
 */
public class MYSListActivity extends AppCompatActivity{



    /*
    * Initiates the toolbar
    */
    public void initToolbar(Toolbar toolBar) {
        setSupportActionBar(toolBar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.rock_gesture);
        }
    }
    public void initTabs(TabLayout tabber)
    {
        tabber.addTab(tabber.newTab().setIcon(R.drawable.layers_icon));
        tabber.addTab(tabber.newTab().setIcon(R.drawable.plain_heart));
        tabber.addTab(tabber.newTab().setIcon(R.drawable.sharing_interface));
        tabber.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    public void initDrawer(DrawerLayout dl, Toolbar toolBar){
        ActionBarDrawerToggle mDrawerToggle;
        mDrawerToggle = new ActionBarDrawerToggle(this,dl,toolBar,R.string.menu_drawer_open,R.string.menu_drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) { super.onDrawerOpened(drawerView); }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        dl.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

}
