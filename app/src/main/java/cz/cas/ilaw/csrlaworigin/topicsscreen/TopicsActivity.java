package cz.cas.ilaw.csrlaworigin.topicsscreen;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;
import cz.cas.ilaw.csrlaworigin.MyApplication;
import cz.cas.ilaw.csrlaworigin.R;
import cz.cas.ilaw.csrlaworigin.base.BaseActivity;
import cz.cas.ilaw.csrlaworigin.db.User;
import java.util.List;
import javax.inject.Inject;

public class TopicsActivity extends BaseActivity implements OnMapReadyCallback, TicketsAdapter.TicketListener {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.search)
    android.support.v7.widget.SearchView searchView;

    @BindView(R.id.topics) ListView mTopics;

    private TopicsViewModel mTopicsViewModel;

    private GoogleMap mMap;

    private ClusterManager<MyItem> mClusterManager;

    private TicketsAdapter mTicketsAdapter;

    /**
     * Starts the activity.
     *
     * @param context The context to start from.
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, TopicsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void injectSelf() {
        MyApplication.get(this).getComponent().injectTopicsActivity(this);
    }

    @Override public void onMapReady(GoogleMap googleMap) {
        Log.d("TopicsActivity", "onMapReady");

        if (mMap != null) {
            Log.d("TopicsActivity", "onMapReady != null");
            return;
        }
        mMap = googleMap;

        showUsers(mTopicsViewModel.getUser().getValue());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_topics);
        ButterKnife.bind(this);

        setUpMap();

        mTopicsViewModel = ViewModelProviders.of(this,viewModelFactory).get(TopicsViewModel.class);
        mTopicsViewModel.getUser().observe(this, new Observer<List<User>>() {
            @Override public void onChanged(@Nullable List<User> users) {
                Log.d("TopicsActivity", "onCreate onChanged");
                showUsers(users);
            }
        });

        searchView.setOnCloseListener(new android.support.v7.widget.SearchView.OnCloseListener() {
            @Override public boolean onClose() {
                Log.d("TopicsActivity", "onClose");
                mTopics.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Log.d("TopicsActivity", "setOnSearchClickListener");

                mTopics.setVisibility(View.VISIBLE);
                mTicketsAdapter.setData(mTopicsViewModel.getUser().getValue());

            }
        });

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                Log.d("TopicsActivity", "onQueryTextSubmit");
                return false;
            }

            @Override public boolean onQueryTextChange(String newText) {
                Log.d("TopicsActivity", "onQueryTextChange");
                mTopics.setVisibility(View.VISIBLE);
                mTicketsAdapter.filter(newText);
                return false;
            }
        });

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                searchView.setIconified(false);
            }
        });

        initListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mTopicsViewModel.load();
    }

    private void setUpMap() {
        Log.d("TopicsActivity", "setUpMap");
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }


    private void showUsers(List<User> users) {

        Log.d("TopicsActivity", "showUsers");

        if (mMap == null) {
            Log.d("TopicsActivity", "showUsers map is not ready");
            return;
        }

        Log.d("TopicsActivity", "showUsers map is ready");

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(50.082555, 14.426095), 10));

        mClusterManager = new ClusterManager<MyItem>(this, mMap);
        mClusterManager.setRenderer(new TicketRenderer());
        mMap.setOnCameraIdleListener(mClusterManager);

        if (users == null) {
            return;
        }

        for (User user : users) {
            mClusterManager.addItem(new MyItem(user.lat, user.lng, user.name, "popis"));
            mClusterManager.setOnClusterClickListener(new ClusterManager.OnClusterClickListener<MyItem>() {
                @Override public boolean onClusterClick(Cluster<MyItem> cluster) {
                    return false;
                }
            });

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override public boolean onMarkerClick(Marker marker) {
                    return false;
                }
            });

        }

        mClusterManager.cluster();
    }


    private void initListView() {
        mTicketsAdapter = new TicketsAdapter(this, this);
        mTopics.setAdapter(mTicketsAdapter);
    }

    @Override public void onTicketSelected(User user) {
        Log.d("TopicsActivity", "onTicketSelected");
        mTopics.setVisibility(View.INVISIBLE);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(user.lat, user.lng), 18));
        searchView.setQuery(user.name, false);
    }


    private class TicketRenderer extends DefaultClusterRenderer<MyItem> {

        public TicketRenderer() {
            super(getApplicationContext(), mMap, mClusterManager);
        }

        @Override protected void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {

            //markerOptions.title("AAAA");
            //markerOptions.snippet("SnipletA");
            //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));

            super.onBeforeClusterRendered(cluster, markerOptions);
        }

        @Override protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions) {

            IconGenerator iconFactory = new IconGenerator(getApplicationContext());

            markerOptions.title(item.getTitle());
            markerOptions.snippet(item.getSnippet());
            //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher));
            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(item.getTitle())));


            super.onBeforeClusterItemRendered(item, markerOptions);
        }
    }

}
