package cz.cas.ilaw.csrlaworigin.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import cz.cas.ilaw.csrlaworigin.topicsscreen.Webservice;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Jiri Sereda.
 */
@Module
public class NetworkModule {

    @Singleton @Provides
    Webservice provideWebservice() {
        return new Retrofit.Builder()
            .baseUrl("https://admin.stopyrepubliky.cz/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Webservice.class);
    }

}
