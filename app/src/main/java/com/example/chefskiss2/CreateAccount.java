package com.example.chefskiss2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {
    public AccountController AC;

    public static final String EXTRA_ID = "com.example.chefskiss2.database.EXTRA_ID";
    public static final String EXTRA_EMAIL = "com.example.chefskiss2.database.EXTRA_EMAIL";
    public static final String EXTRA_USERNAME = "com.example.chefskiss2.database.EXTRA_USERNAME";
    public static final String EXTRA_PASSWORD = "com.example.chefskiss2.database.EXTRA_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        EditText email = (EditText) findViewById(R.id.userEmail);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        TextView errorMessage = (TextView) findViewById(R.id.invalidInfoMessage);


        Button createAccount = (Button) findViewById(R.id.createAccountBtn2);


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailString = email.getText().toString();
                String usernameString = username.getText().toString();
                String passwordString = password.getText().toString();

                //*** Add later if username is already taken once DB is implemented
                if (emailString.contains("@") && emailString.endsWith(".com")) {

                    //Used to check a password contains uppercase, lowercase,
                    // has a special character, and a number
                    //https://www.geeksforgeeks.org/check-if-a-string-contains-uppercase-lowercase-special-characters-and-numeric-values/
                    String regex = "^(?=.*[a-z])(?=."
                            + "*[A-Z])(?=.*\\d)"
                            + "(?=.*[-+_!@#$%^&*., ?]).+$";

                    //Compiles regex?
                    Pattern p = Pattern.compile(regex);

                    //Checks if password matches values
                    Matcher m = p.matcher(passwordString);

                    //Checks if the password matches
                    if(m.matches()) {
                        //saveUser(emailString, usernameString, passwordString);
                        Account account = new Account(usernameString, emailString, passwordString);
                        CreateAccount.this.AC = new AccountController(account);
                        if(databaseHelper.addOne(account) == true) {
                            Intent intent = new Intent(CreateAccount.this, Homepage.class);
                            startActivity(intent);
                        }
                    } else {
                        errorMessage.setText("Password must contain:\n - Uppercase Letter\n - Lowercase Letter\n - Number\n - Special Character($!?,.)");
                    }
                } else {
                    errorMessage.setText("*Invalid Email Address");
                }
            }
        });
    }


}
