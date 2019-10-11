package passion.events.last_version;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    public static final String CHANNEL_ID = "channel_id";

    TextView titleV, descV, dateV, timeV, byV, locV;
    ImageView imgV;
    String desc, time, date, by, title, location, status;
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
        locV=findViewById(R.id.location);




        byte[] bytes = getIntent().getByteArrayExtra("image");
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);

        title = getIntent().getStringExtra("title");
        actionBar.setTitle(title);

        desc = getIntent().getStringExtra("desc");
        time = getIntent().getStringExtra("time");
        date = getIntent().getStringExtra("date");
        by = getIntent().getStringExtra("by");
        location = getIntent().getStringExtra("location");
    status = getIntent().getStringExtra("status");

        descV.setText(desc);
        dateV.setText(date);
        timeV.setText(time);
        byV.setText(by);
        locV.setText(location);

        imgV.setImageBitmap(bitmap);

        
        final Button button = (Button) findViewById(R.id.ticket);
       button.setOnClickListener(new View.OnClickListener() {
                    @Override
            public void onClick(View v) {

                if(status.equals("No")){
                    button.setEnabled(false);
                    Toast.makeText(getApplicationContext(),"انتهى وقت هذه الفعالية",Toast.LENGTH_LONG).show();
                }
                else {
                  Intent intent = new Intent(getApplicationContext(),Ticket.class);
                intent.putExtra("eventName", title);
                intent.putExtra("status",status);
                startActivity(intent);}  
                          }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
