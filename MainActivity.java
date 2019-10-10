package com.example.firebaseregistration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    EditText editEmail,editPassword,editConPassword;
    Button registerBtn;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Registration form");

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConPassword = findViewById(R.id.editConPassword);

        registerBtn = findViewById(R.id.registerBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail.getText().toString().trim();
                String pass = editPassword.getText().toString().trim();
                String conPass = editConPassword.getText().toString().trim();


                if (TextUtils.isEmpty(email))
                {
                    Toast.makeText(MainActivity.this, "Please Entire Email id!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(pass))
                {
                    Toast.makeText(MainActivity.this, "Please Entire Password!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(conPass))
                {
                    Toast.makeText(MainActivity.this, "Please Entire Confirm Password!", Toast.LENGTH_LONG).show();
                    return;
                }


                if (pass.length() <6)
                {
                    Toast.makeText(MainActivity.this, "Minimum Six numbers", Toast.LENGTH_LONG).show();
                    return;
                }

                //progressBar

                if (pass.equals(conPass))
                {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(),RegisterdActivity.class));
                                        Toast.makeText(MainActivity.this, "Registration Complete", Toast.LENGTH_LONG).show();


                                    } else {

                                        Toast.makeText(MainActivity.this, "Password not matching!!", Toast.LENGTH_LONG).show();

                                    }

                                    // ...
                                }
                            });
                }
            }
        });
    }
}
