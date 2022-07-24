package com.example.farmsecurity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Change_pw extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_pw);
        setTitle("비밀번호 변경");

        Button btnReturn = (Button) findViewById(R.id.button_find_id);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

    }
}
