package com.example.cvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainFrame extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frame);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        String Phone=i.getStringExtra("Phone");
        String Password=i.getStringExtra("Password");
        user = getUser(Phone,Password);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "opening camera activity", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(),OcrCaptureActivity.class);
                startActivity(intent);
            }
        });
        FloatingActionButton logout = findViewById(R.id.logoutfab);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logout();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        View view= navigationView.getHeaderView(0);
        TextView Namee = view.findViewById(R.id.NameTitle);
        TextView Emaill = view.findViewById(R.id.PhoneTitle);
        Namee.setText(user.getName());
        Emaill.setText(user.getEmail());
    }
    public User getUser(String phone, String passw){
        return new DataBase(getApplicationContext()).getUser(phone,passw);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_frame, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void Logout()
    {
        LocalDB db = new LocalDB(this);
        db.Remove();
        Intent I = new Intent(this, Login.class);
        startActivity(I);
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
