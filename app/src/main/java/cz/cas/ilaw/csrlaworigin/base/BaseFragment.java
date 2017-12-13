package cz.cas.ilaw.csrlaworigin.base;

import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import cz.cas.ilaw.csrlaworigin.MyApplication;

/**
 * Base class for all Fragments.
 *
 * Subclasses can override {@link #injectSelf()} method to inject self.
 *
 * @author Jiri Sereda
 */
abstract public class BaseFragment extends LifecycleFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        injectSelf();
        super.onCreate(savedInstanceState);
    }

    /**
     * Subclasses can override this method to inject self.
     */
    protected void injectSelf() {
        MyApplication.get(getActivity()).getComponent().injectBaseFragment(this);
    }


}

