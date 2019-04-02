package net.ddns.bivor.inclass09_group14;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener,
SignUpFragment.OnFragmentInteractionListener, ContactFragment.OnFragmentInteractionListener,
CreateContactFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }


    @Override
    public void goToCreateNewContact() {
        
    }

    @Override
    public void goToLogInFromContact() {

    }

    @Override
    public void goToContactFromCreateNewContact() {

    }

    @Override
    public void goToSignUp() {

    }

    @Override
    public void goToContactFromLogIn() {

    }

    @Override
    public void goToLogInFromSignUp() {

    }

    @Override
    public void goToContactFromSignUp() {

    }
}
