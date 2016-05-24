package kraog.moveyourscene.viewmodel.bands;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kraog.moveyourscene.R;
import kraog.moveyourscene.model.data.MYSFirebase;
import kraog.moveyourscene.model.data.RxFirebase;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.model.domain.Preference;
import kraog.moveyourscene.model.domain.User;
import kraog.moveyourscene.util.Funciones;
import kraog.moveyourscene.view.adapter.BandRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;
import kraog.moveyourscene.viewmodel.MYSListVM;
import rx.Observable;
import rx.Subscriber;

public class BandListVM extends MYSListVM {

    public ObservableArrayList<Band> bandList = new ObservableArrayList<Band>();
    public ObservableField<User> user = new ObservableField<User>();
    public ObservableArrayList<MenuDrawerItem> menuItemList  = new ObservableArrayList<MenuDrawerItem>();
    static List<Band> bandListWrapper;
    static BandRecyclerViewAdapter.BandRecyclerViewListener listener;
    static MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener;
    private BandListViewModelListener mBandListViewModelListener;

    public BandListVM(BandRecyclerViewAdapter.BandRecyclerViewListener listener,
                      MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener,
                      BandListViewModelListener mBandListViewModelListener,
                      MYSListVMInterface mMYSListVMInterface,
                      Band bandFilter) {
        super(mMYSListVMInterface,mListener);
        this.bandListWrapper = new ArrayList<Band>();
        Funciones.setStupidData(user,menuItemList);
        this.mBandListViewModelListener = mBandListViewModelListener;
        this.listener = listener;
        super.mMYSListVMInterface = mMYSListVMInterface;
        mysfb.getBandsSnapShot().subscribe(new Subscriber<DataSnapshot>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DataSnapshot ds) {
                for (DataSnapshot postSnapshot: ds.getChildren()) {
                    bandListWrapper.add(postSnapshot.getValue(Band.class));
                }
                bandList.addAll(bandListWrapper);
            }
        });
    }

    @BindingAdapter({"bind:items"})
    public static void bindList(RecyclerView view, ObservableArrayList<Band> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setAdapter(new BandRecyclerViewAdapter(list, listener));
    }

    public NavigationView.OnNavigationItemSelectedListener getNavItemSelected() {
       return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mBandListViewModelListener.onNavigationItemSelectecCompleted(menuItem.getTitle() + " pressed",menuItem);
                menuItem.setChecked(true);
                return true;
            }
        };
    }


    public interface BandListViewModelListener {
        public void onNavigationItemSelectecCompleted(String message,MenuItem menuItem);
    }
}
