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
/*
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
*/

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
   // GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseAnalytics mFirebaseAnalytics;
    TextView mRegister;
    EditText mEmail, mPassword;
    Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase Analytics
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Firebase Authentication

        mAuth=FirebaseAuth.getInstance();

        //Login components
        mRegister=findViewById(R.id.register);
        mEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.password);
        mLogin=findViewById(R.id.login);


/*
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

*/
        //Login action
        mLogin.setOnClickListener(new View.OnClickListener() {
            private String TAG="";

            @Override
            public void onClick(View v) {
                String email,password;
                email=mEmail.getText().toString();
                password=mPassword.getText().toString();

                //Check correct data
                //Email check


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Incorrect Login",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Incorrect login",
                            Toast.LENGTH_SHORT).show();
                    return;
                }




                mAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");

                            Intent intent=new Intent(MainActivity.this,MenuActivity.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Authentication successful",
                                    Toast.LENGTH_SHORT).show();



                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
            }
        });

        //Listener Register
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });


    }
/*
    private void signIn() {
        GoogleSignInApi mGoogleSignInClient;
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 101);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 101) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    */
//TO know whether the user logged in or not
    @Override
    protected void onStart() {
        super.onStart();

FirebaseUser currentUser=mAuth.getCurrentUser();
updateUI(currentUser);

    }

    //Detects if a user logged in already
    private void updateUI(FirebaseUser currentUser) {

        if(currentUser!=null){

            Intent intent=new Intent(MainActivity.this,MenuActivity.class);
            startActivity(intent);



        }

    }




}
