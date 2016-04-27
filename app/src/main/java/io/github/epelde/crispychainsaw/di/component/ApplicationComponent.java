package io.github.epelde.crispychainsaw.di.component;

import javax.inject.Singleton;

import dagger.Component;
import io.github.epelde.crispychainsaw.di.module.ApplicationModule;
import io.github.epelde.crispychainsaw.viewmodel.BandListViewModel;

import io.github.epelde.crispychainsaw.di.module.NetworkModule;

/**
 * Created by epelde on 26/04/2016.
 */
@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {
    void inject(BandListViewModel vm);
}