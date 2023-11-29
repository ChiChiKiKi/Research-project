package com.example.cd_ptc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class Activity3 extends AppCompatActivity {
    TextInputEditText Else;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Else = findViewById(R.id.write_else);

        CheckBox checkBox = findViewById(R.id.interestelse);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Else.setEnabled(true);
                }
                else{
                    Else.setEnabled(false);
                }
            }
        });
        Button btn = (Button) findViewById(R.id.next_button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity3.this, Activity0.class);
                startActivity(intent);
            }
        });
    }
}
