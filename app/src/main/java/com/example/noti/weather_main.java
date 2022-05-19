package com.example.noti;

/*
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class weather_main extends AppCompatActivity {

    private LocationManager lm;
    private ShortWeather sw;
    private MAgencyViewModel mavm;
    private final String TAG = "SplashActivity";
    private LocationManager lm;
    private Coord coord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mavm = new ViewModelProvider(this).get(MAgencyViewModel.class);

        coord = new Coord();

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // 위치정보 권한 허용되어 있는지 확인
        if (ActivityCompat.checkSelfPermission(weather_main.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 허용 x 실행,
            ActivityCompat.requestPermissions(weather_main.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200); // 위침정보 권한 요청
        } else { // 허용 o
            Log.d(TAG, "위치정보 허용됨");
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
            getWeather(mavm);
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 200) {
            if (grantResults[0] == 0) {
                Toast.makeText(getApplicationContext(), "위치정보 승인 완료", Toast.LENGTH_SHORT).show();

                //위치정보 권한 받으면 진입
                if (ActivityCompat.checkSelfPermission(weather_main.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this); // 위치정보 업데이트 함수
                }
                getWeather(mavm);
            } else { // 위치정보 허가 x 경우
                Toast.makeText(getApplicationContext(), "위치가 확인되지 않습니다. ", Toast.LENGTH_SHORT).show();
                getWeather(mavm); // 날씨정보 api 호출
            }
        }
    }

    public void getWeather(MAgencyViewModel mavm) {
        mavm.init();
        mavm.getWeather().observe(this, new Observer<ShortWeather>() {
            @Override
            public void onChanged(ShortWeather shortWeather) {
                sw = mavm.getWeather().getValue();
                Log.i(TAG, sw.toString());

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("ShortWeather", sw);
                        startActivity(intent);
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        coord.setLat(location.getLatitude()); // 위도
        coord.setLon(location.getLongitude()); // 경도
        Log.d(TAG, coord.toString());
        // 한 번 업데이트하고 나서 위치를 업데이트 다시 안시키기 위해서 하는 것
        lm.removeUpdates((LocationListener) this);
    }

    @Override
    public void onStatusChange(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnable(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider){

    }


    }

 */
