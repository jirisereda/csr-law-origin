package cz.cas.ilaw.csrlaworigin.topiceditor.relatedtopic;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.base.BaseToolbarFragment;
import cz.cas.ilaw.csrlaworigin.db.RecordTopic;
import cz.cas.ilaw.csrlaworigin.viewmodel.TopicEditorViewModel;
import cz.cas.ilaw.csrlaworigin.topiceditor.EntityActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class ChooseTopicFragment extends BaseToolbarFragment implements TopicsAdapterSelect.SelectItemListener {

    @BindView(R.id.list_record_topics)
    ListView mRecordTopics;

    private TopicEditorViewModel mTopicEditorViewModel;
    private TopicsAdapterSelect mTopicsAdapterSelect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mTopicEditorViewModel = ViewModelProviders.of(getActivity()).get(TopicEditorViewModel.class);

        return inflater.inflate(R.layout.fragment_choose_item, container, false);
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
                mTopicsAdapterSelect.setData(recordTopics);
            }
        });

    }

    private void initListView() {
        mTopicsAdapterSelect = new TopicsAdapterSelect(getContext(), this);
        mRecordTopics.setAdapter(mTopicsAdapterSelect);
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
                mTopicsAdapterSelect.filter(s);
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
    public static Bundle createArguments() {
        Bundle arguments = new Bundle();
        arguments.putBoolean(BaseToolbarFragment.ARG_DISPLAY_HOME_AS_UP, false);
        return arguments;
    }

    private void initListeners() {
    }

    @Override
    public void onItemSelected(String item) {
        Log.d("ChooseTopicFragment", "Selected topic: " + item);
        EntityActivity entityActivity = (EntityActivity) getActivity();
        entityActivity.items.add(item);
        entityActivity.openItemCreator();
    }
}
