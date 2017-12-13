package cz.cas.ilaw.csrlaworigin;

import android.arch.lifecycle.ViewModel;

import android.arch.lifecycle.ViewModelProvider;
import cz.cas.ilaw.csrlaworigin.topicsscreen.TopicsViewModel;
import cz.cas.ilaw.csrlaworigin.viewmodel.StepsViewModelFactory;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TopicsViewModel.class)
    abstract ViewModel bindTopicsViewModel(TopicsViewModel topicsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(StepsViewModelFactory factory);
}
