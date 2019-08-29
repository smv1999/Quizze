package com.programmersgateway.sm1999.quizze;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    Button editprofile;
    TextView name,bio,dob,website;
    private FirebaseAuth mAuth;
    private ProgressDialog working_dialog;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private FirebaseDatabase mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        if(mAuth.getCurrentUser() != null)
            myRef = mFirebaseDatabase.getReference().child("Users").child(mAuth.getCurrentUser().getUid());

        editprofile = findViewById(R.id.btn_edit_profile);
        name = findViewById(R.id.profile_name);
        bio = findViewById(R.id.profile_bio);
        dob = findViewById(R.id.profile_dob);
        website = findViewById(R.id.profile_website);



        showWorkingDialog();


        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, EditProfileActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        removeWorkingDialog();
                    }

                }, 3000);



                String names = dataSnapshot.child("full name").getValue(String.class);
                String years = dataSnapshot.child("bio").getValue(String.class);
                String depts = dataSnapshot.child("date of birth").getValue(String.class);
                String sections = dataSnapshot.child("website").getValue(String.class);
                name.setText(names);
                bio.setText(years);
                dob.setText(depts);
                website.setText(sections);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent k = new Intent(ProfileActivity.this, MainActivity.class);
        k.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(k);
    }


    private void showWorkingDialog() {
        working_dialog = ProgressDialog.show(ProfileActivity.this, "","Collecting Data Please Wait...", true);
    }

    private void removeWorkingDialog() {
        if (working_dialog != null) {
            working_dialog.dismiss();
            working_dialog = null;
        }
    }

}
