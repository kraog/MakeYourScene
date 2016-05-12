package kraog.moveyourscene.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

import kraog.moveyourscene.model.data.MYSFirebase;
import kraog.moveyourscene.model.domain.MenuDrawerItem;

/**
 * Created by Gorka on 04/05/2016.
 */
public class FrontVM {
    public MYSFirebase mysfb;

    public static FrontVMListener mFrontVMListener;
    public ObservableField<MenuDrawerItem.Activity_Related> bandAct;
    public ObservableField<MenuDrawerItem.Activity_Related> discAct;
    public ObservableField<MenuDrawerItem.Activity_Related> concertAct;

    public FrontVM(FrontVMListener mFrontVMListener){
        this.mFrontVMListener=mFrontVMListener;
        bandAct = new ObservableField<MenuDrawerItem.Activity_Related>();
        bandAct.set(MenuDrawerItem.Activity_Related.BAND_LIST);
        discAct = new ObservableField<MenuDrawerItem.Activity_Related>();
        discAct.set(MenuDrawerItem.Activity_Related.DISC_LIST);
        concertAct = new ObservableField<MenuDrawerItem.Activity_Related>();
        concertAct.set(MenuDrawerItem.Activity_Related.CONCERT_LIST);

    }

    @BindingAdapter({"bind:relatedFrontAct"})
    public static void setOnClickListener(View v, final MenuDrawerItem.Activity_Related relatedAct) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFrontVMListener.onOptionClicked(relatedAct);
            }
        });
    }
    public interface FrontVMListener{
        public void onOptionClicked(MenuDrawerItem.Activity_Related relatedAct);
    }
}
