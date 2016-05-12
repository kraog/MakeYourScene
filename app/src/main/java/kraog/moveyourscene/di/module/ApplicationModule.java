package kraog.moveyourscene.di.module;

import dagger.Module;
import dagger.Provides;
import kraog.moveyourscene.model.data.DataManager;
import kraog.moveyourscene.model.data.DataManagerImpl;
import kraog.moveyourscene.model.data.MYSFirebase;

/**
 * Created by epelde on 26/04/2016.
 */
@Module
public class ApplicationModule {
    @Provides
    DataManager providesDataManager() {
        return new DataManagerImpl();
    }
    @Provides
    MYSFirebase providesMYSFirebase(){return new MYSFirebase();}
}
