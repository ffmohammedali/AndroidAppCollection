package com.example.projectdirectory.MusicPlayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectdirectory.R;

import java.io.File;
import java.util.ArrayList;

public class CustomAdapterMusicPlayer extends RecyclerView.Adapter<CustomAdapterMusicPlayer.ViewHolder> {
    ArrayList<File> arrayList;
    Context context;
    private OnMusicClick onMusicClick;


    public CustomAdapterMusicPlayer(ArrayList<File> arrayList, Context context, OnMusicClick onMusicClick) {
        this.arrayList = arrayList;
        this.context = context;
        this.onMusicClick = onMusicClick;
    }


    @NonNull
    @Override
    public CustomAdapterMusicPlayer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemmusic, parent, false);
        return new ViewHolder(v, onMusicClick);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomAdapterMusicPlayer.ViewHolder holder, final int position) {

//        final File file = arrayList.get(position);
        holder.textView.setText(arrayList.get(position).getName().replace(".mp3", ""));
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(), SongPlayer.class);
                intent.putExtra("song_File", arrayList);
                intent.putExtra("position", position);
                //(holder.itemView.getContext(), intent);
//                holder.itemView.getContext().startActivity(intent);


            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        private OnMusicClick onMusicClick;

        public ViewHolder(@NonNull View itemView, OnMusicClick onMusicClick) {

            super(itemView);
            textView = itemView.findViewById(R.id.itemMusic);
            this.onMusicClick = onMusicClick;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onMusicClick.onClickMusic(getAdapterPosition());
        }
    }

    public interface OnMusicClick {
        void onClickMusic(int position);
    }
}
