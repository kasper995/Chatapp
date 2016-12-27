package com.example.kaspe.chatapp;


import android.content.Context;
import android.content.SharedPreferences;
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

public class Logon
{

    EditText username; // input of username
    Button logonBtn; //login button
    TextView users; //display users (for testing)
    List list; // list of users
    Context context;

    public void spref(){

        SharedPreferences sharedPref = context.getSharedPreferences("users",Context.MODE_PRIVATE); // declaring shard preferences with file users
        SharedPreferences.Editor editor	= sharedPref.edit(); // makes you able to edit??? (come back to)

        Set<String> set = new HashSet<String>(); // i have no idea (come back to)
        set.addAll(list); // adds items to list
        editor.putStringSet( "users",set); // no idea (come back to)
        editor.apply(); // applies changes? (come back to)

    }

    public void getsaveddata(){

        SharedPreferences	sharedPref	= context.getSharedPreferences("users",Context.MODE_PRIVATE);
        Set<String> set = new HashSet<String>();
        list.addAll(sharedPref.getStringSet("users", set));
    }

    public void reset(View view) {
        list.clear();
        resetfile();
    }

    public void show(){
        if (list != null) {
            users.setText(list.toString());
        }
    }



}


