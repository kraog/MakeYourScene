package kraog.moveyourscene.view.discs;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.DiscListActivityBinding;
import kraog.moveyourscene.model.domain.Disc;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.view.MYSListActivity;
import kraog.moveyourscene.view.bands.BandListActivity;
import kraog.moveyourscene.view.adapter.DiscRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;
import kraog.moveyourscene.viewmodel.MYSListVM;
import kraog.moveyourscene.viewmodel.discs.DiscListVM;

/**
 * Created by epelde on 20/04/2016.
 */
public class DiscListActivity extends MYSListActivity implements DiscRecyclerViewAdapter.DiscRecyclerViewListener, DiscListVM.DiscListViewModelListener, MenuRecyclerViewAdapter.MenuRecyclerViewListener  {

    private static final String EXTRA_DISC_FILTER = "kraog.moveyourscene.EXTRA_DISC_FILTER";
    DiscListActivityBinding binding;

    public DiscListActivity(){
        super();
    }

    public static void navigate(AppCompatActivity activity,View trantistionImage, Disc discFilter) {
        Intent intent = new Intent(activity, DiscListActivity.class);
        intent.putExtra(EXTRA_DISC_FILTER,discFilter);
        ActivityCompat.startActivity(activity,intent,null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Disc discFilter = (Disc)intent.getSerializableExtra(EXTRA_DISC_FILTER);
        binding = DataBindingUtil.setContentView(this, R.layout.disc_list_activity);
        binding.setDiscListVM(new DiscListVM(this,this,this,this,discFilter));
        initToolbar(binding.toolbar,R.string.disc_title);
        initTabs(binding.tabs);
        initDrawer(binding.drawerLayout,binding.toolbar);
        initSearchFrame(binding.searchFrame,binding.searchSpinner);

    }


    @Override
    public void onItemClickedSuperior(View view,Disc disc) {
        DiscDetailActivity.navigate(this,view.findViewById(R.id.image),disc);
    }



    /*
     * generates the necessary tabs
     */

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
            case BAND_LIST:
                BandListActivity.navigate(this,null,null);break;
            case DISC_LIST:DiscListActivity.navigate(this,null,null);break;
            //case CONCERT_LIST:initDrawer();break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }
}
