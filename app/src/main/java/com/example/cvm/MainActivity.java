package com.example.cvm;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!GlobalVar.numberPlateFound) {
                    startActivity(new Intent(getApplicationContext(),Login.class));
                }
                GlobalVar.canRun = false;
            }
        }, 2000);

    }
}
