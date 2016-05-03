package kraog.moveyourscene.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.ItemMenuBinding;
import kraog.moveyourscene.databinding.MenuHeaderBinding;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.model.domain.User;
import kraog.moveyourscene.viewmodel.MenuHeaderVM;
import kraog.moveyourscene.viewmodel.MenuItemVM;

/**
 * Created by Gorka on 27/04/2016.
 */
public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MenuHeaderVM.MenuHeaderViewModelListener,MenuItemVM.MenuItemViewModelListener{

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    public List<MenuDrawerItem> menuList;
    public ObservableField<User> user;
    public MenuRecyclerViewListener mMenuRecyclerViewListener;

    public MenuRecyclerViewAdapter (User user, List<MenuDrawerItem> menuList, MenuRecyclerViewListener mMenuRecyclerViewListener){
        this.menuList = menuList;
        this.user = new ObservableField<User>();
        this.user.set(user);
        this.mMenuRecyclerViewListener = mMenuRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            ItemMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_menu,parent,false);
            MenuItemViewHolder vhItem = new MenuItemViewHolder(binding);
            return vhItem;
        } else if (viewType == TYPE_HEADER) {
            MenuHeaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.menu_header,parent,false);
            HeaderViewHolder vhHeader = new HeaderViewHolder(binding);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof HeaderViewHolder){
            HeaderViewHolder hvh = (HeaderViewHolder)holder;
            hvh.binding.setHeaderMenuVM(new MenuHeaderVM(user,this));
        } else {
            MenuItemViewHolder mivh = (MenuItemViewHolder)holder;
            mivh.binding.setMenuItemVM(new MenuItemVM(menuList.get(position-1),this));
        }
    }

    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)){
            return TYPE_HEADER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return menuList.size()+1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public void onMenuItemClicked(MenuDrawerItem.Activity_Related relatedAct) {
        this.mMenuRecyclerViewListener.onMenuItemClicked(relatedAct);
    }

    public class MenuItemViewHolder extends RecyclerView.ViewHolder{
        public ItemMenuBinding binding;

        public MenuItemViewHolder(ItemMenuBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{
        public MenuHeaderBinding binding;
        public HeaderViewHolder(MenuHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface MenuRecyclerViewListener{
        public void onMenuItemClicked(MenuDrawerItem.Activity_Related relatedAct);
    }
}
