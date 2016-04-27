package io.github.epelde.crispychainsaw.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import javax.inject.Inject;

import io.github.epelde.crispychainsaw.di.component.DaggerApplicationComponent;
import io.github.epelde.crispychainsaw.model.data.DataManager;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.view.adapter.BandRecyclerViewAdapter;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListViewModel {
    @Inject
    DataManager dm;

    public ObservableArrayList<Band> bandList = new ObservableArrayList<Band>();

    static BandRecyclerViewAdapter.BandRecyclerViewListener listener;
    private BandListViewModelListener mBandListViewModelListener;

    public BandListViewModel(BandRecyclerViewAdapter.BandRecyclerViewListener listener) {
        DaggerApplicationComponent.create().inject(this);
        bandList.addAll(dm.getBands());
        this.listener = listener;
    }

    @BindingAdapter("bind:items")
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
                mBandListViewModelListener.onNavigationItemselectecCompleted(menuItem.getTitle() + " pressed");
                Snackbar.make(menuItem.getActionView(),menuItem.getTitle() + " pressed" , Snackbar.LENGTH_LONG).show();
                menuItem.setChecked(true);
                return true;
            }
        };
    }

    public interface BandListViewModelListener {
        public void onNavigationItemselectecCompleted(String message);
    }
}
