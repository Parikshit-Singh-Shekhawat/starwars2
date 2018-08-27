package app.starwars.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import app.starwars.R;
import app.starwars.activities.DetailsActivity;
import app.starwars.listeners.LoadMoreData;
import app.starwars.pojo.CharacterListModel;

/**
 * Created by apple on 25/08/18.
 */

public class CharactersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CharacterListModel> characterList;
    private  LoadMoreData listenerLoadMore;
    private final int View_DATA=1;
    private  final int VIEW_PROGRESS=0;
    private   boolean hasError=false;
    Context ctx;
    public CharactersAdapter(Activity c, List<CharacterListModel> dataList, LoadMoreData listener) {
        this.characterList = dataList;
        this.listenerLoadMore=listener;
        this.ctx=c;
    }

    @Override
    public int getItemViewType(int position) {

        return characterList.get(position) == null ? VIEW_PROGRESS : View_DATA;
        //return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == View_DATA) {
            View itemView = LayoutInflater.from(ctx).inflate(R.layout.character_list_item, parent, false);
            return new CharactersViewHolder(itemView);
        } else if (viewType == VIEW_PROGRESS) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.loading_item, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,  int position) {

        if (holder instanceof CharactersViewHolder) {
            CharacterListModel character = characterList.get(position);
            CharactersViewHolder itemHolder = (CharactersViewHolder) holder;
            itemHolder.title.setText(character.getName());

        } else  if (holder instanceof LoadingViewHolder) {

            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            if (hasError) {
                loadingViewHolder.progressBar.setVisibility(View.GONE);
                loadingViewHolder.tryAgain.setVisibility(View.VISIBLE);
            } else
            {
                loadingViewHolder.progressBar.setVisibility(View.VISIBLE);
                loadingViewHolder.tryAgain.setVisibility(View.GONE);
            }

        }

        if(position==characterList.size()-1)
        {
            listenerLoadMore.loadMore();
        }

    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public class CharactersViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public CharactersViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ctx, DetailsActivity.class);
                    intent.putExtra("data",characterList.get(getAdapterPosition()));
                    ctx.startActivity(intent);
                }
            });
        }
    }

     class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;
        public TextView tryAgain;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar1);
            tryAgain = (TextView) itemView.findViewById(R.id.tryAgain);

            tryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setError(false);
                    listenerLoadMore.loadMore();
                }
            });
        }
    }

    public  void setError(boolean isError)
    {
        hasError=isError;
        notifyItemChanged(characterList.size()-1);
    }

}