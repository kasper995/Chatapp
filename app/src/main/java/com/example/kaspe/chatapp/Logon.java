package com.example.kaspe.chatapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.R.attr.button;

/**
 * Created by kaspe on 27-12-2016.
 */

public class Logon extends AppCompatActivity
{

    EditText userinput; // input of username
    Button logonBtn; //login button
    TextView users; //display users (for testing)
    Context context;
    String username;

    public void spref(String username)
    {
        userinput = (EditText) findViewById(R.id.usernameEditTxt);
        SharedPreferences sharedPref = context.getSharedPreferences("users",Context.MODE_PRIVATE); // declaring shared preferences with file users
        SharedPreferences.Editor editor	= sharedPref.edit(); // makes you able to edit in shared pref.

        editor.putString("username",username); // takes string
        editor.apply(); // applies changes
    }

    public void getsaveddata()
    {

        SharedPreferences sharedPref = context.getSharedPreferences("users",Context.MODE_PRIVATE); // declaring shared preferences with file users
        sharedPref.getString("username",username); // gets user as string
    }

    public void reset(View view)
    {
        username = "";

    }

    public void show()
    {

            users.setText(username);

    }



}


