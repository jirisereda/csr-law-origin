package cz.cas.ilaw.csrlaworigin.mainscreen;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import cz.cas.ilaw.csrlaworigin.base.SinglePaneActivity;

public class MainActivity extends SinglePaneActivity {

    /**
     * Starts the activity.
     *
     * @param context The context to start from.
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected Fragment onCreatePane() {
        MainActivityFragment mainActivityFragment = new MainActivityFragment();
        mainActivityFragment.setArguments(MainActivityFragment.createArguments());
        return mainActivityFragment;
    }
}
