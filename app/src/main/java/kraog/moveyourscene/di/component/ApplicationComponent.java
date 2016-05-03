package kraog.moveyourscene.di.component;

import javax.inject.Singleton;

import dagger.Component;

import kraog.moveyourscene.di.module.ApplicationModule;
import kraog.moveyourscene.di.module.NetworkModule;
import kraog.moveyourscene.viewmodel.MYSViewModel;

/**
 * Created by epelde on 26/04/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(MYSViewModel vm);
}