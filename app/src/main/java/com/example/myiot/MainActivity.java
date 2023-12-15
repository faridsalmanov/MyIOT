package com.example.myiot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.myiot;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperatureTextView = findViewById(R.id.temperatureTextView);
        Button sendDataButton = findViewById(R.id.sendDataButton);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("temperature");

        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int randomTemperature = (int) (Math.random() * 100);


                sendTemperatureData(randomTemperature);
            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Integer temperature = snapshot.getValue(Integer.class);
                if (temperature != null) {
                    temperatureTextView.setText("Temperature: " + temperature + "°C");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendTemperatureData(int temperature) {

        databaseReference.setValue(temperature);
    }
}
