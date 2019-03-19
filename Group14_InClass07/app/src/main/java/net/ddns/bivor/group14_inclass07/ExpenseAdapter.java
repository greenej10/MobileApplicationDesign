package net.ddns.bivor.group14_inclass07;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder>{

    ArrayList<Expense> mData;
    public OnItemClickListener mListener;

    public ExpenseAdapter(ArrayList<Expense> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expense_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Expense expense = mData.get(i);
        viewHolder.textViewExpenseItem.setText(expense.name);
        viewHolder.textViewItemCost.setText("$"+ expense.amount);
        viewHolder.expense = expense;
        viewHolder.expenses = mData;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewExpenseItem, textViewItemCost;
        Expense expense;
        ArrayList<Expense> expenses;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewExpenseItem = itemView.findViewById(R.id.textViewExpenseItem);
            textViewItemCost = itemView.findViewById(R.id.textViewItemCost);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    MainActivity.expenses.remove(expense);
                    return false;
                }
            });


        }

        @Override
        public void onClick(View v) {
            
        }

    }

    public interface OnItemClickListener{
        void OnItemClick(Expense expense);
    }
}
