package com.example.cvm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class NumberActivity extends AppCompatActivity {

    User user;
    String D,T,carnumber;
    CurrentUser currentUser;
    LocalDB localDB;
    DataBase db;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);


        localDB = new LocalDB(this.getApplicationContext());
        currentUser = localDB.isExists();
        db = new DataBase(this.getApplicationContext());
        user = db.getUser(currentUser.getPhone(),currentUser.getPassword());
        TextView name = findViewById(R.id.SenderName);
        name.setText(user.getName());

        TextView car = findViewById(R.id.CarNumber);
        carnumber=getIntent().getStringExtra("result");
        car.setText(carnumber);

        GPSTracker gpsTracker = new GPSTracker(this);
        String stringLatitude = String.valueOf(gpsTracker.latitude);
        String stringLongitude = String.valueOf(gpsTracker.longitude);

        //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        InsertRecord insertRecord = new InsertRecord();
        insertRecord.setDateTime(currentDateTime());
        insertRecord.setName(user.getName());
        insertRecord.setNumber(carnumber);
        insertRecord.setPhNo(currentUser.getPhone());
        insertRecord.imageUrl="null";
        insertRecord.lat = stringLatitude;
        insertRecord.lng = stringLongitude;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        //databaseReference.push().setValue("sgdfhbj");


        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        D = date.format(formatter);
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        T = time.format(formatter2);
        TextView t1 = findViewById(R.id.Time1);
        t1.setText(T);
        TextView d1 = findViewById(R.id.Date1);
        d1.setText(D);
        FloatingActionButton send = findViewById(R.id.sendtoDB);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                History H = new History(user.getPhone(),"100",D,carnumber);
                Reward R = new Reward(user.getPhone(),"100","pending");
                db.InsertHistory(H);
                db.InsertRewarc(R);


                //uploadPicture();

                Toast.makeText(NumberActivity.this, "Challan Added Successfully", Toast.LENGTH_SHORT).show();
                Intent I = new Intent(getApplicationContext(),MainFrame.class);
                I.putExtra("Phone",currentUser.getPhone());
                I.putExtra("Password",currentUser.getPassword());
                startActivity(I);
            }
        });
    }
    public String currentDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");
        String currentTime = dateFormat.format(new Date());
        return currentTime;
    }
}
