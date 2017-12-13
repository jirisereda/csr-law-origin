package cz.cas.ilaw.csrlaworigin.Settings;

import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Jiri Sereda.
 */
@Singleton
public class DefaultSettings implements Settings {

    private final static String KEY_MOCKED = "mocked";

    /**
     * Shared preferences instance used to load/store data.
     */
    private final SharedPreferences mSharedPreferences;

    @Inject
    public DefaultSettings(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    public boolean isMocked() {
        return mSharedPreferences.getBoolean(KEY_MOCKED, false);
    }

    @Override
    public void setMocked(boolean mocked) {
        mSharedPreferences.edit().putBoolean(KEY_MOCKED, mocked).apply();
    }
}