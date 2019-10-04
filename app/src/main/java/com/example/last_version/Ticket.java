package com.example.last_version;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Ticket extends AppCompatActivity {
    EditText  name, email,event;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    String eventName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        databaseReference= FirebaseDatabase.getInstance().getReference("Tickets");
        event  = (EditText) findViewById(R.id.event);
        eventName = getIntent().getStringExtra("eventName");
        event.setText(eventName);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(eventName);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        Button button = (Button) findViewById(R.id.goF);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTicket();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void addTicket(){
        String name1 = name.getText().toString();
        String email1 = email.getText().toString();
        String event1 = eventName;
        String ii = databaseReference.push().getKey();

        postTicket post = new postTicket(ii,name1,email1,event1);
        databaseReference.child(ii).setValue(post);
        Toast.makeText(getApplicationContext(),"تم تأكيد حجزك، شكرا لك",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(Ticket.this,MainActivity.class);
        startActivity(intent);
    }
}
