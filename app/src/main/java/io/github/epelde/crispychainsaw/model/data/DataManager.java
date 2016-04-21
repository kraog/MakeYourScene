package io.github.epelde.crispychainsaw.model.data;

import android.support.annotation.NonNull;

import java.util.List;

import io.github.epelde.crispychainsaw.model.domain.Band;

/**
 * Created by epelde on 20/04/2016.
 */
public interface DataManager {

    public List<Band> getBands();

    public Band getBand(@NonNull String id);
}
