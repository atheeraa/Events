package com.example.last_version;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Passion");
        recyclerView = (RecyclerView) findViewById(R.id.recylerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        // Set the layout manager to your recyclerview
        recyclerView.setLayoutManager(mLayoutManager);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Events");

    }

    private void firebaseSearch(String searchBar){
        Query search = databaseReference.orderByKey().startAt(searchBar).endAt(searchBar + "\uf88f");
        FirebaseRecyclerAdapter<Model, ViewHolder>  firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        search
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {

                        viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getImg(),model.getStatus(),model.getTime(),model.getDate(),model.getBy(),model.getLocation());
                        // dd=model.getDesc();

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        final ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListner(new ViewHolder.ClickListner() {
                            @Override
                            public void onItemClick(View view, int position) {

                                TextView titleView, timeView, byView, dateView , locView;
                                ImageView imageView, statusView ;
                                titleView = view.findViewById(R.id.titleRow);
                                timeView=view.findViewById(R.id.timeRow);
                                byView=view.findViewById(R.id.byRow);
                                dateView = view.findViewById(R.id.dateRow);
                                locView = view.findViewById(R.id.location);
                                imageView = view.findViewById(R.id.imgRow);
                                statusView = view.findViewById(R.id.status);

                                String titleString = titleView.getText().toString();
                                String timeString = timeView.getText().toString();
                                String byString = byView.getText().toString();
                                String dateString = dateView.getText().toString();
                                String locString = locView.getText().toString();


                                Drawable drawable = imageView.getDrawable();
                                Drawable drawableStatus = statusView.getDrawable();
                                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();
                                Bitmap bitmapStatus = ((BitmapDrawable)drawableStatus).getBitmap();

                                Intent intent = new Intent(view.getContext(), Details.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                                bitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);

                                 byte[] bytes = stream.toByteArray();
                                intent.putExtra("image",bytes);




                                Model model = getItem(position);
                                intent.putExtra("title",titleString);
                                intent.putExtra("time",timeString);
                                intent.putExtra("date",dateString);
                                intent.putExtra("by",byString);
                                intent.putExtra("desc",model.getDesc());
                                intent.putExtra("location",locString);

                               intent.putExtra("status",model.getStatus());
                                startActivity(intent);




                            }
                        });
                        return viewHolder;
                    }


                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    protected void onStart() {
        FirebaseApp.initializeApp(this);

        super.onStart();
        FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        databaseReference

                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getTitle(), model.getImg(),model.getStatus(),model.getTime(),model.getDate(),model.getBy(),model.getLocation());
                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        final ViewHolder viewHolder = super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListner(new ViewHolder.ClickListner() {
                            @Override
                            public void onItemClick(View view, int position) {


                                TextView titleView, timeView, byView, dateView,locView,  statusView;
                                ImageView imageView;

                                titleView = view.findViewById(R.id.titleRow);
                                timeView=view.findViewById(R.id.timeRow);
                                byView=view.findViewById(R.id.byRow);
                                dateView = view.findViewById(R.id.dateRow);
                                imageView = view.findViewById(R.id.imgRow);
                                statusView = view.findViewById(R.id.status);
                                locView = view.findViewById(R.id.location);


                                String titleString = titleView.getText().toString();
                                String timeString = timeView.getText().toString();
                                String byString = byView.getText().toString();
                                String dateString = dateView.getText().toString();
                                String statusString = statusView.getText().toString();
                                String locString = locView.getText().toString();

                                Drawable drawable = imageView.getDrawable();
                                Bitmap bitmap = ((BitmapDrawable)drawable).getBitmap();




                                Intent intent = new Intent(view.getContext(), Details.class);
                                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                                bitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);

                                bitmap.compress(Bitmap.CompressFormat.PNG, 100,stream);
                                byte[] bytes = stream.toByteArray();
                                intent.putExtra("image",bytes);

                                Model model = getItem(position);
                                intent.putExtra("title",titleString);
                                intent.putExtra("time",timeString);
                                intent.putExtra("date",dateString);
                                intent.putExtra("by",byString);

                                intent.putExtra("desc",model.getDesc());
                                intent.putExtra("status",model.getStatus());
                                intent.putExtra("location",locString);
                                startActivity(intent);




                            }
                        });
                        return viewHolder;
                    }



                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                firebaseSearch(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                firebaseSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.search){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 }





