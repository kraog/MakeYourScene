package kraog.moveyourscene.view.concerts;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.ConcertListActivityBinding;
import kraog.moveyourscene.databinding.DiscListActivityBinding;
import kraog.moveyourscene.model.domain.Concert;
import kraog.moveyourscene.model.domain.Disc;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.view.MYSListActivity;
import kraog.moveyourscene.view.adapter.ConcertRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.DiscRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;
import kraog.moveyourscene.view.bands.BandListActivity;
import kraog.moveyourscene.view.discs.DiscDetailActivity;
import kraog.moveyourscene.view.discs.DiscListActivity;
import kraog.moveyourscene.viewmodel.concerts.ConcertListVM;
import kraog.moveyourscene.viewmodel.discs.DiscListVM;

public class ConcertListActivity extends MYSListActivity implements ConcertRecyclerViewAdapter.ConcertRecyclerViewListener, ConcertListVM.ConcertListViewModelListener, MenuRecyclerViewAdapter.MenuRecyclerViewListener  {

    private static final String EXTRA_CONCERT_FILTER = "kraog.moveyourscene.EXTRA_CONCERT_FILTER";
    ConcertListActivityBinding binding;

    public ConcertListActivity(){
        super();
    }

    public static void navigate(AppCompatActivity activity,View trantistionImage, Concert concertFilter) {
        Intent intent = new Intent(activity, ConcertListActivity.class);
        intent.putExtra(EXTRA_CONCERT_FILTER,concertFilter);
        ActivityCompat.startActivity(activity,intent,null);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Concert concertFilter = (Concert)intent.getSerializableExtra(EXTRA_CONCERT_FILTER);
        binding = DataBindingUtil.setContentView(this, R.layout.concert_list_activity);
        binding.setConcertListVM(new ConcertListVM(this,this,this,this,concertFilter));
        initToolbar(binding.toolbar,R.string.concert_title);
        initTabs(binding.tabs);
        initDrawer(binding.drawerLayout,binding.toolbar);
        initSearchDialog(this);

    }


    @Override
    public void onItemClickedSuperior(View view,Concert concert) {
        ConcertDetailActivity.navigate(this,view.findViewById(R.id.image),concert);
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
            case DISC_LIST:
                DiscListActivity.navigate(this,null,null);break;
            case CONCERT_LIST:
                ConcertListActivity.navigate(this,null,null);break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //// TODO: 16/05/2016
    }

    @Override
    public void initCustomSearch() {
        //// TODO: 16/05/2016  
        
    }
}
