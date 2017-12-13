package cz.cas.ilaw.csrlaworigin.loginscreen;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import cz.cas.ilaw.csrlaworigin.base.SinglePaneActivity;
import cz.cas.ilaw.csrlaworigin.mainscreen.MainActivityFragment;

public class IntroActivity extends SinglePaneActivity {

    /**
     * Starts the activity.
     *
     * @param context The context to start from.
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, IntroActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected Fragment onCreatePane() {
        IntroActivityFragment introActivityFragment = new IntroActivityFragment();
        introActivityFragment.setArguments(IntroActivityFragment.createArguments());
        return introActivityFragment;
    }
}
