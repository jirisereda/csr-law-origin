package cz.cas.ilaw.csrlaworigin.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import cz.cas.ilaw.csrlaworigin.R;


/**
 * Activity displaying a single content pane, which is a Fragment returned by {@link #onCreatePane()}.
 *
 * @author Jiri Sereda
 */
public abstract class SinglePaneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());

        setupActionBar();

        if (savedInstanceState == null) {
            Fragment fragment = onCreatePane();
            addIntentExtrasToFragmentArgs(fragment);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.single_pane_content, fragment);
            transaction.commit();
        }
    }

    /**
     * Adds {@link Intent}'s extras to {@code fragment}'s arguments if requested.
     *
     * @param fragment The fragment which args to modify.
     */
    private void addIntentExtrasToFragmentArgs(Fragment fragment) {
        if (shouldAddIntentExtrasToFragmentArgs()) {
            Bundle args = new Bundle();
            if (fragment.getArguments() != null) {
                args.putAll(fragment.getArguments());
            }
            if(getIntent().getExtras() != null) {
                args.putAll(getIntent().getExtras());
            }
            fragment.setArguments(args);
        }
    }

    /**
     * Dynamically sets the Action Bar. Child activity should override this method to customize the ActionBar.
     */
    protected void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Gets the layout for the activity. The layout must contain {@link R.id#single_pane_content} that the fragment will
     * be placed into.
     *
     * @return The layout id.
     */
    protected int getContentViewLayout() {
        return R.layout.activity_single_pane;
    }

    /**
     * Called to create the pane content of the Activity.
     *
     * @return The fragment to be displayed as the content of the Activity.
     */
    abstract protected Fragment onCreatePane();

    /**
     * Called to check whether or not to add {@link Intent}'s extras to {@link Fragment}'s (returned from {@link
     * #onCreatePane()}) arguments.
     *
     * By default returns false to not interfere with already present arguments.
     *
     * @return True to add the {@link Intent}'s extras to the {@link Fragment}'s arguments.
     */
    protected boolean shouldAddIntentExtrasToFragmentArgs() {
        return false;
    }
}

