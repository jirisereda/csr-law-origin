package cz.cas.ilaw.csrlaworigin.topiceditor;

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
import android.widget.EditText;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.base.BaseToolbarFragment;
import cz.cas.ilaw.csrlaworigin.viewmodel.TopicEditorViewModel;

/**
 * @author Jiri Sereda
 */

public class EntityFragment extends BaseToolbarFragment {

    @BindView(R.id.heslo)
    EditText mHeslo;

    @BindView(R.id.popisek)
    EditText mPopis;

    @BindView(R.id.add_item_btm)
    Button mAddRelatedItem;

    @BindView(R.id.items)
    ListView mRelatedItems;

    private TopicEditorViewModel mTopicEditorViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        mTopicEditorViewModel = ViewModelProviders.of(getActivity()).get(TopicEditorViewModel.class);

        return inflater.inflate(R.layout.fragment_entity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initListeners();

        initViews();
    }

    private void initViews() {

        EntityActivity entityActivity = (EntityActivity) getActivity();

        mHeslo.setText(entityActivity.title);

        RelatedItemsAdapter relatedItemsAdapter = new RelatedItemsAdapter(getContext());
        relatedItemsAdapter.setData(entityActivity.items);
        mRelatedItems.setAdapter(relatedItemsAdapter);
    }

    private void initListeners() {
        mAddRelatedItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (!isAdded()) {
                   return;
               }

               EntityActivity entityActivity = (EntityActivity) getActivity();
               entityActivity.title =  mHeslo.getText().toString();
               entityActivity.openListOfTopics();
            }
        });
    }

    /**
     * Creates the default fragment's arguments.
     *
     * @return The arguments bundle.
     */
    static Bundle createArguments() {
        Bundle arguments = new Bundle();
        arguments.putBoolean(BaseToolbarFragment.ARG_DISPLAY_HOME_AS_UP, true);
        return arguments;
    }

    @Nullable
    @Override
    protected String getTitle() {
        return getString(R.string.entity_header);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        final MenuItem saveMenu = menu.findItem(R.id.menu_save);
        saveMenu.getActionView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(saveMenu);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
            return true;
        }

        if (item.getItemId() == R.id.menu_save) {
            mTopicEditorViewModel.saveTopic(mHeslo.getText().toString(), mPopis.getText().toString());
            getActivity().finish();
            return true;
        }

        return false;
    }
}