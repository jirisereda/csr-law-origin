package cz.cas.ilaw.csrlaworigin.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


import butterknife.ButterKnife;
import cz.cas.ilaw.csrlaworigin.R;

/**
 * Base class for fragments with a {@link Toolbar} that must be part of its layout with id {@link R
 * .id#base_fragment_toolbar}.
 *
 * @author Jiri Sereda
 */
abstract public class BaseToolbarFragment extends BaseFragment {
    /**
     * Boolean argument to enable displaying 'home as up' in the Toolbar.
     */
    public static final String ARG_DISPLAY_HOME_AS_UP = "display_home_as_up";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setupToolbar();
    }

    /**
     * Sets up the Toolbar.
     */
    private void setupToolbar() {
        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

            Bundle arguments = getArguments();
            boolean displayHomeAsUp = arguments == null || arguments.getBoolean(ARG_DISPLAY_HOME_AS_UP, true);
            setSupportActionBarDisplayHomeAsUpEnabled(displayHomeAsUp);

            setupToolbarTitle();
        }
    }

    /**
     * Sets the 'display home as up' on the Activity's support ActionBar (which is in fact our Toolbar).
     *
     * @param enabled True to enable, false to disable.
     */
    protected void setSupportActionBarDisplayHomeAsUpEnabled(boolean enabled) {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(enabled);

            Toolbar toolbar = getToolbar();
            toolbar.setContentInsetsRelative(enabled ? 0 : getResources().getDimensionPixelSize(
                    R.dimen.margin_horizontal), toolbar.getContentInsetEnd());
        }
    }

    /**
     * Sets up the Toolbar title.
     */
    private void setupToolbarTitle() {
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            String title = getTitle();
            if (title != null) {
                actionBar.setTitle(title);
            }
        }
    }

    /**
     * Gets the toolbar.
     *
     * @return The toolbar or null if there isn't any.
     */
    @Nullable
    protected Toolbar getToolbar() {
        final View view = getView();
        if (view != null) {
            return ButterKnife.findById(view, R.id.base_fragment_toolbar);
        }
        return null;
    }

    /**
     * Gets the title to put in the toolbar.
     *
     * @return The title or null to use the activity label.
     */
    @Nullable
    abstract protected String getTitle();
}
