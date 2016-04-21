package io.github.epelde.crispychainsaw.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import io.github.epelde.crispychainsaw.model.data.DataManager;
import io.github.epelde.crispychainsaw.model.data.DataManagerImpl;
import io.github.epelde.crispychainsaw.model.domain.Band;
import io.github.epelde.crispychainsaw.view.adapter.BandRecyclerViewAdapter;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListViewModel {

    public ObservableArrayList<Band> bandList = new ObservableArrayList<Band>();

    static BandRecyclerViewAdapter.BandRecyclerViewListener listener;

    public BandListViewModel(BandRecyclerViewAdapter.BandRecyclerViewListener listener) {
        DataManager dm = new DataManagerImpl();
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
}
