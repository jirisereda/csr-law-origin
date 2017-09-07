package cz.cas.ilaw.csrlaworigin.topiceditor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;

import java.util.ArrayList;
import java.util.List;

import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.base.BaseActivity;
import cz.cas.ilaw.csrlaworigin.topiceditor.relatedtopic.ChooseTopicFragment;

/**
 * @author Jiri Sereda.
 */
public class EntityActivity extends BaseActivity {

    public List<String> items = new ArrayList<>();
    public String title;

    /**
     * Starts the activity.
     *
     * @param context The context to start from.
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, EntityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_single_pane);

        setupActionBar();

        if (savedInstanceState == null) {
            EntityFragment fragment = new EntityFragment();
            fragment.setArguments(EntityFragment.createArguments());
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.single_pane_content, fragment, "TAG_CREATE_RECORD");
            transaction.commit();
        }
    }

    private void setupActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            CancelEditDialogFragment.show(transaction);
        }
    }

    public void doPositiveClick() {
        finish();
    }

    public void openListOfTopics() {
        ChooseTopicFragment chooseTopicFragment = new ChooseTopicFragment();
        chooseTopicFragment.setArguments(ChooseTopicFragment.createArguments());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.single_pane_content, chooseTopicFragment, "TAG_CHOOSE_ITEM");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void openItemCreator() {
        getSupportFragmentManager().popBackStack();
    }

}