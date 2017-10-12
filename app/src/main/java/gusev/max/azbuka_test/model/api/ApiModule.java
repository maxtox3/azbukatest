package gusev.max.azbuka_test.model.api;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by v on 09/10/2017.
 */

public class ApiModule {

    private static final boolean ENABLE_LOG = true;

    private static final String BASE_URL = "http://shmot.ga/api/";

    public static ApiInterface getApiInteface() {

        OkHttpClient client = new OkHttpClient();

        if (ENABLE_LOG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.interceptors().add(interceptor);
        }

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        builder.client(client);

        return builder.build().create(ApiInterface.class);
    }


}
