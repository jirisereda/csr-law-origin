package cz.cas.ilaw.csrlaworigin;

import android.app.Application;
import android.content.Context;

/**
 * @author Jiri Sereda
 */

public class MyApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initDependencyInjection();
    }

    public AppComponent getComponent() {
        return mAppComponent;
    }

    private void initDependencyInjection() {
        mAppComponent = createAppComponent();
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    /**
     * Gets the application instance.
     *
     * @param context The context.
     * @return The application instance.
     */
    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

}
