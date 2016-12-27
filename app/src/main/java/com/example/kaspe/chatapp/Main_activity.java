package com.example.kaspe.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Created by kaspe on 27-12-2016.
 */

public class Main_Activity extends AppCompatActivity implements View.OnKeyListener {

    EditText usernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logon);
        usernameInput = (EditText) findViewById(R.id.username_input);
        usernameInput.setOnKeyListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.close_settings) {

            finish();
        }
        if (id == R.id.darktheme_settings) {
            return true;



        }
        if (id == R.id.lighttheme_settings) {
            setTheme(R.style.Lighttheme);



        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            String username = usernameInput.getText().toString();
            Intent intent = new Intent(getApplicationContext(),ChatClass .class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        return true;
    }
}