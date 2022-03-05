package com.example.projectdirectory.AwesomeTMDB;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdirectory.R;

import java.util.List;

import retrofit2.Callback;

public class CustomAdapterTMDB extends RecyclerView.Adapter<CustomAdapterTMDB.ViewHolder> {
    public CustomAdapterTMDB(List<MovieResults.Result> resultList, Callback<MovieResults> context) {
        this.resultList = resultList;
        this.context = context;
    }

    List<MovieResults.Result> resultList;
    Callback<MovieResults> context;
    Context context1;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemtmdb, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final MovieResults.Result movieResults = resultList.get(position);
        holder.textView1.setText(movieResults.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   Intent intent = new Intent(holder.itemView.getContext(), Tmdbdescription.class);
                                                   intent.putExtra("description", movieResults.getOverview());
                                                   intent.putExtra("title", movieResults.getTitle());
                                                   intent.putExtra("imagepath",movieResults.getPosterPath());
                                                   holder.itemView.getContext().startActivity(intent);
                                               }
                                           }
        );
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.titleViewTmdb);


        }
    }
}
