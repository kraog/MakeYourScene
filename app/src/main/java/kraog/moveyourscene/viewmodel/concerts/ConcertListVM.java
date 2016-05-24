package kraog.moveyourscene.viewmodel.concerts;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;


import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import kraog.moveyourscene.R;
import kraog.moveyourscene.model.domain.Concert;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.model.domain.Preference;
import kraog.moveyourscene.model.domain.User;
import kraog.moveyourscene.util.Funciones;
import kraog.moveyourscene.view.adapter.ConcertRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;
import kraog.moveyourscene.viewmodel.MYSListVM;
import rx.Subscriber;

public class ConcertListVM extends MYSListVM {

    public ObservableArrayList<Concert> concertList = new ObservableArrayList<Concert>();
    public ObservableField<User> user = new ObservableField<User>();
    public ObservableArrayList<MenuDrawerItem> menuItemList  = new ObservableArrayList<MenuDrawerItem>();
    static List<Concert> concertListWrapper;
    static ConcertRecyclerViewAdapter.ConcertRecyclerViewListener listener;
    private ConcertListViewModelListener mConcertListViewModelListener;

    public ConcertListVM(ConcertRecyclerViewAdapter.ConcertRecyclerViewListener listener,
                         MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener,
                         ConcertListViewModelListener mConcertListViewModelListener,
                         MYSListVMInterface mMYSListVMInterface,
                         Concert concertFilter) {
        super(mMYSListVMInterface,mListener);
        this.concertListWrapper = new ArrayList<Concert>();
        Funciones.setStupidData(user,menuItemList);
        this.mConcertListViewModelListener = mConcertListViewModelListener;
        this.listener = listener;
        super.mMYSListVMInterface = mMYSListVMInterface;
        mysfb.getConcertsSnapShot().subscribe(new Subscriber<DataSnapshot>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DataSnapshot ds) {
                for (DataSnapshot postSnapshot: ds.getChildren()) {
                    concertListWrapper.add(postSnapshot.getValue(Concert.class));
                }
                concertList.addAll(concertListWrapper);
            }
        });
    }

    @BindingAdapter({"bind:items"})
    public static void bindList(RecyclerView view, ObservableArrayList<Concert> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setAdapter(new ConcertRecyclerViewAdapter(list, listener));
    }

    public NavigationView.OnNavigationItemSelectedListener getNavItemSelected() {
       return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mConcertListViewModelListener.onNavigationItemSelectecCompleted(menuItem.getTitle() + " pressed",menuItem);
                menuItem.setChecked(true);
                return true;
            }
        };
    }

    public interface ConcertListViewModelListener {
        public void onNavigationItemSelectecCompleted(String message, MenuItem menuItem);
    }
}
