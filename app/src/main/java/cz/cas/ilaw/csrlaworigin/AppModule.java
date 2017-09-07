package cz.cas.ilaw.csrlaworigin;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * @author Jiri Sereda
 */
@Module
public class AppModule {
    private final MyApplication mApplication;

    public AppModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    public MyApplication provideApplication() {
        return mApplication;
    }

    @Provides
    Context provideApplicationContext() {
        return mApplication;
    }
}

