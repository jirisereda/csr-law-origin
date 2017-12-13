package cz.cas.ilaw.csrlaworigin.topicsscreen;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import cz.cas.ilaw.csrlaworigin.MyApplication;
import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.Settings.Settings;
import cz.cas.ilaw.csrlaworigin.base.BaseToolbarFragment;
import cz.cas.ilaw.csrlaworigin.db.User;
import java.util.List;
import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class TopicsActivityFragment extends BaseToolbarFragment {

//    @BindView(R.id.add_floating_action_menu)
//    FloatingActionMenu mFloatingActionMenu;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    //@BindView(R.id.button2)
    //Button mLoadButton;

    //@BindView(R.id.add_item)
    //FloatingActionButton mFloatingActionItem;
    //

    //@BindView(R.id.textView)
    //TextView mTextView;


    //@Inject
    //Settings mSettings;


    private TopicsViewModel mTopicsViewModel;
    //private TopicsAdapter mTopicsAdapter;


    @Override
    protected void injectSelf() {
        MyApplication.get(getActivity()).getComponent().injectTopicsActivityFragment(this);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTopicsViewModel = ViewModelProviders.of(getActivity(),viewModelFactory).get(TopicsViewModel.class);
        mTopicsViewModel.getUser().observe(this, new Observer<List<User>>() {
            @Override public void onChanged(@Nullable List<User> users) {
                showUsers(users);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_topics, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        //mLoadButton.setOnClickListener(new View.OnClickListener() {
        //    @Override public void onClick(View view) {
        //        mTopicsViewModel.load();
        //    }
        //});
    }

    private void showUsers(List<User> users) {

        String listString = "";

        if (users == null) {
            return;
        }

        for (User user : users) {
            listString += user.name + "\n";
            Log.d("TopicsActivityFragment", user.name);
        }

        //mTextView.setText(listString);

    }

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
        return "Topics";
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
}
