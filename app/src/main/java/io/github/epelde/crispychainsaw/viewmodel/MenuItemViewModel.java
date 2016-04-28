package io.github.epelde.crispychainsaw.viewmodel;

/**
 * Created by Gorka on 27/04/2016.
 */
public class MenuItemViewModel {
    private MenuItemViewModelListener mMenuItemViewModelListener;

    public MenuItemViewModel(MenuItemViewModelListener mMenuItemViewModelListener) {
        this.mMenuItemViewModelListener = mMenuItemViewModelListener;
    }

    public interface MenuItemViewModelListener {
    }
}
