package cz.cas.ilaw.csrlaworigin.topicsscreen;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;
import cz.cas.ilaw.csrlaworigin.Settings.Settings;
import cz.cas.ilaw.csrlaworigin.db.AppDatabase;
import cz.cas.ilaw.csrlaworigin.db.DatabaseHelper;
import cz.cas.ilaw.csrlaworigin.db.utils.DatabaseInitializer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.reactivestreams.Subscription;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jiri Sereda
 */
@Singleton
public class UserRepository {

  private Webservice webservice;
  private Settings mSettings;
  private DatabaseHelper mDatabaseHelper;

  @Inject
  public UserRepository(Webservice webservice, Settings settings, DatabaseHelper databaseHelper) {
    this.webservice = webservice;
    this.mSettings = settings;
    this.mDatabaseHelper = databaseHelper;
  }

  public void getTickets() {

    if (mSettings.isMocked()) {

      List responses = new ArrayList<UserResponse>();
      responses.add(new UserResponse("1", "1", "A-fake", "email", "body", 50.086161, 14.415712));
      responses.add(new UserResponse("2", "2", "AA-fake", "email", "body", 50.058456, 14.495654));
      responses.add(new UserResponse("2", "3", "B-fake", "email", "body", 50.079931, 14.455831));
      responses.add(new UserResponse("2", "4", "C-fake", "email", "body", 50.072845, 14.433416));
      responses.add(new UserResponse("2", "5", "CA-fake", "email", "body", 50.083404, 14.448678));


      responses.add(new UserResponse("1", "6", "DD-fake", "email", "body", 50.086161, 14.406742));
      responses.add(new UserResponse("2", "7", "DDA-fake", "email", "body", 50.087078, 14.407742));
      responses.add(new UserResponse("2", "8", "DDD-fake", "email", "body", 50.085078, 14.406732));
      responses.add(new UserResponse("2", "9", "DFA-fake", "email", "body", 50.085478, 14.406832));
      responses.add(new UserResponse("2", "10", "DFFA-fake", "email", "body", 50.086478, 14.406532));


      mDatabaseHelper.addTickets(responses);
      return;
    }

    Log.d("getTickets()", "start");

    //CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    //
    //    mCompositeDisposable.add(webservice.getTickets()
    //        .subscribeOn(Schedulers.io())
    //        .observeOn(AndroidSchedulers.mainThread())
    //        .map(new Function<List<UserResponse>, String>() {
    //          @Override public String apply(final List<UserResponse> userResponse) throws Exception {
    //            Log.d("getTickets()", "apply");
    //            return userResponse.get(0).getName();
    //          }
    //        })
    //        .subscribe(new Consumer<String>() {
    //          @Override public void accept(String s) throws Exception {
    //            Log.d("getTickets()", "accept");
    //            Log.d("UserRepository : ", s);
    //          }
    //        })
    //    );


        //.subscribeOn(Schedulers.io())
        //.observeOn(AndroidSchedulers.mainThread())
        //.map(new Function<UserResponse, String>() {
        //  @Override public String apply(UserResponse userResponse) throws Exception {
        //    return userResponse.getName();
        //  }
        //})
        //.subscribe(new Consumer<String>() {
        //  @Override public void accept(String s) throws Exception {
        //    Log.d("TopicsViewModel", s);
        //    MutableLiveData<String> data = new MutableLiveData<>();
        //    data.setValue(s);
        //    userId = data;
        //  }
        //})
        //);

    //new Thread(new Runnable() {
    //  public void run() {
    //
    //    Log.d("UserRepository", "RUN WEB SERVICE");
    //
    //    webservice.getTickets(userId).enqueue(new Callback<List<UserResponse>>() {
    //      @Override public void onResponse(Call<List<UserResponse>> call, Response<List<UserResponse>> response) {
    //        mDatabaseHelper.addTickets(response.body());
    //      }
    //
    //      @Override public void onFailure(Call<List<UserResponse>> call, Throwable t) {
    //        Log.d("UserRepository", "failure");
    //      }
    //    });
    //  }
    //}).start();
  }
}
