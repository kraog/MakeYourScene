package kraog.moveyourscene.di.component;

import javax.inject.Singleton;

import dagger.Component;

import kraog.moveyourscene.di.module.ApplicationModule;
import kraog.moveyourscene.viewmodel.MYSListVM;

/**
 * Created by epelde on 26/04/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MYSListVM vm);
}