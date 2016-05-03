package kraog.moveyourscene.model.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import kraog.moveyourscene.model.domain.Band;


/**
 * Created by epelde on 20/04/2016.
 */
public class DataManagerImpl implements DataManager {

    private static final String[] BAND_NAMES = {"Acid Drinkers", "Anacrusis", "Blood Feast",
            "Cerebral Fix", "Corrosion of Conformity", "Death Angel",
            "Demolition Hammer", "Gama Bomb", "Gwar", "Havolk"};

    private static final String BAND_IMAGE_URL = "http://poprocklaredo.webcindario.com/grupos/akhvan1.jpg";

    private static List<Band> bands = new ArrayList<Band>();

    static {
        int i = 0;
        for (String name : BAND_NAMES) {
            bands.add(new Band(i, name, BAND_IMAGE_URL));
            ++i;
        }
    }

    @Override
    public List<Band> getBands() {
        return bands;
    }

    @Override
    public Band getBand(@NonNull String id) {
        return bands.get(Integer.valueOf(id));
    }

    @Override
    public List<Band> getBands(final Band filter)
    {
        Predicate<Band> pred = new Predicate<Band>() {
            @Override
            public boolean apply(Band band) {
                boolean matched = true;
                if(matched && filter.getName()!=null){
                    matched = band.getName().equals(filter.getName());
                }
                if(matched && filter.getId_country()>0){
                    matched = (band.getId_country()==filter.getId_country());
                }
                if(matched && filter.getId_local()>0){
                    matched = (band.getId_local()==filter.getId_local());
                }
                return matched;
            }
        };
        pred.apply(filter);
        return Lists.newArrayList(Collections2.filter(bands,pred));
    }
}
