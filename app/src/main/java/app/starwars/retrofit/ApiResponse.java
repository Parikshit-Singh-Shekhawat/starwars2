package app.starwars.retrofit;


import retrofit2.Response;

/**
 * Created by apple on 25/08/18.
 */

public interface ApiResponse {

        int GET_CHARACTERS = 1;
        int GET_TIME_LINE=2;

        void onSuccess(Response response, int tag);
        void onFailure(Throwable t, int tag);
}


