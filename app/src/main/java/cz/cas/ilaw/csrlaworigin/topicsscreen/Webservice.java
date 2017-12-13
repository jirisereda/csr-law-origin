package cz.cas.ilaw.csrlaworigin.topicsscreen;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Jiri Sereda
 */

public interface Webservice {
  //@GET("posts/{user}")

  //@GET("posts/{user}/comments")
  //UserResponse getTickets(@Path("user") String userId);
  //Flowable<List<UserResponse>> getTickets(@Path("user") String userId);
  //Observable<UserResponse> getTickets(@Path("user") String userId);

  //void getUserPhoto(@Path("id") int id, Callback<Photo> cb);


  @GET("rest/api/1/ticket")
  Flowable<List<UserResponse>> getTickets();

  @GET("rest/api/1/ticket/{id}")
  Flowable<List<UserResponse>> getTicket(@Path("id") String userId);

}
