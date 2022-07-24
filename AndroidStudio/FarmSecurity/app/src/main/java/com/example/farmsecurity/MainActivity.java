package com.example.farmsecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 로그인 버튼
        Button login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 메인 화면으로 이동
                Intent intent = new Intent(MainActivity.this, Main.class);
                startActivity(intent);
            }
        });
        
        // 회원가입 버튼
        Button signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 회원가입 화면으로 이동
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });

        // 아이디/비밀번호 찾기
        TextView find_idpw = (TextView) findViewById(R.id.find_idpw);
        find_idpw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 아이디/비밀번호 찾기 화면으로 이동
                Intent intent = new Intent(MainActivity.this, Find_idpw.class);
                startActivity(intent);
            }
        });
    }
}