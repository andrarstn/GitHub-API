package com.kelompokempat.githubapi.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kelompokempat.githubapi.R;
import com.kelompokempat.githubapi.model.repo.ModelRepo;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private ArrayList<ModelRepo> modelRepoArrayList = new ArrayList<>();
    private Context context;

    public RepoAdapter(Context context) {
        this.context = context;
    }

    public void setRepoList(ArrayList<ModelRepo> items) {
        modelRepoArrayList.clear();
        modelRepoArrayList.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public RepoAdapter.RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_data, parent, false);
        return new RepoAdapter.RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepoAdapter.RepoViewHolder holder, int position) {
        ModelRepo item = modelRepoArrayList.get(position);

        holder.tvUsername.setText(item.getLogin());
        holder.tvUrl.setText(item.getHtmlUrl());
        /*holder.cvListUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.DETAIL_USER, modelFollowersArrayList.get(position));
                context.startActivity(intent);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return modelRepoArrayList.size();
    }


    public static class RepoViewHolder extends RecyclerView.ViewHolder {

        CardView cvListUser;
        TextView tvUsername, tvUrl;
        ImageView imageUser, imageArrow;

        public RepoViewHolder(View itemView) {
            super(itemView);
            cvListUser = itemView.findViewById(R.id.cvListUser);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvUrl = itemView.findViewById(R.id.tvUrl);
            imageUser = itemView.findViewById(R.id.imageUser);
            imageArrow = itemView.findViewById(R.id.imageArrow);

            imageArrow.setVisibility(View.GONE);
        }
    }
}
