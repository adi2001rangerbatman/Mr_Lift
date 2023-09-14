package com.example.mrlift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    Button btnlogin;
    EditText editTextUserName;
    EditText editTextPassword;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference reference;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btn_login2);
        editTextPassword=findViewById(R.id.editTextPassword);
        editTextUserName=findViewById(R.id.editTextUserName);

        auth = FirebaseAuth.getInstance();


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUserName.getText().toString();
                String password = editTextPassword.getText().toString();

                if (!(username.equals("") && password.equals(""))){
                    login(username,password);
                }
            }
        });

    }
    public void login(String username,String password){
        try{
            auth.signInWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isComplete()){
                        Intent intentlogin = new Intent(Login.this,Home.class);
                        startActivity(intentlogin);
                        Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(Login.this,"Login Unsuccessfully",Toast.LENGTH_SHORT).show();
                    }
                }

            });
        }catch (Exception e){
            Log.e("errors",String.valueOf(e));
        }

    }
}