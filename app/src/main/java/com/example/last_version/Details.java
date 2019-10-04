package com.example.last_version;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    TextView titleV, descV, dateV, timeV, byV;
    ImageView imgV;
    String desc, time, date, by, title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        descV = findViewById(R.id.descD);
        dateV = findViewById(R.id.dateD);
        timeV = findViewById(R.id.timeD);
        byV = findViewById(R.id.byD);
        imgV = findViewById(R.id.imgD);



        byte[] bytes = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        title = getIntent().getStringExtra("title");
        actionBar.setTitle(title);

        desc = getIntent().getStringExtra("desc");
        time = getIntent().getStringExtra("time");
        date = getIntent().getStringExtra("date");
        by = getIntent().getStringExtra("by");


        descV.setText(desc);
        dateV.setText(date);
        timeV.setText(time);
        byV.setText(by);
        imgV.setImageBitmap(bitmap);

        Button button = (Button) findViewById(R.id.ticket);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Ticket.class);
                intent.putExtra("eventName", title);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
