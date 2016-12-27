package com.example.kaspe.chatapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.kaspe.chatapp.R.styleable.View;

/**
 * Created by kaspe on 27-12-2016.
 */



public class ChatClass extends ActionBarActivity implements android.view.View.OnClickListener {

    EditText messageInput;
    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get our input field by its ID
        messageInput = (EditText) findViewById(R.id.message_input);


        // get our button by its ID
        sendButton = (Button) findViewById(R.id.send_button);

        // set its click listener
        sendButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        postMessage();
    }

    private void postMessage() {
    }
}