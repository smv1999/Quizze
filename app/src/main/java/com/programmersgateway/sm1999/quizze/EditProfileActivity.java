package com.programmersgateway.sm1999.quizze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private EditText full_name,bio,dob,website;
    private Button submit;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        full_name = findViewById(R.id.fullname);
        bio = findViewById(R.id.bio);
        dob = findViewById(R.id.dob);
        website = findViewById(R.id.edit_profile_website);
        submit = findViewById(R.id.submit);
        mProgressBar = findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressBar.setVisibility(View.VISIBLE);

                if (!bio.getText().toString().equals("") && !dob.getText().toString().equals("") &&
                        !website.getText().toString().equals("")&&!full_name.getText().toString().equals("")
                        ){

                    if(mAuth.getCurrentUser() != null){

                        String yr = bio.getText().toString();
                        myRef.child("Users")
                                .child(mAuth.getCurrentUser().getUid())
                                .child("bio")
                                .setValue(yr);
                        String dt = website.getText().toString();
                        myRef.child("Users")
                                .child(mAuth.getCurrentUser().getUid())
                                .child("website")
                                .setValue(dt);
                        String sn = dob.getText().toString();
                        myRef.child("Users")
                                .child(mAuth.getCurrentUser().getUid())
                                .child("date of birth")
                                .setValue(sn);
                        String name = full_name.getText().toString();
                        myRef.child("Users")
                                .child(mAuth.getCurrentUser().getUid())
                                .child("full name")
                                .setValue(name);

                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(EditProfileActivity.this, "Profile Updated!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(EditProfileActivity.this,MainActivity.class);
                        startActivity(i);

                    }

                }
                else{
                    Toast.makeText(EditProfileActivity.this, "All the fields are compulsory!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(EditProfileActivity.this,MainActivity.class);
        startActivity(i);
        super.onBackPressed();
    }
}
