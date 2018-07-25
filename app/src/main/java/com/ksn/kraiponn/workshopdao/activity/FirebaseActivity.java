package com.ksn.kraiponn.workshopdao.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ksn.kraiponn.workshopdao.R;

import java.util.HashMap;
import java.util.Map;

public class FirebaseActivity extends AppCompatActivity {
    private TextView tvResult;
    private EditText edtID;
    private EditText edtTempS1;
    private EditText edtTempS2;
    private EditText edtDetails;
    private Button btnFeedData;
    private DatabaseReference mReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        initInstance();
    }

    private void initInstance() {
        tvResult = findViewById(R.id.tvResult);
        edtID = findViewById(R.id.edtId);
        edtTempS1 = findViewById(R.id.edtTempS1);
        edtTempS2 = findViewById(R.id.edtTempS2);
        edtDetails = findViewById(R.id.edtDetail);
        btnFeedData = findViewById(R.id.btnFeedData);

        btnFeedData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewDataToFireBase();
            }
        });

        initFireBase();
    }

    private void initFireBase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mReff = database.getReference();
        mReff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map value = (Map) dataSnapshot.getValue();
                String strRealTime = "";
                strRealTime = "id: " + String.valueOf(value.get("id")) + "\n";
                strRealTime += "Temp sensor1: " + String.valueOf(value.get("temp_sensor1_on")) + "\n";
                strRealTime += "Temp sensor2: " + String.valueOf(value.get("temp_sensor2_on")) + "\n";
                strRealTime += "Details: " + String.valueOf(value.get("details"));
                tvResult.setText(strRealTime);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void writeNewDataToFireBase() {
        Map<String, Object> value = new HashMap<>();
        value.put("id", edtID.getText().toString());
        value.put("temp_sensor1_on", edtTempS1.getText().toString());
        value.put("temp_sensor2_on", edtTempS2.getText().toString());
        value.put("details", edtDetails.getText().toString());

        mReff.updateChildren(value);
    }
}
