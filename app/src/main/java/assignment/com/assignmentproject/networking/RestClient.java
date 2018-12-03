package assignment.com.assignmentproject.networking;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import assignment.com.assignmentproject.constant.MobileDataUsageAPI;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * For REST over HTTP(S). Holds the client for other services to put interfaces against.
 */
public class RestClient {
    private static final String TAG = RestClient.class.toString();

    public RestClient() {
        getClient();
    }

    /**
     * @return
     */
    public static Retrofit getClient() {
        return RetrofitAPI.retrofit;
    }


    private static class RetrofitAPI {
        static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
//                .addInterceptor(BuildConfig.DEBUG ? httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//                        : httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE))
                .addInterceptor(new ConnectivityInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        private static HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        /**
         * For TMW APIs
         */
        private static Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MobileDataUsageAPI.getBaseUrl())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

    }
}
