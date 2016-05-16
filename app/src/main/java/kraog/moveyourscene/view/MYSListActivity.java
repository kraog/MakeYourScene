package kraog.moveyourscene.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;

import kraog.moveyourscene.R;
import kraog.moveyourscene.viewmodel.MYSListVM;

/**
 * Created by Gorka on 04/05/2016.
 */
public abstract class MYSListActivity extends AppCompatActivity implements MYSListVM.MYSListVMInterface{


    public static final String ALLTAG = "kraog.moveyourscene.view.ALLTAG";
    public static final String FAVTAG = "kraog.moveyourscene.view.FAVTAG";
    public static final String LOOKTAG = "kraog.moveyourscene.view.LOOKTAG";
    public Animation slide_down,slide_up;
    public LinearLayout searchFrame;
    public Dialog searchDialog;


    public MYSListActivity(){

        Firebase.setAndroidContext(this);
    }
    /*
    * Initiates the toolbar
    */
    public void initToolbar(Toolbar toolBar, int titleId) {
        setSupportActionBar(toolBar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle(titleId);
        }
    }

    /*
     * Initiates the upper tabs
     */
    public void initTabs(TabLayout tabber)
    {
        tabber.addTab(tabber.newTab().setIcon(R.drawable.layers_icon).setTag(ALLTAG));
        tabber.addTab(tabber.newTab().setIcon(R.drawable.plain_heart).setTag(FAVTAG));
        tabber.addTab(tabber.newTab().setIcon(R.drawable.magnifier).setTag(LOOKTAG));
        tabber.setTabGravity(TabLayout.GRAVITY_FILL);
    }

    /*
     * Initiates the drawer component
     */
    public void initDrawer(final DrawerLayout dl, Toolbar toolBar){
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
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.rock_gesture, this.getTheme());
        mDrawerToggle.setHomeAsUpIndicator(drawable);
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dl.isDrawerVisible(GravityCompat.START)) {
                    dl.closeDrawer(GravityCompat.START);
                } else {
                    dl.openDrawer(GravityCompat.START);
                }
            }
        });
        mDrawerToggle.syncState();
    }


    /*
     * Initiates the search component inside toolbar
     */
    public void initSearchFrame(LinearLayout searchFrame, Spinner searchSpinner){

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.search_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        searchSpinner.setAdapter(adapter);
        this.searchFrame = searchFrame;
        this.searchFrame.setVisibility(View.INVISIBLE);
        LinearLayout.LayoutParams params= (LinearLayout.LayoutParams)this.searchFrame.getLayoutParams();
        params.height = 0;
        this.searchFrame.setLayoutParams(params);
        slide_down = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_out_bottom);

        slide_up = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_bottom);
    }

    @Override
    public void onTabSelectedStart(TabLayout.Tab tab){

        if (searchDialog.isShowing()){
            searchDialog.hide();
        } else if (tab.getTag().equals(LOOKTAG)){
            onTabSelected(tab);
            searchDialog.show();
        }
    }

    public void initSearchDialog(Context context){
        ContextThemeWrapper ctw = new ContextThemeWrapper( context, R.style.style_transparent );
        AlertDialog.Builder builder = new AlertDialog.Builder( ctw );
        builder.setView(R.layout.search_dialog);
        searchDialog =builder.create();
        searchDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public  void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(4000);
        slide.setSlideEdge(Gravity.TOP);
        getWindow().setExitTransition(slide);
    }

    public abstract void onTabSelected(TabLayout.Tab tab);
    public abstract void initCustomSearch();

}
