package cz.cas.ilaw.csrlaworigin;

import javax.inject.Singleton;

import cz.cas.ilaw.csrlaworigin.base.BaseActivity;
import cz.cas.ilaw.csrlaworigin.base.BaseFragment;
import dagger.Component;

/**
 * @author Jiri Sereda
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
        }
)

public interface AppComponent {

    // Base
    void injectBaseActivity(BaseActivity activity);
    void injectBaseFragment(BaseFragment fragment);
}

