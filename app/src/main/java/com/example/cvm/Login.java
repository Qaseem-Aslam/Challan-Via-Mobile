package com.example.cvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    String Phone,Password;
    String DBPhone, DBPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CurrentUser currentUser = isExists();
        if(currentUser==null) {
            setContentView(R.layout.activity_login2);
            TextView sgnup = findViewById(R.id.SgnUP);
            sgnup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent I = new Intent(getApplicationContext(),SignUp.class);
                    startActivity(I);
                }
            });
            Button button = findViewById(R.id.login1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText Ph = findViewById(R.id.phone);
                    EditText pas = findViewById(R.id.password);
                    Phone = Ph.getText().toString();
                    Password = pas.getText().toString();
                    if((Phone!=null && Password!=null) && (Phone.length()>0 && Password.length()>0)) {

                        DataBase db = new DataBase(getApplicationContext());
                        User user=db.getUser(Phone,Password);
                        if(user!=null){
                            if (insert(Phone, Password)) {
                                Intent I = new Intent(getApplicationContext(), MainFrame.class);
                                I.putExtra("Phone",Phone);
                                I.putExtra("Password",Password);
                                startActivity(I);
                            }
                        }
                        else{
                            Toast.makeText(Login.this, "Error! Phone Number or Password does not matched.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
        else{
            Intent I = new Intent(getApplicationContext(),MainFrame.class);
            I.putExtra("Phone",currentUser.getPhone());
            I.putExtra("Password",currentUser.getPassword());
            startActivity(I);
        }
    }
    private boolean insert(String Phone, String Password)
    {
        LocalDB db = new LocalDB(this.getApplicationContext());
        return db.Insert(Phone,Password);
    }
    private CurrentUser isExists()
    {
        LocalDB db = new LocalDB(this.getApplicationContext());
        return db.isExists();
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
