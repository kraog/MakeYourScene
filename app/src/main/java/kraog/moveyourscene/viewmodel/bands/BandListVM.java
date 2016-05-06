package kraog.moveyourscene.viewmodel.bands;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;


import kraog.moveyourscene.R;
import kraog.moveyourscene.di.component.DaggerApplicationComponent;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.model.domain.Preference;
import kraog.moveyourscene.model.domain.User;
import kraog.moveyourscene.util.Funciones;
import kraog.moveyourscene.view.adapter.BandRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;
import kraog.moveyourscene.viewmodel.MYSListVM;

/**
 * Created by epelde on 20/04/2016.
 */
public class BandListVM extends MYSListVM {

    public ObservableArrayList<Band> bandList = new ObservableArrayList<Band>();
    public ObservableField<User> user = new ObservableField<User>();
    public ObservableArrayList<MenuDrawerItem> menuItemList  = new ObservableArrayList<MenuDrawerItem>();

    static BandRecyclerViewAdapter.BandRecyclerViewListener listener;
    public static MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener;
    private BandListViewModelListener mBandListViewModelListener;

    public BandListVM(BandRecyclerViewAdapter.BandRecyclerViewListener listener,
                      MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener,
                      BandListViewModelListener mBandListViewModelListener,
                      MYSListVMInterface mMYSListVMInterface,
                      Band bandFilter) {
        super(mMYSListVMInterface);
        DaggerApplicationComponent.create().inject(this);
        bandList.addAll(dm.getBands(bandFilter));
        Funciones.setStupidData(user,menuItemList);
        this.mBandListViewModelListener = mBandListViewModelListener;
        this.listener = listener;
        this.mListener = mListener;
        super.mMYSListVMInterface = mMYSListVMInterface;
    }

    @BindingAdapter({"bind:items"})
    public static void bindList(RecyclerView view, ObservableArrayList<Band> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setAdapter(new BandRecyclerViewAdapter(list, listener));
    }

    @BindingAdapter(value={"bind:menuitem","bind:iuser"},requireAll = false)
    public static void bindMenuList(RecyclerView view, ObservableArrayList<MenuDrawerItem> menuItemList, User user) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setHasFixedSize(true);
        view.setAdapter(new MenuRecyclerViewAdapter(user,menuItemList,mListener));
        layoutManager.setMeasuredDimension(100,100);
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
        user.set(new User());
        user.get().setMail("email_cojonudo@gmail.com");
        user.get().setName("ou yeahh");
        Preference pref = new Preference();
        pref.setUrl_photo("https://upload.wikimedia.org/wikipedia/commons/0/03/Jacinto_Benavente.jpg");
        user.get().setPreferences(pref);
        MenuDrawerItem mdi = new MenuDrawerItem();
        mdi.setTitle("Bandas");
        mdi.setImageResource(R.drawable.christian_cross);
        mdi.setActivity_related(MenuDrawerItem.Activity_Related.BAND_LIST);
        menuItemList.add(mdi);
        MenuDrawerItem mdi2 = new MenuDrawerItem();
        mdi2.setTitle("Álbumes");
        mdi2.setImageResource(R.drawable.music);
        mdi2.setActivity_related(MenuDrawerItem.Activity_Related.DISC_LIST);
        menuItemList.add(mdi2);
        MenuDrawerItem mdi3 = new MenuDrawerItem();
        mdi3.setTitle("Conciertos");
        mdi3.setImageResource(R.drawable.business);
        mdi3.setActivity_related(MenuDrawerItem.Activity_Related.CONCERT_LIST);
        menuItemList.add(mdi3);
    }

    public interface BandListViewModelListener {
        public void onNavigationItemSelectecCompleted(String message,MenuItem menuItem);
    }
}
