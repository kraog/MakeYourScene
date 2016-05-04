package kraog.moveyourscene.model.data;

import android.support.annotation.NonNull;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;


import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Disc;


/**
 * Created by epelde on 20/04/2016.
 */
public class DataManagerImpl implements DataManager {

    private static final String[] BAND_NAMES = {"Faith no more", "Deftones", "Akhvan",
            "Carnivorous Voracity", "Nostromo", "Camela",
            "Entombed", "Helloween", "Breach", "Knut"};

    private static final String[] BAND_PHOTOS = {
            "https://i.ytimg.com/vi/f2HP_d_MVH4/maxresdefault.jpg",
            "http://noisecreep.com/files/2010/05/deftones-400-2-gdp-52710.jpg",
            "http://poprocklaredo.webcindario.com/grupos/akhvan1.jpg",
            "http://2.bp.blogspot.com/-zCWR7QaBkrw/VdWPjlKakgI/AAAAAAAAaWU/BeqGy_O9eKA/s640/CV.jpg",
            "http://metalchoc.chez-alice.fr/nostromo_band.jpg",
            "http://soundline.es/wp-content/uploads/2012/09/CAMELA-PRUEBA-1-copia1.jpg",
            "http://www.metal-archives.com/images/7/7_photo.jpg?3659",
            "https://www.gp32spain.com/foros/cache.php?img=http%3A%2F%2Fcdn-ak.f.st-hatena.com%2Fimages%2Ffotolife%2Fl%2Flastseen1013%2F20070910%2F20070910171701.jpg",
            "http://www.cvltnation.com/wp-content/uploads/2012/01/Breach-1-e1326412154486.jpeg",
            "http://www.metal-archives.com/images/3/4/9/2/3492_photo.jpg"};

    private static final String[] DISC_NAMES = {"Izeshne 33.4", "Adrenaline", "King for a day, fool for a lifetime",
            "Terraformer", "keeper of the seven keys I", "It's me god",
            "Debasement encarnated", "Wolverine blues", "Escuchame, comprendelo", "Venom"};

    private static final String BAND_IMAGE_URL = "http://poprocklaredo.webcindario.com/grupos/akhvan1.jpg";
    private static final String DISC_IMAGE_URL = "http://poprocklaredo.webcindario.com/grupos/akhvan1.jpg";

    private static List<Band> bands = new ArrayList<Band>();
    private static List<Disc> discs = new ArrayList<Disc>();

    static {
        int i = 0;
        for (String name : BAND_NAMES) {
            bands.add(new Band(i, name, BAND_PHOTOS[i]));
            ++i;
        }
        int ii = 0;
        for (String name : DISC_NAMES) {
            discs.add(new Disc(ii, name, BAND_PHOTOS[ii]));
            ++ii;
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
      /*  Predicate<Band> pred = new Predicate<Band>() {
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
        return Lists.newArrayList(Collections2.filter(bands,null));*/
        return bands;
    }

    @Override
    public List<Disc> getDiscs() {
        return null;
    }

    @Override
    public List<Disc> getDiscs(@NonNull Disc filter) {
        return null;
    }
}
