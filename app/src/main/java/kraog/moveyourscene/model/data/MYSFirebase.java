package kraog.moveyourscene.model.data;

import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import kraog.moveyourscene.model.domain.Band;
import rx.Observable;
import rx.Observer;

/**
 * Created by Gorka on 10/05/2016.
 */
public class MYSFirebase {

    public static final String MYS_ROOT = "MYS";
    public static final String NODE_BANDS = "bands";
    public static final String NODE_DISCS = "discs";
    public static final String NODE_CONCERTS = "concerts";
    public DataManagerImpl dmi = new DataManagerImpl();

    public Firebase fb;
    static List<Band> bandList = new ArrayList<Band>();

    public MYSFirebase(){
        this.fb = new Firebase("https://moveyourscene.firebaseio.com/");
    }

    public void setStupidDataFirebase(){

        fb.child(MYS_ROOT).child(NODE_CONCERTS).setValue(dmi.getMapConcerts());
    }
    public Observable<DataSnapshot> getBandsSnapShot(){
        return RxFirebase.observe(fb.child(MYS_ROOT).child(NODE_BANDS));
    }
    public Observable<DataSnapshot> getDiscsSnapShot(){

        return RxFirebase.observe(fb.child(MYS_ROOT).child(NODE_DISCS));
    }
    public Observable<DataSnapshot> getConcertsSnapShot(){

        return RxFirebase.observe(fb.child(MYS_ROOT).child(NODE_CONCERTS));
    }
}
