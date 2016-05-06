package kraog.moveyourscene.viewmodel;

import android.support.design.widget.TabLayout;
import android.widget.LinearLayout;

import javax.inject.Inject;

import kraog.moveyourscene.model.data.DataManager;

/**
 * Created by Gorka on 02/05/2016.
 */
public class MYSListVM {
    @Inject
    public DataManager dm;
    public static MYSListVMInterface mMYSListVMInterface;


    public MYSListVM(MYSListVMInterface mMYSListVMInterface){
        this.mMYSListVMInterface = mMYSListVMInterface;
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

            }
        };
    }

    public interface MYSListVMInterface{
        void onTabSelectedStart(TabLayout.Tab tab);
    }

}
