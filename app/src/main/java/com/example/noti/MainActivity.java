package com.example.noti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button start, stop;
    View.OnClickListener cl;
    TextView myText;

    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.fore_start);
        stop = (Button) findViewById(R.id.fore_stop);

        myText = findViewById(R.id.text);

        long mNow = System.currentTimeMillis();
        Date mReDate = new Date(mNow);
        SimpleDateFormat mFormat = new SimpleDateFormat("MM-dd HH:mm");
        String formatDate = mFormat.format(mReDate);

        while (myText==null);

        myText.setText("업데이트 "+ formatDate);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch ( view.getId() )  {
                    case R.id.fore_start:
                        startService();
                        Toast.makeText(getApplicationContext(), "서비스 시작", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.fore_stop:
                        Toast.makeText(getApplicationContext(), "서비스 중지", Toast.LENGTH_SHORT).show();
                        stopService();
                        break;
                }
            }
        };
        start.setOnClickListener(cl);
        stop.setOnClickListener(cl);

        }
        // 어플 종료 후에도 실행되도록 startForegroundService 이용
        // 어플 종류 후에도 실행 원치 않으면 startService 이용
        // 알림 서비스 실행
    public void startService() {
        serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }

        // 알림 서비스 중지, 바인딩만 구현하고 싶을 때는 이 메소드 구현 x
    public void stopService() {
        serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
    }
}