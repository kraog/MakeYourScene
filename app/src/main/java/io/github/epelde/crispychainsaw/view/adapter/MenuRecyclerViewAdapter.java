package io.github.epelde.crispychainsaw.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import io.github.epelde.crispychainsaw.R;
import io.github.epelde.crispychainsaw.databinding.ItemMenuBinding;
import io.github.epelde.crispychainsaw.databinding.MenuHeaderBinding;
import io.github.epelde.crispychainsaw.model.domain.Header;
import io.github.epelde.crispychainsaw.model.domain.MenuDrawerItem;
import io.github.epelde.crispychainsaw.viewmodel.MenuHeaderViewModel;
import io.github.epelde.crispychainsaw.viewmodel.MenuItemViewModel;

/**
 * Created by Gorka on 27/04/2016.
 */
public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements MenuHeaderViewModel.MenuHeaderViewModelListener,MenuItemViewModel.MenuItemViewModelListener{

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    public List<MenuDrawerItem> menuList;
    public Header header;
    public MenuRecyclerViewListener mMenuRecyclerViewListener;

    public MenuRecyclerViewAdapter (Header header,List<MenuDrawerItem> menuList,MenuRecyclerViewListener mMenuRecyclerViewListener){
        this.menuList = menuList;
        this.header = header;
        this.mMenuRecyclerViewListener = mMenuRecyclerViewListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            ItemMenuBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.item_menu,parent,false);
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
            hvh.binding.setHeaderMenuVM(new MenuHeaderViewModel(this));
            hvh.binding.avatar.setImageResource(header.getProfile());
            hvh.binding.email.setText(header.getEmail());
        } else {
            MenuItemViewHolder mivh = (MenuItemViewHolder)holder;
            mivh.binding.setMenuItemVM(new MenuItemViewModel(this));
            mivh.binding.rowIcon.setImageResource(menuList.get(position-1).getImageResource());
            mivh.binding.rowText.setText(menuList.get(position-1).getTitle());
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

    }
}
