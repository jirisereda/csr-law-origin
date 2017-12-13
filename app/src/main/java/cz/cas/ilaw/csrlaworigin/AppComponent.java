package cz.cas.ilaw.csrlaworigin;

import cz.cas.ilaw.csrlaworigin.Settings.SettingsModule;
import cz.cas.ilaw.csrlaworigin.db.DatabaseModule;
import cz.cas.ilaw.csrlaworigin.loginscreen.IntroActivityFragment;
import cz.cas.ilaw.csrlaworigin.network.NetworkModule;
import cz.cas.ilaw.csrlaworigin.topicsscreen.TopicsActivity;
import cz.cas.ilaw.csrlaworigin.topicsscreen.TopicsActivityFragment;
import javax.inject.Singleton;

import cz.cas.ilaw.csrlaworigin.base.BaseActivity;
import cz.cas.ilaw.csrlaworigin.base.BaseFragment;
import dagger.Component;

/**
 * @author Jiri Sereda
 */
@Singleton @Component(modules = {
    AppModule.class,
    SettingsModule.class,
    NetworkModule.class,
    DatabaseModule.class,
    ViewModelModule.class
})

public interface AppComponent {

  // Base
  void injectBaseActivity(BaseActivity activity);
  void injectBaseFragment(BaseFragment fragment);

  void injectTopicsActivityFragment(TopicsActivityFragment topicsActivityFragment);
  void injectIntroActivityFragment(IntroActivityFragment introActivityFragment);

  void injectTopicsActivity(TopicsActivity topicsActivity);


}

