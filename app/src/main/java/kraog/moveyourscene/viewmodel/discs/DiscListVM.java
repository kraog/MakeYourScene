package kraog.moveyourscene.viewmodel.discs;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import kraog.moveyourscene.R;
import kraog.moveyourscene.di.component.DaggerApplicationComponent;
import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Disc;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.model.domain.Preference;
import kraog.moveyourscene.model.domain.User;
import kraog.moveyourscene.util.Funciones;
import kraog.moveyourscene.view.adapter.DiscRecyclerViewAdapter;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;
import kraog.moveyourscene.viewmodel.MYSListVM;
import rx.Subscriber;


public class DiscListVM extends MYSListVM {

    public ObservableArrayList<Disc> discList = new ObservableArrayList<Disc>();
    public static ObservableField<User> user = new ObservableField<User>();
    public ObservableArrayList<MenuDrawerItem> menuItemList  = new ObservableArrayList<MenuDrawerItem>();
    static List<Disc> discListWrapper;

    static DiscRecyclerViewAdapter.DiscRecyclerViewListener listener;
    static MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener;
    private DiscListViewModelListener mDiscListViewModelListener;

    public DiscListVM(DiscRecyclerViewAdapter.DiscRecyclerViewListener listener,
                      MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener,
                      DiscListViewModelListener mDiscListViewModelListener,
                      MYSListVMInterface mMYSListVMInterface,
                      Disc discFilter) {
        super(mMYSListVMInterface);
        Funciones.setStupidData(user,menuItemList);
        this.mDiscListViewModelListener = mDiscListViewModelListener;
        this.listener = listener;
        this.mListener = mListener;
        super.mMYSListVMInterface = mMYSListVMInterface;
        this.discListWrapper = new ArrayList<Disc>();


        mysfb.getDiscsSnapShot().subscribe(new Subscriber<DataSnapshot>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(DataSnapshot ds) {
                for (DataSnapshot postSnapshot: ds.getChildren()) {
                    discListWrapper.add(postSnapshot.getValue(Disc.class));
                }
                discList.addAll(discListWrapper);
            }
        });
    }

    @BindingAdapter({"bind:items"})
    public static void bindList(RecyclerView view, ObservableArrayList<Disc> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setAdapter(new DiscRecyclerViewAdapter(list, listener));
    }
    @BindingAdapter(value={"bind:itemmenu","user"}, requireAll = false)
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
                mDiscListViewModelListener.onNavigationItemSelectecCompleted(menuItem.getTitle() + " pressed",menuItem);
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

    public interface DiscListViewModelListener {
        public void onNavigationItemSelectecCompleted(String message, MenuItem menuItem);
    }
}
