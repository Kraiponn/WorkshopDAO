package com.ksn.kraiponn.workshopdao.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ksn.kraiponn.workshopdao.R;

public class FireBase2Activity extends AppCompatActivity {

    private TextView tvMsg;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReff;
    private DatabaseReference mChildReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initInstance();
    }

    private void initInstance() {
        tvMsg = findViewById(R.id.tvMsg);

        mDatabase = FirebaseDatabase.getInstance();
        mReff = mDatabase.getReference();
        mChildReff = mReff.child("details");
        mChildReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String str = dataSnapshot.getValue(String.class);
                Log.d("DataSnapShort", str);
                tvMsg.setText(str);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
