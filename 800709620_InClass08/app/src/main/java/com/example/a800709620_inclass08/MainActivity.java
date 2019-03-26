package com.example.a800709620_inclass08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExpenseAppFragment.OnFragmentInteractionListener, AddExpenseFragment.OnFragmentInteractionListener, ShowExpense.OnFragmentInteractionListener{


    public static final ArrayList<Expense> expenses = new ArrayList<>();
    public static final ArrayList<Expense> selectedExpenses = new ArrayList<>();
    public FirebaseDatabase database;
    public DatabaseReference myRef;
// ...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Expense App");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("expenses");


        /* myRef.addValueEventListener(new ValueEventListener() {

            @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                //Log.w("loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });*/



            getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new ExpenseAppFragment(), "tag_expenseApp").commit();

    }

    @Override
    public void goToAddExpense() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new AddExpenseFragment(), "tag_addExpense")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void goToShowExpense(Expense expense) {
        selectedExpenses.add(expense);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ShowExpense(), "tag_showExpense").commit();
    }

    @Override
    public void goToExpenseOnCancel() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ExpenseAppFragment(), "tag_expenseApp").commit();
    }

    @Override
    public void goToExpenseOnAdd(Expense expense) {
        expenses.add(expense);
        myRef.setValue(expenses);
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ExpenseAppFragment(), "tag_expenseApp").commit();
    }


    @Override
    public void goToExpenseFromShow() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ExpenseAppFragment(), "tag_expenseApp").commit();
    }
}
