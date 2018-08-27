package app.starwars.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import app.starwars.pojo.CharacterListModel;
import app.starwars.pojo.CharacterListResult;
import app.starwars.retrofit.ApiResponse;
import app.starwars.retrofit.ServerApi;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by apple on 25/08/18.
 */

public class MainActivityViewModel extends ViewModel implements ApiResponse {

    private MutableLiveData<List<CharacterListModel>> characterListModelList;

    private MutableLiveData<Boolean> errorResult;

    private List<CharacterListModel> characterListFull=new ArrayList<>();
    private String pageUrl="1";
    private boolean loadingStatus=false;

    public LiveData<List<CharacterListModel>> getCharacterListModeles() {
        //if the list is null 
        if (characterListModelList == null) {
            characterListModelList = new MutableLiveData<List<CharacterListModel>>();
            //we will load it from server
//             loadCharacterListModeles();
        }
//        else if(characterListFull.size()==0)
//        {
//            loadCharacterListModeles();
//        }
        else
        {
            characterListModelList.setValue(characterListFull);
        }
        return characterListModelList;
    }

//    public LiveData<Boolean> checkError() {
//        //if the list is null
//        if (errorResult == null) {
//            errorResult = new MutableLiveData<Boolean>();
//
//        }
//        return errorResult;
//    }


    //This method is using Retrofit to get the data from URL
    public void loadCharacterListModeles() {
        if(!loadingStatus) {

            if (pageUrl != null) {
                loadingStatus=true;
                if (!pageUrl.equals("1")) {
                    pageUrl = pageUrl.substring(pageUrl.indexOf("=") + 1);

                }

                ServerApi.getInstance().getCharactersList(ApiResponse.GET_CHARACTERS, this, pageUrl);
            }
        }
        //showProgressDialog();
    }

    @Override
    public void onSuccess(Response response, int tag) {
        if (response.isSuccessful()) {
            CharacterListResult characterListResult = (CharacterListResult) response.body();
            pageUrl=characterListResult.getNext();
//            errorResult.setValue(false);
            characterListModelList.setValue(characterListResult.getResults());
            characterListFull.addAll(characterListResult.getResults());
        }
        else
            characterListModelList.setValue(null);

        loadingStatus=false;
    }

    @Override
    public void onFailure(Throwable t, int tag) {
        characterListModelList.setValue(null);
        loadingStatus=false;
    }

    public boolean checkForMore() {
            if(pageUrl!=null)
                return true;
            else
                return false;
    }


}