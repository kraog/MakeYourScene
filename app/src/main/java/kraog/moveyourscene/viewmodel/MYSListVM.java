package kraog.moveyourscene.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.widget.LinearLayout;

import javax.inject.Inject;

import kraog.moveyourscene.di.component.DaggerApplicationComponent;
import kraog.moveyourscene.model.data.DataManager;
import kraog.moveyourscene.model.data.MYSFirebase;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.model.domain.User;
import kraog.moveyourscene.view.adapter.MenuRecyclerViewAdapter;

/**
 * Created by Gorka on 02/05/2016.
 */
public class MYSListVM {
    @Inject
    public DataManager dm;
    @Inject
    public MYSFirebase mysfb;

    public static MYSListVMInterface mMYSListVMInterface;
    public static MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener;


    public MYSListVM(MYSListVMInterface mMYSListVMInterface,
                     MenuRecyclerViewAdapter.MenuRecyclerViewListener mListener){
        DaggerApplicationComponent.create().inject(this);
        this.mMYSListVMInterface = mMYSListVMInterface;
        this.mListener = mListener;
    }
    public TabLayout.OnTabSelectedListener getTabListener(){

        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                MYSListVM.this.mMYSListVMInterface.onTabSelectedStart(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                MYSListVM.this.mMYSListVMInterface.onTabSelectedStart(tab);
            }
        };
    }

    public interface MYSListVMInterface{
        void onTabSelectedStart(TabLayout.Tab tab);
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

}
