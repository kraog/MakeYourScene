package kraog.moveyourscene.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kraog.moveyourscene.R;
import kraog.moveyourscene.databinding.FrontActivityBinding;
import kraog.moveyourscene.model.domain.MenuDrawerItem;
import kraog.moveyourscene.viewmodel.FrontVM;

public class FrontActivity extends AppCompatActivity implements FrontVM.FrontVMListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrontActivityBinding binding = DataBindingUtil.setContentView(this,R.layout.front_activity);
        binding.setFrontVM(new FrontVM(this));
    }

    @Override
    public void onOptionClicked(MenuDrawerItem.Activity_Related relatedAct) {

        switch (relatedAct){
            case BAND_LIST:BandListActivity.navigate(this,null,null);break;
            case DISC_LIST:DiscListActivity.navigate(this,null,null);break;
            //case CONCERT_LIST:initDrawer();break;
        }
    }
}
