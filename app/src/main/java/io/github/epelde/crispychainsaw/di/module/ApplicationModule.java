package io.github.epelde.crispychainsaw.di.module;

import dagger.Module;
import dagger.Provides;
import io.github.epelde.crispychainsaw.model.data.DataManager;
import io.github.epelde.crispychainsaw.model.data.DataManagerImpl;

/**
 * Created by epelde on 26/04/2016.
 */
@Module
public class ApplicationModule {
    @Provides
    DataManager providesDataManager() {
        return new DataManagerImpl();
    }
}
