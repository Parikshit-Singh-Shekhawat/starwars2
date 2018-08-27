package app.starwars.retrofit;

import java.util.concurrent.TimeUnit;

import app.starwars.StarWars;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apple on 25/08/18.
 */

public class ServerApi {

    public static final String BASE_URL = StarWars.BASE_URL;
    private static ServerApi ourInstance = new ServerApi();
    private Retrofit mRetrofit;
    private ApiReferences apiReference;
    OkHttpClient client;
    HttpLoggingInterceptor interceptor;

    private ServerApi() {

        interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client = new OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(25, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)

                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiReference = mRetrofit.create(ApiReferences.class);
    }

    public static ServerApi getInstance() {
        return ourInstance;
    }


//    public void Login(final int tag, String pass, String email, String Password, final ApiResponse ApiResponse) {
//        Call call = apiReference.userLogin(pass, email, Password, "St7kWo0p+gJ82kDhkSoWQmnTs/XOU=", "bfJXLxVECrYD+M8ELqC");
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(Call call, Response response) {
//                ApiResponse.onSuccess(response, tag);
//            }
//
//            @Override
//            public void onFailure(Call call, Throwable t) {
//                ApiResponse.onFailure(t, tag);
//            }
//        });
//    }
//
    public void getCharactersList(final int tag,  final ApiResponse ApiResponse,String pageStr) {
        Call call = apiReference.getCharacterList(pageStr);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                ApiResponse.onSuccess(response, tag);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                ApiResponse.onFailure(t, tag);
            }
        });
    }
}
