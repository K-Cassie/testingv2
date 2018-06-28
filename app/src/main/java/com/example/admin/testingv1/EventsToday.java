package com.example.admin.testingv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class EventsToday extends AppCompatActivity implements View.OnClickListener{

    private TextView theDate;
    private Button backToProfile;
    private Button addEvent;
    private FirebaseAuth firebaseAuth;
    private String date;
    private RecyclerView myRecyclerView;
    private DatabaseReference mRef;
    private String userID;
    private FirebaseRecyclerAdapter <Event, EventViewHolder> recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_today);

        theDate = (TextView) findViewById(R.id.theDate);
        addEvent = (Button) findViewById(R.id.addEvent);
        backToProfile = (Button) findViewById(R.id.backToProfile);
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getCurrentUser().getUid();

        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setHasFixedSize(true);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent incomingIntent = getIntent();
        date = incomingIntent.getStringExtra("date");
        String [] info = date.split("/");
        int day = Integer.parseInt(info[0]);
        int month = Integer.parseInt(info [1]);
        int year = Integer.parseInt(info [2]);

        mRef = FirebaseDatabase.getInstance().getReference();
        int key = year*1000 +month *100 +day;
        Query query = mRef.child("Users").child(userID).child("Events").child(Integer.toString(key));


        theDate.setText(date);

        backToProfile.setOnClickListener(this);
        addEvent.setOnClickListener(this);

        FirebaseRecyclerOptions<Event> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<Event>().setQuery(query, Event.class).build();
        recyclerAdapter = new FirebaseRecyclerAdapter<Event, EventViewHolder>
                (firebaseRecyclerOptions) {
            @Override
            public EventViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_info, parent, false);
                return new EventViewHolder(view);
            }
            @Override
            protected void onBindViewHolder(EventViewHolder viewHolder, int position, Event model) {
                viewHolder.setTitle(model.getTitle());
                viewHolder.setStartTime(model.getStartTime());
                viewHolder.setEndTime("-   " +model.getEndTime());
                viewHolder.setRemarks(model.getRemarks());
            }
        };
        myRecyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        recyclerAdapter.startListening();
    }
    @Override
    protected void onStop(){
        super.onStop();
        if(recyclerAdapter!= null){
            recyclerAdapter.stopListening();
        }
    }

    public void onClick(View view) {
        if (view == backToProfile) {
            //will open login
            startActivity(new Intent(this, ProfileActivity.class));
        }
        if (view == addEvent) {
            Intent intent = new Intent(EventsToday.this, addEvent.class);
            intent.putExtra("date", date);
            startActivity(intent);
        }
    }
}
