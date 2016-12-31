package com.example.kaspe.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;



public class ChatClass extends AppCompatActivity implements View.OnKeyListener, View.OnClickListener {

    final String MESSAGES_ENDPOINT = "http://pusher-chat-demo.herokuapp.com";
    final String APP_KEY ="faa685e4bb3003eb825c";

    MessageAdapter messageAdapter;
    EditText messageInput;
    Button sendButton;
    String username;
    EditText usernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        usernameInput = (EditText) findViewById(R.id.username_input);

        username = this.getIntent().getExtras().getString("username");
        Toast.makeText(this, "Welcome, " + username + "!", Toast.LENGTH_LONG).show();

        messageInput = (EditText) findViewById(R.id.message_input);
        messageInput.setOnKeyListener(this);

        sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(this);

        messageAdapter = new MessageAdapter(this, new ArrayList<Message>());
        final ListView messagesView = (ListView) findViewById(R.id.messages_view);
        messagesView.setAdapter(messageAdapter);
        //creates a new pusher with the chosen APP_KEY
        Pusher pusher = new Pusher(APP_KEY);

        pusher.connect();

        Channel channel = pusher.subscribe("messages");

        channel.bind("new_message", new SubscriptionEventListener() {
            @Override
            public void onEvent(String channelName, String eventName, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Message message = gson.fromJson(data, Message.class);
                        messageAdapter.add(message);
                        messagesView.setSelection(messageAdapter.getCount() - 1);
                    }

                });
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_chat, menu);
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
            //create a new activity of closing
            Intent i = new Intent(this, Closing.class);
            //closes all activies and open a new one
            i.setFlags(i.FLAG_ACTIVITY_CLEAR_TASK|i.FLAG_ACTIVITY_NEW_TASK);
            //starts the closing activity
            startActivity(i);
        }
        if (id == R.id.logout_settings) {
            //clears username field
            usernameInput.setText("");
            //returns to main
            finish();

        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP){
            postMessage();
        }
        return true;
    }

    private void postMessage()  {
        //gets the messages from the input field
        String text = messageInput.getText().toString();
        //checks if inputfield is empty
        if (text.equals("")) {
            //do notthing if empty
            return;
        }

        RequestParams params = new RequestParams();
        //the info that is send with the post
        params.put("text", text);
        params.put("name", username);
        params.put("time", new Date().getTime());
        //makes a new client
        AsyncHttpClient client = new AsyncHttpClient();
        //try to post the messages to the server
        client.post(MESSAGES_ENDPOINT + "/messages", params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //clears the input field so a new messages can be typed
                        messageInput.setText("");
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //notifies user that the post failed
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        postMessage();
    }

}