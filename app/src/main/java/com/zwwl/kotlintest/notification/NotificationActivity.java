package com.zwwl.kotlintest.notification;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zwwl.kotlintest.R;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        TextView textView = findViewById(R.id.textView);
        if (getIntent().hasExtra("data")) {
            textView.setText(getIntent().getStringExtra("data"));
        }
    }
}