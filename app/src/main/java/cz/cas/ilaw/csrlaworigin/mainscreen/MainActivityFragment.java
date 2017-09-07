package cz.cas.ilaw.csrlaworigin.mainscreen;

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
import android.widget.ListView;
import android.widget.SearchView;

import com.github.clans.fab.FloatingActionButton;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.base.BaseToolbarFragment;
import cz.cas.ilaw.csrlaworigin.db.RecordTopic;
import cz.cas.ilaw.csrlaworigin.topiceditor.EntityActivity;
import cz.cas.ilaw.csrlaworigin.viewmodel.TopicEditorViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends BaseToolbarFragment {

//    @BindView(R.id.add_floating_action_menu)
//    FloatingActionMenu mFloatingActionMenu;

//    @BindView(R.id.add_location)
//    FloatingActionButton mFloatingActionLocation;

    @BindView(R.id.add_item)
    FloatingActionButton mFloatingActionItem;

    @BindView(R.id.list_record_topics)
    ListView mRecordTopics;


    private TopicEditorViewModel mTopicEditorViewModel;
    private TopicsAdapter mTopicsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTopicEditorViewModel = ViewModelProviders.of(getActivity()).get(TopicEditorViewModel.class);

        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        ButterKnife.bind(this, view);

        initListView();

        initListeners();


        subscribeToDbChanges();
    }


    private void subscribeToDbChanges() {

        mTopicEditorViewModel.getBooks().observe((LifecycleOwner) getActivity(), new Observer<List<RecordTopic>>() {

            @Override
            public void onChanged(@Nullable List<RecordTopic> recordTopics) {

                Collections.sort(recordTopics);
                mTopicsAdapter.setData(recordTopics);
            }
        });

    }

    private void initListView() {
        mTopicsAdapter = new TopicsAdapter(getContext());
        mRecordTopics.setAdapter(mTopicsAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.menu_topics, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        final MenuItem searchItem = menu.findItem(R.id.menu_search);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mTopicsAdapter.filter(s);
                return true;
            }
        });

//        SearchView searchView = (SearchView) MenuItemCompat.getA

    }

    @Nullable
    @Override
    protected String getTitle() {
        return "Hesla";
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

        mFloatingActionItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EntityActivity.startActivity(getActivity());
            }
        });
    }

}
