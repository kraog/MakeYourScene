package kraog.moveyourscene.model.data;

import android.support.annotation.NonNull;

import java.util.List;

import kraog.moveyourscene.model.domain.Band;
import kraog.moveyourscene.model.domain.Concert;
import kraog.moveyourscene.model.domain.Disc;


/**
 * Created by epelde on 20/04/2016.
 */
public interface DataManager {

    public List<Band> getBands();

    public List<Band> getBands(@NonNull final Band filter);

    public List<Disc> getDiscs();

    public List<Disc> getDiscs(@NonNull final Disc filter);

    public List<Concert> getConcerts();

    public List<Concert> getConcerts(@NonNull final Concert filter);

    public Band getBand(@NonNull String id);
}
