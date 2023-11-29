package com.example.cd_ptc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity1 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView rgt = (TextView) findViewById(R.id.register);
        Button lgn = (Button) findViewById(R.id.login_button);

        rgt.setOnClickListener(this);
        lgn.setOnClickListener(this);
    }
    public void onClick(View v) {
        if (v.getId() == R.id.login_button){
            Intent intent1 = new Intent(Activity1.this, Activity3.class);
            startActivity(intent1);

            Toast.makeText(getApplicationContext(), "로그인하셨습니다.", Toast.LENGTH_SHORT).show();
        }
        else if (v.getId() == R.id.register){
            Intent intent = new Intent(Activity1.this, Activity2.class);
            startActivity(intent);
        }
    }
}