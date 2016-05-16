package kraog.moveyourscene.view.bands;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.BandListActivityBinding;
import kraog.moveyourscene.model.data.MYSFirebase;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.view.concerts.ConcertListActivity;
import kraog.moveyourscene.view.discs.DiscListActivity;
import kraog.moveyourscene.view.MYSListActivity;
import kraog.moveyourscene.view.adapter.BandRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;
import kraog.moveyourscene.viewmodel.MYSListVM;
import kraog.moveyourscene.viewmodel.bands.BandListVM;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListActivity extends MYSListActivity implements BandRecyclerViewAdapter.BandRecyclerViewListener, BandListVM.BandListViewModelListener, MenuRecyclerViewAdapter.MenuRecyclerViewListener {

    private static final String EXTRA_BAND_FILTER = "kraog.moveyourscene.EXTRA_BAND_FILTER";
    BandListActivityBinding binding;
    static List<Band> bandListWrapper = new ArrayList<Band>();


    public BandListActivity(){
        super();
    }


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
        Toast.makeText(getApplicationContext(),"Bands loading...", Toast.LENGTH_SHORT);

      createNext(binding);

    }

    private void createNext(BandListActivityBinding binding){
        binding.setBandListVM(new BandListVM(this,this,this,this,new Band()));
        initToolbar(binding.toolbar,R.string.band_title);
        initTabs(binding.tabs);
        initDrawer(binding.drawerLayout,binding.toolbar);
        initSearchDialog(this);
        //initSearchFrame(binding.searchFrame,binding.searchSpinner);
    }


    @Override
    public void onItemClickedSuperior(View view,Band band) {
        BandDetailActivity.navigate(this,view.findViewById(R.id.image),band);
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
