package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends
RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder>{
    private List<Article> articles = new ArrayList<>();
    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }
    interface ItemCallback {
        void onOpenDetails(Article article);
    }
    private ItemCallback itemCallback;
    public void setItemCallback (ItemCallback listener) {
        itemCallback = listener;
    }
    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        holder.itemTitleTextView.setText(article.title);
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
        Picasso.get().load(article.urlToImage).into(holder.itemImageView);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder {
        ImageView favoriteImageView;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {

            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;
        }

    }
}

