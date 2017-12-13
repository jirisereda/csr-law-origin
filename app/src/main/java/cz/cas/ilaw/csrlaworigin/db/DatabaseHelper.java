package cz.cas.ilaw.csrlaworigin.db;

import android.arch.lifecycle.LiveData;
import cz.cas.ilaw.csrlaworigin.topicsscreen.UserResponse;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Jiri Sereda
 */
@Singleton public class DatabaseHelper {

  private final UserDao userDao;

  @Inject public DatabaseHelper(UserDao userDao) {
    this.userDao = userDao;
  }

  private void addTicket(UserResponse userResponse) {
    User user = new User();
    user.id = userResponse.getId();
    //user.name = userResponse.getName();
    user.lat = userResponse.getLatitude();
    user.lng = userResponse.getLongitude();

    userDao.save(user);
    //userDao.updateQueryContent(userResponse.getId(), userResponse.getName(), userResponse.getLatitude(), userResponse.getLongitude());
  }

  public void addTickets(final List<UserResponse> listResponse) {

    new Thread(new Runnable() {
      public void run() {
        // a potentially time consuming task
        for (UserResponse response : listResponse) {

          //if (!isAlreadyStored(response.getName())) {
            addTicket(response);
          //}


        }
      }
    }).start();
  }

  public LiveData<List<User>> loadAllUsers() {
    return userDao.loadAllUsers();
  }


  private boolean isAlreadyStored(String name) {

    User user = userDao.loadUserByName(name);

    return user != null;
  }

}
