package io.github.epelde.crispychainsaw.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import javax.inject.Inject;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.di.component.DaggerApplicationComponent;
import io.github.epelde.crispychainsaw.model.data.DataManager;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.model.domain.Header;
import io.github.epelde.crispychainsaw.model.domain.MenuDrawerItem;
import io.github.epelde.crispychainsaw.view.adapter.BandRecyclerViewAdapter;
import io.github.epelde.crispychainsaw.view.adapter.MenuRecyclerViewAdapter;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListViewModel {
    @Inject
    DataManager dm;

    public ObservableArrayList<Band> bandList = new ObservableArrayList<Band>();
    public static ObservableField<Header> header = new ObservableField<Header>();
    public ObservableArrayList<MenuDrawerItem> menuItemList  = new ObservableArrayList<MenuDrawerItem>();

    static BandRecyclerViewAdapter.BandRecyclerViewListener listener;
    static MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener;
    private BandListViewModelListener mBandListViewModelListener;

    public BandListViewModel(BandRecyclerViewAdapter.BandRecyclerViewListener listener, MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener,BandListViewModelListener mBandListViewModelListener) {
        DaggerApplicationComponent.create().inject(this);
        bandList.addAll(dm.getBands());
        setStupidData();
        this.mBandListViewModelListener = mBandListViewModelListener;
        this.listener = listener;
        this.mListener = mListener;
    }

    @BindingAdapter({"bind:items"})
    public static void bindList(RecyclerView view, ObservableArrayList<Band> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setAdapter(new BandRecyclerViewAdapter(list, listener));
    }
    @BindingAdapter(value={"bind:itemmenu","header"}, requireAll = false)
    public static void bindMenuList(RecyclerView view, ObservableArrayList<MenuDrawerItem> menuItemList, Header header) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setAdapter(new MenuRecyclerViewAdapter(header,menuItemList,mListener));
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
    private void setStupidData(){
        header.set(new Header());
        header.get().setEmail("iiiimeee8il");
        header.get().setProfile(R.drawable.euskobands_ico);
        MenuDrawerItem mdi = new MenuDrawerItem();
        mdi.setTitle("Últimos Lanzamientos");
        mdi.setImageResource(R.drawable.ic_add_black);
        menuItemList.add(mdi);
        MenuDrawerItem mdi2 = new MenuDrawerItem();
        mdi2.setTitle("Próximos conciertos");
        mdi2.setImageResource(R.drawable.ic_add_black);
        menuItemList.add(mdi2);
        MenuDrawerItem mdi3 = new MenuDrawerItem();
        mdi3.setTitle("Últimos Lanzamientos");
        mdi3.setImageResource(R.drawable.ic_add_black);
        menuItemList.add(mdi3);
    }

    public interface BandListViewModelListener {
        public void onNavigationItemSelectecCompleted(String message,MenuItem menuItem);
    }
}
