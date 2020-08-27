package com.example.cvm;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {


    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button button = findViewById(R.id.signup1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText Ph = findViewById(R.id.phone1);
                EditText Nm = findViewById(R.id.editText2);
                EditText psw1 = findViewById(R.id.password1);
                EditText psw2 = findViewById(R.id.password2);
                EditText email = findViewById(R.id.email2);
                String Phone = Ph.getText().toString();
                String Name = Nm.getText().toString();
                String Pass1 = psw1.getText().toString();
                String Pass2 = psw2.getText().toString();
                String Email = email.getText().toString();
                if (isValidEmail(Email)) {
                    if (Pass1.equals(Pass2)) {
                        user = new User(Name, Phone, Pass1, email.getText().toString());
                        DataBase db = new DataBase(getApplicationContext());
                        if (db.InsertUser(user)) {
                            LocalDB db1 = new LocalDB(getApplicationContext());
                            db1.Insert(user.getPhone(), user.getPassword());
                            Intent I = new Intent(getApplicationContext(), MainFrame.class);
                            startActivity(I);
                        } else {
                            Toast.makeText(SignUp.this, "User already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        psw1.setText("");
                        psw2.setText("");
                        psw1.requestFocus();
                        Toast.makeText(SignUp.this, "Password and Confirm Password Did not matched! ", Toast.LENGTH_SHORT).show();
                    }
                }
            else{
                email.setText("");
                    psw1.setText("");
                    psw2.setText("");
                    psw1.requestFocus();
                    Toast.makeText(SignUp.this, "Invalid Email Format! ", Toast.LENGTH_SHORT).show();
                }
        }
        });


    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
