package cz.cas.ilaw.csrlaworigin.Settings;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * @author Jiri Sereda.
 */
@Module
public class SettingsModule {

    /**
     * Name of the shared preferences file.
     */
    public static final String SHARED_PREFERENCES_FILE = "stopy";

    @Provides
    @Singleton
    public Settings provideSettings(DefaultSettings settings) {
        return settings;
    }

    @Provides
    @Singleton SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE, Context.MODE_PRIVATE);
    }
}