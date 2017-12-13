package cz.cas.ilaw.csrlaworigin.loginscreen;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.github.clans.fab.FloatingActionButton;
import cz.cas.ilaw.csrlaworigin.MyApplication;
import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.Settings.Settings;
import cz.cas.ilaw.csrlaworigin.base.BaseToolbarFragment;
import cz.cas.ilaw.csrlaworigin.db.RecordTopic;
import cz.cas.ilaw.csrlaworigin.mainscreen.TopicsAdapter;
import cz.cas.ilaw.csrlaworigin.topiceditor.EntityActivity;
import cz.cas.ilaw.csrlaworigin.topicsscreen.TopicsActivity;
import cz.cas.ilaw.csrlaworigin.viewmodel.TopicEditorViewModel;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class IntroActivityFragment extends BaseToolbarFragment {

    @BindView(R.id.button)
    Button mButton;

    @BindView(R.id.buttonFakeData)
    Button mButtonFakeData;

    @Inject
    Settings mSettings;

    @Override
    protected void injectSelf() {
        MyApplication.get(getActivity()).getComponent().injectIntroActivityFragment(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_intro, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setHasOptionsMenu(true);

        ButterKnife.bind(this, view);


        initListeners();


    }


    //private void subscribeToDbChanges() {
    //
    //    mTopicEditorViewModel.getBooks().observe((LifecycleOwner) getActivity(), new Observer<List<RecordTopic>>() {
    //
    //        @Override
    //        public void onChanged(@Nullable List<RecordTopic> recordTopics) {
    //
    //            Collections.sort(recordTopics);
    //            mTopicsAdapter.setData(recordTopics);
    //        }
    //    });
    //
    //}
    //
    //private void initListView() {
    //    mTopicsAdapter = new TopicsAdapter(getContext());
    //    mRecordTopics.setAdapter(mTopicsAdapter);
    //}

    //@Override
    //public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    //
    //    inflater.inflate(R.menu.menu_topics, menu);
    //}
    //
    //@Override
    //public void onPrepareOptionsMenu(Menu menu) {
    //    final MenuItem searchItem = menu.findItem(R.id.menu_search);
    //
    //    SearchView searchView = (SearchView) searchItem.getActionView();
    //
    //    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    //        @Override
    //        public boolean onQueryTextSubmit(String s) {
    //            return false;
    //        }
    //
    //        @Override
    //        public boolean onQueryTextChange(String s) {
    //            mTopicsAdapter.filter(s);
    //            return true;
    //        }
    //    });

//        SearchView searchView = (SearchView) MenuItemCompat.getA

    //}

    @Nullable
    @Override
    protected String getTitle() {
        return "Intro";
    }

    /**
     * Creates the default fragment's arguments.
     *
     * @return The arguments bundle.
     */
    static Bundle createArguments() {
        Bundle arguments = new Bundle();
        arguments.putBoolean(BaseToolbarFragment.ARG_DISPLAY_HOME_AS_UP, false);
        return arguments;
    }

    private void initListeners() {
//        mFloatingActionLocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//            }
//        });

        //mFloatingActionItem.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        EntityActivity.startActivity(getActivity());
        //    }
        //});

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                mSettings.setMocked(false);
                TopicsActivity.startActivity(getActivity());
            }
        });

        mButtonFakeData.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                mSettings.setMocked(true);
                TopicsActivity.startActivity(getActivity());
            }
        });

    }

}
