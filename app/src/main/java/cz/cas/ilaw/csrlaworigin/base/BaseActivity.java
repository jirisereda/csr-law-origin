package cz.cas.ilaw.csrlaworigin.base;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cz.cas.ilaw.csrlaworigin.MyApplication;

/**
 * Base class for all Activities.
 *
 * Subclasses can override {@link #injectSelf()} method to inject self.
 *
 * @author Jiri Sereda.
 */
abstract public class BaseActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    /**
     * Subclasses can override this method to inject self.
     */
    protected void injectSelf() {
        MyApplication.get(this).getComponent().injectBaseActivity(this);
    }

    private final LifecycleRegistry mRegistry = new LifecycleRegistry(this);

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectSelf();
    }
}

