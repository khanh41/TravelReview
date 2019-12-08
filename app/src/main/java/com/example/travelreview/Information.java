package com.example.travelreview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Information extends AppCompatActivity {
    private Button btn_finish;
    private TextView tv_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        btn_finish = findViewById(R.id.btn_finish);
        tv_text = findViewById(R.id.tv_text);

        Intent intent = getIntent();
        tv_text.setText(intent.getStringExtra("name"));
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
