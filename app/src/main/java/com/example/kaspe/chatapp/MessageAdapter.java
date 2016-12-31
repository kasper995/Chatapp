package com.example.kaspe.chatapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MessageAdapter extends BaseAdapter {

    Context messageContext;
    List<Message> messageList;

    public MessageAdapter(Context context, List<Message> messages) {
        messageList = messages;
        messageContext = context;
    }

    @Override
    public int getCount() {
        return messageList.size();
    }

    @Override
    public Object getItem(int position) {
        return messageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageViewHolder holder;
        if (convertView == null) {
            LayoutInflater messageInflater = (LayoutInflater) messageContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = messageInflater.inflate(R.layout.message, null);
            holder = new MessageViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.img_thumbnail);
            holder.senderView = (TextView) convertView.findViewById(R.id.message_sender);
            holder.bodyView = (TextView) convertView.findViewById(R.id.message_body);

            convertView.setTag(holder);
        } else {
            holder = (MessageViewHolder) convertView.getTag();
        }

        Message message = (Message) getItem(position);

        holder.bodyView.setText(message.text);
        holder.senderView.setText(message.name);

        Picasso.with(messageContext).
                //finds your picture on twitter if it exist
                        load("https://twitter.com/" + message.name + "/profile_image?size=original").
                //otherwise uses default_userpicture as default
                        placeholder(R.mipmap.default_userpicture).
                //places picture into imageview
                        into(holder.thumbnailImageView);
        return convertView;
    }

    public void add(Message message) {
        //adds the message you wrote into the list
        messageList.add(message);
        //notifies the data has changed and will update the list
        notifyDataSetChanged();
    }


}