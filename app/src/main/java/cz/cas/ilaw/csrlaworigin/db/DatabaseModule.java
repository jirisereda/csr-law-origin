package cz.cas.ilaw.csrlaworigin.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * @author Jiri Sereda.
 */
@Module
public class DatabaseModule {

    @Singleton @Provides
    AppDatabase provideDb(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class,"step.db").build();
    }

    @Singleton @Provides
    RecordTopicDao provideRecordTopicDao(AppDatabase db) {
        return db.recordTopicDao();
    }

    @Singleton @Provides
    UserDao provideUserDao(AppDatabase db) {
        return db.userDao();
    }
}
