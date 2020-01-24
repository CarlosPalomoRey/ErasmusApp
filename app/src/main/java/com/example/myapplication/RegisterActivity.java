package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    Button mLogin;
    EditText mEmail, mPassword, mDate;
    Button mRegister;

    //Firebase
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        mRegister=findViewById(R.id.register);
        mEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.password);
        mLogin=findViewById(R.id.login);
        mDate=findViewById(R.id.date);

        //Initialize Firebase
        mAuth = FirebaseAuth.getInstance();


        mRegister.setOnClickListener(new View.OnClickListener() {
            private String TAG="";

            @Override
            public void onClick(View v) {
                String email,password,birthdate;
                email=mEmail.getText().toString();
                password=mPassword.getText().toString();
                birthdate=mDate.getText().toString();

                //Check correct data
                //Datecheck
                String regex0 = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";

                Pattern patternBirthdate = Pattern.compile(regex0);
                Matcher matcherBirthdate=patternBirthdate.matcher(birthdate);
                //Check correct registration
                //Email check
                String regex1 = "^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*\n" +
                        "      @[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$";

                Pattern patternEmail = Pattern.compile(regex1);


                Matcher matherEmail=patternEmail.matcher(email);
                //Password check
                String regex2 =  "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,40})";

                Pattern patternPassword=Pattern.compile(regex2);


                Matcher matherPassword=patternPassword.matcher(password);


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Incorrect Login",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "Incorrect login",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(matherEmail.find()) {
                    Toast.makeText(RegisterActivity.this, "Try email again",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(matherPassword.find()) {
                    Toast.makeText(RegisterActivity.this, "Try password again",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(matcherBirthdate.find()) {
                    Toast.makeText(RegisterActivity.this, "Incorrect date of birth",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


            //register
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    Intent intent=new Intent(RegisterActivity.this,MenuActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(RegisterActivity.this, "Registration successful",
                                            Toast.LENGTH_SHORT).show();

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(RegisterActivity.this, "Registration failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }


                            }
                        });

            }
        });
    mLogin.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
    }
});

    }

    //Detects if a user logged in already
    private void updateUI(FirebaseUser currentUser) {

        if(currentUser!=null){

            Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);



        }

    }

}
