package com.programmersgateway.sm1999.quizze;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView online_quiz;
    ImageView bopl;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        online_quiz = findViewById(R.id.online_quiz);
        bopl = findViewById(R.id.bopl);
        navigationView = findViewById(R.id.mainnavView);
        toolbar = findViewById(R.id.toolmainbar);
        toolbar.setTitle("Quiz Categories");
        drawerLayout = findViewById(R.id.drawerlayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View navView = navigationView.inflateHeaderView(R.layout.navigation_header);


        bopl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,BOPL.class);
                startActivity(i);
            }
        });

        online_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://smv1999.github.io/prog_quiz.htm"));
                startActivity(browserIntent);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                        Intent  i= new Intent(MainActivity.this,MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);
                        break;
                    case R.id.logout:
                        mAuth.signOut();
                        Intent j = new Intent(MainActivity.this,LoginActivity.class);
                        j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(j);
                        break;
                    case R.id.settings:
                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
                        builderSingle.setIcon(R.drawable.ic_settings);
                        builderSingle.setTitle("Settings");

                        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_activated_1);
                        arrayAdapter.add("How to Play");
                        arrayAdapter.add("Rate Us");
                        arrayAdapter.add("More Apps");
                        arrayAdapter.add("Privacy Policy");
                        arrayAdapter.add("Visit Our Website");


                        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String strName = arrayAdapter.getItem(which);
                                if(strName.equals("More Apps"))
                                {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Programmers+Gateway"));
                                    startActivity(browserIntent);
                                }
                                if(strName.equals("Visit Our Website"))
                                {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://smv1999.github.io/programmersgateway"));
                                    startActivity(browserIntent);
                                }
                            }
                        });
                        builderSingle.show();
                        break;
                    case R.id.profile:
                        Intent p =new Intent(MainActivity.this,ProfileActivity.class);
                        startActivity(p);
                        break;

                }
                return false;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
