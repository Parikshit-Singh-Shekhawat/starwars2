package app.starwars.retrofit;

import app.starwars.pojo.CharacterListResult;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by apple on 25/08/18.
 */

public interface ApiReferences {

    @GET("people/")
    Call<CharacterListResult> getCharacterList(@Query("page") String pageNo);

}
