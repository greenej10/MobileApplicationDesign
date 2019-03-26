package com.example.a800709620_inclass08;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class EditExpenseFragment extends Fragment {

    ArrayList<String> category;
    TextView textViewCategory;
    EditText editTextAddExpenseName, editTextAddExpenseAmount;


    Expense expense;

    private EditExpenseFragment.OnFragmentInteractionListener mListener;

    public EditExpenseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        category = new ArrayList<>(Arrays.asList("Groceries","Invoice","Transportation","Shopping", "Rent", "Trips",
                "Utilities","Other"));

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_expense, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AddExpenseFragment.OnFragmentInteractionListener) {
            mListener = (EditExpenseFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Add Expense");
        editTextAddExpenseName = getActivity().findViewById(R.id.editTextAddExpenseName);
        editTextAddExpenseAmount = getActivity().findViewById(R.id.editTextAddExpenseAmount);
        textViewCategory = getActivity().findViewById(R.id.textViewAddExpenseSelectCategory);

        getActivity().findViewById(R.id.textViewAddExpenseSelectCategory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Choose a Keyword")
                        .setItems(category.toArray(new CharSequence[category.size()]), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                textViewCategory.setText(category.get(which));
                            }
                        });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        getActivity().findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.goToExpenseOnCancel();
            }
        });

        getActivity().findViewById(R.id.buttonAddExpense).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAddExpenseName.getText().toString().isEmpty()){
                    editTextAddExpenseName.setError("Enter Expense Name");
                    Toast.makeText(getActivity(), "Enter Expense Name", Toast.LENGTH_SHORT).show();
                }
                else if(editTextAddExpenseAmount.getText().toString().isEmpty()){
                    editTextAddExpenseAmount.setError("Enter Expense Name");
                    Toast.makeText(getActivity(), "Enter Expense Amount", Toast.LENGTH_SHORT).show();
                }
                else if (textViewCategory.getText().toString().equals("Select Category")){
                    Toast.makeText(getActivity(), "Select a Category", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Toast.makeText(getActivity(), "Entered ELSE", Toast.LENGTH_SHORT).show();

                    expense = new Expense();
                    expense.name = editTextAddExpenseName.getText().toString();
                    expense.amount = editTextAddExpenseAmount.getText().toString();
                    expense.category = textViewCategory.getText().toString();
                    expense.date = new Date();

                    mListener.goToExpenseOnAdd(expense);
                }

            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
