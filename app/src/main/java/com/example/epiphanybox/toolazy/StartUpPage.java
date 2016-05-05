package com.example.epiphanybox.toolazy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartUpPage extends AppCompatActivity implements View.OnClickListener {


    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_page);



        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == button2) {
            startActivity(new Intent(this, MainActivity.class));
        }

        if (v == button3) {
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

}
