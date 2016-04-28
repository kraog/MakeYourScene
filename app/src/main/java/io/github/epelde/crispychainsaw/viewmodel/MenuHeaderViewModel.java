package io.github.epelde.crispychainsaw.viewmodel;


public class MenuHeaderViewModel {

    private MenuHeaderViewModelListener mMenuHeaderViewModelListener;

    public MenuHeaderViewModel(MenuHeaderViewModelListener mMenuHeaderViewModelListener) {
        this.mMenuHeaderViewModelListener = mMenuHeaderViewModelListener;
    }

    public interface MenuHeaderViewModelListener {
    }
}
