package net.ddns.bivor.hw04_group14;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    ArrayList<Recipe> mData;
    private FragmentCommunication mCommunicator;

    public RecipeAdapter(ArrayList<Recipe> mData, FragmentCommunication mCommunicator) {
        this.mData = mData;
        this.mCommunicator = mCommunicator;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_item, parent, false);
        RecipeAdapter.ViewHolder viewHolder = new RecipeAdapter.ViewHolder(view, mCommunicator, mData);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.recipes = mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        FragmentCommunication mCommunication;
        ArrayList<Recipe> recipes;

        public ViewHolder(final View itemView, FragmentCommunication Communicator, ArrayList<Recipe> list) {
            super(itemView);
            mCommunication = Communicator;
            recipes = list;

        }
    }
}
