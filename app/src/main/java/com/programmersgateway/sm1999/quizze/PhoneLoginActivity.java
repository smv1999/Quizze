package com.programmersgateway.sm1999.quizze;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PhoneLoginActivity extends AppCompatActivity {

    EditText mobile;
    Button button;
    String no;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        toolbar = findViewById(R.id.toolmainbar);
        toolbar.setTitle("Login with Phone Number");
        mobile = (EditText) findViewById(R.id.mobile);

        button = (Button) findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                no = mobile.getText().toString();
                Intent intent = new Intent(PhoneLoginActivity.this,VerifyNumberActivity.class);
                intent.putExtra("mobile",no);
                startActivity(intent);
            }
        });


    }


}
