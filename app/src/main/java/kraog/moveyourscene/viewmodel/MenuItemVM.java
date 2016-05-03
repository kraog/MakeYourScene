package kraog.moveyourscene.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import kraog.moveyourscene.model.domain.MenuDrawerItem;

/**
 * Created by Gorka on 27/04/2016.
 */
public class MenuItemVM {
    private MenuItemViewModelListener mMenuItemViewModelListener;
    public ObservableField<MenuDrawerItem> menuDrawerItem;

    public MenuItemVM(MenuDrawerItem menuDrawerItem, MenuItemViewModelListener mMenuItemViewModelListener) {
        this.menuDrawerItem = new ObservableField<MenuDrawerItem>();
        this.menuDrawerItem.set(menuDrawerItem);
        this.mMenuItemViewModelListener = mMenuItemViewModelListener;
    }

    @BindingAdapter({"bind:menuItemImage"})
    public static void setMenuItemImage(View v, int url){
        //picasso
    }
    public OnClickListener setMListener() {
        return new OnClickListener() {
            @Override
            public void onClick(View v) {
        /*depends on*/ mMenuItemViewModelListener.onMenuItemClicked(menuDrawerItem.get().getActivity_related());
            }
        };
    }

    public interface MenuItemViewModelListener {
        public void onMenuItemClicked(MenuDrawerItem.Activity_Related relatedAct);
    }
}
