package com.example.farmsecurity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Signup extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        setTitle("Second 액티비티");

        Button btnReturn = (Button) findViewById(R.id.button_signup_finish);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}
