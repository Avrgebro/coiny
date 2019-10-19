package com.arsenic.coiny.Activities.More;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.arsenic.coiny.Model.Chat.Author;
import com.arsenic.coiny.Model.Chat.Message;
import com.arsenic.coiny.R;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.Date;

public class Help extends AppCompatActivity {

    private MessagesList messagesList;
    private MessagesListAdapter<Message> adapter;
    private MessageInput input;
    private Author author;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        messagesList = (MessagesList) findViewById(R.id.messagesList);
        input = (MessageInput) findViewById(R.id.input);

        adapter = new MessagesListAdapter<>("0", null);
        messagesList.setAdapter(adapter);

        input.setInputListener(new MessageInput.InputListener() {
            @Override
            public boolean onSubmit(CharSequence input) {
                Message m = addMessage("0", input.toString());
                adapter.addToStart(m, true);
                return true;
            }
        });

    }

    private Message addMessage(String userId, String text){
        String name = "";
        if(userId.equals("0")) {
            name = "User";
        } else {
            name = "Bot";
        }
        author = new Author(userId,name,null);
        return new Message(userId,author,text,new Date());
    }

}
