package io.github.epelde.crispychainsaw.model.data;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import io.github.epelde.crispychainsaw.model.domain.Band;

/**
 * Created by epelde on 20/04/2016.
 */
public class DataManagerImpl implements DataManager {

    private static final String[] BAND_NAMES = {"Acid Drinkers", "Anacrusis", "Blood Feast",
            "Cerebral Fix", "Corrosion of Conformity", "Death Angel",
            "Demolition Hammer", "Gama Bomb", "Gwar", "Havolk"};

    private static final String BAND_IMAGE_URL = "https://source.unsplash.com/450x350/?music,band,concert";

    private static List<Band> bands = new ArrayList<Band>();

    static {
        int i = 0;
        for (String name : BAND_NAMES) {
            bands.add(new Band(Integer.toString(i), name, BAND_IMAGE_URL));
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
}
