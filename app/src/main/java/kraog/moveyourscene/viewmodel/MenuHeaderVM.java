package kraog.moveyourscene.viewmodel;


import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import kraog.moveyourscene.model.domain.User;

public class MenuHeaderVM {
    public ObservableField<User> user;

    private MenuHeaderViewModelListener mMenuHeaderViewModelListener;

    public MenuHeaderVM(ObservableField<User> user, MenuHeaderViewModelListener mMenuHeaderViewModelListener) {
        this.user = new ObservableField<User>();
        this.setUser(user);
        this.mMenuHeaderViewModelListener = mMenuHeaderViewModelListener;
    }

    @BindingAdapter({"bind:menuItemImage"})
    public static void setMenuItemImage(ImageView v, String url){
        Picasso.with(v.getContext()).load(url).fit().centerCrop().into(v);
    }

    public ObservableField<User> getUser() {
        return user;
    }

    public void setUser(ObservableField<User> user) {
        this.user = user;
    }


    public interface MenuHeaderViewModelListener {
    }
}
