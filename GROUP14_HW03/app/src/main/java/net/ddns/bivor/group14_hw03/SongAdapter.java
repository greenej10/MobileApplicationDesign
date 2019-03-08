package net.ddns.bivor.group14_hw03;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    ArrayList<Song> mData;

    public SongAdapter(ArrayList<Song> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.song_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = mData.get(i);
        viewHolder.textViewItemArtist.setText("Artist: "+song.artistName);
        viewHolder.textViewItemTrack.setText("Track: "+song.trackName);
        viewHolder.textViewItemPrice.setText("Price: "+song.trackPrice+" $");
        String date = song.dateTime;
        viewHolder.textViewItemDate.setText("Date: "+date.substring(5,7)+"-"+date.substring(8,10)+"-"+date.substring(0,4));
        viewHolder.song = song;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewItemTrack, textViewItemPrice, textViewItemArtist, textViewItemDate;
        Song song;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);



            textViewItemTrack = itemView.findViewById(R.id.textViewItemTrack);
            textViewItemPrice = itemView.findViewById(R.id.textViewItemPrice);
            textViewItemArtist = itemView.findViewById(R.id.textViewItemArtist);
            textViewItemDate = itemView.findViewById(R.id.textViewItemDate);

        }
    }


}
