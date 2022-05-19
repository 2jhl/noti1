package com.example.noti;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

// 서비스 구현 부분
// 서비스 안에서 반복작업을 할 수 있는 스레드 구현

public class MyService extends Service {
    BackgroundTask task;

    int value = 0;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // bindService() 메소드를 통해 서비스를 바인딩할 때 호출
        // 구현 시 클라이언트가 이 서비스와 커뮤니케이션할 때 사용할 수 있는 인터페이스인 IBinder 객체 반환, or null 반환
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 반복작업 부분
        // mainactivity에서 startService() 메소드를 통해 서비스를 시작할 때 callback 호출

        task = new BackgroundTask();
        task.execute();

        initializeNotification(); // 포그라운드 생성
        return START_NOT_STICKY; // 서비스가 죽어도 시스템에서 재생성 x
        //return super.onStartCommand(intent, flags, startId); //기존 코드
    }

    // 포그라운드 서비스
    public void initializeNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1");
        builder.setSmallIcon(R.drawable.sunny);
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.bigText("앱으로 이동하려면 누르세요"); // 내용 표시
        style.setBigContentTitle(null);
        style.setSummaryText("서비스 동작 중"); // 패키지 이름 옆에 조금 큰 텍스트뷰
        builder.setContentText("포그라운드 서비스 정상");
        builder.setContentTitle("날씨정원");
        builder.setOngoing(true);
        builder.setStyle(style);
        builder.setWhen(0);
        builder.setShowWhen(false);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("1", "포그라운드 서비스", NotificationManager.IMPORTANCE_NONE));
        }
        Notification notification = builder.build();
        startForeground(1, notification);
    }

    class BackgroundTask extends AsyncTask<Integer, String, Integer> {

        String result = "";

        @Override
        protected Integer doInBackground(Integer... integers) {

            while ( isCancelled() == false ) {
                try {
                    println(value + "번째 실행 중");
                    Thread.sleep(1000);
                    value++;
                } catch (InterruptedException e) {}
            }
            return value;
        }
        // 상태 확인
        @Override
        protected void onProgressUpdate(String... values) {
            println("onProgressUpdate()업데이트");
        }

        @Override
        protected void onPostExecute(Integer integer) {
            println("onPostExecute()");
            value = 0;
        }

        @Override
        protected void onCancelled() {
            value = 0; // 정지로 초기화
        }
    }

    // 서비스 종료
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyService", "onDestory");

        task.cancel(true);
    }

    public void println(String message) {
        Log.d("MyService", message);
    }
}