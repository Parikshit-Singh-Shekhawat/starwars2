package app.starwars.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.starwars.R;
import app.starwars.adapters.CharactersAdapter;
import app.starwars.listeners.LoadMoreData;
import app.starwars.pojo.CharacterListModel;
import app.starwars.utilities.Utilities;
import app.starwars.viewmodels.MainActivityViewModel;

/**
 * Created by apple on 25/08/18.
 */
public class MainActivity extends BaseActivity implements LoadMoreData {

    private List<CharacterListModel> characterList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CharactersAdapter mAdapter;
    MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!Utilities.isInternetConnected(this)) {
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
            return;
        }

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getCharacterListModeles().observe(this, new Observer<List<CharacterListModel>>() {
            @Override
            public void onChanged(@Nullable List<CharacterListModel> heroList) {
                // Removing progress bar at bottom

                if(heroList!=null) {
                    characterList.remove(characterList.size() - 1);
                    mAdapter.notifyItemRemoved(characterList.size());


                    //Adding result
                    mAdapter.setError(false);
                    characterList.addAll(heroList);
                    mAdapter.notifyItemRangeInserted(characterList.size() - heroList.size(), heroList.size());

                    // Adding progress bar at bottom  if  there is more data
                    if (mainActivityViewModel.checkForMore())
                        addProgressBar();

                }
                else
                {
                    mAdapter.setError(true);
                }
            }
        });

//        mainActivityViewModel.checkError().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean errorResult) {
//                // Removing progress bar at bottom
//                mAdapter.setError(errorResult);
//
//            }
//        });
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        initViews();
    }

    private void initViews()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new CharactersAdapter(this,characterList,MainActivity.this);

        // vertical RecyclerView
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        addProgressBar();

    }

    private void addProgressBar()
    {
        CharacterListModel characterListModel=null;
        characterList.add(characterListModel);
        mAdapter.notifyItemInserted(characterList.size()-1);
    }

    @Override
    public void loadMore() {
        if(mainActivityViewModel.checkForMore())
            mainActivityViewModel.loadCharacterListModeles();
    }
}
