package com.trashgo.app;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.trashgo.app.Model.PloggingData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * PloggingActivity 전체- pkdgood
 */
public class PloggingActivity extends AppCompatActivity {

    Chronometer chronometer;
    Button btnStop;
    Location location;
    TextView tv3;
    TextView tvDistance;
    double distance = 0;
    ImageView ivWalking;

    LocationManager lm;
    PloggingData ploggingData;

    Intent intent;

    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plogging);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        distance = 0;

        intent = getIntent();

        chronometer = findViewById(R.id.stopWatch);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();

        btnStop = findViewById(R.id.btnStop);
        btnStop.setOnClickListener(view -> stopPlogging());

        ivWalking = findViewById(R.id.ivWalking);
        Glide.with(this).load(R.raw.walking).into(ivWalking);   // gif위해 Glide 적용

        tv3 = findViewById(R.id.textView3);
        tvDistance = findViewById(R.id.tvDistance);
        tvDistance.setText(0+"km");

        ploggingData = new PloggingData();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        String[] permission = {
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_FINE_LOCATION
        };
        if ((ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PloggingActivity.this, permission, 1);
        }

        // fusedLocationClient 사용
//        fusedLocationClient.getLastLocation()
//                .addOnSuccessListener(this, location -> {
//                    // Got last known location. In some rare situations this can be null.
//                    if (location != null) {
//                        LatLng mLatLng = new LatLng(location.getLatitude(), location.getLongitude());
//                        Log.println(Log.INFO, "위치", mLatLng.toString());
//                    }
//                });


        // LocationManager 사용
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, gpsLocationListener);
//        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, gpsLocationListener);
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            String provider = location.getProvider();
            double longitude = location.getLongitude(); // 위도
            double latitude = location.getLatitude(); // 경도
            double altitude = location.getAltitude(); // 고도

            Log.println(Log.INFO, provider + "위치", longitude + " " + latitude);
            PloggingData.Coordiante coordinate = new PloggingData.Coordiante(latitude, longitude);
            ploggingData.latLngList.add(coordinate);
//            tv3.setText("latitude : " + coordinate.latitude + ", longitude : " + coordinate.logitude );
            distance += 0.00129;
            tvDistance.setText(String.format("%.2f", distance) + "km");
        }
        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    };


    private void stopPlogging() {
        chronometer.stop();
//        saveDataJson();
        saveDataFile();
        intent.putExtra("file",  saveDataJson());
        setResult(RESULT_OK, intent);
        Handler handler = new Handler();
        handler.postDelayed(() -> finish(), 700);
    }

    private void saveDataFile() {
        ObjectMapper mapper = new ObjectMapper();
        try {
//            File dir = new File(Environment.getExternalStorageDirectory(), "PloPlo");
//            if (!dir.exists()) {
//                dir.mkdir();
//            }
            //파일로 저장
            File file = new File(Environment.getExternalStorageDirectory()+"/PloPlo", "test.json");
            //파일이 존재하지 않다면 생성
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            if (!file.exists()) {
                file.createNewFile();
            }

            if(file.canWrite()) {
                mapper.writeValue(file, ploggingData);
            }
            //문자열로 변환
//            String webSiteJsonString = mapper.writeValueAsString(ploggingData);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String saveDataJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String inputData = mapper.writeValueAsString(ploggingData);
            FileOutputStream fos = null;
            String filename ="plogging" + LocalDateTime.now().toLocalDate().toString()+ ".json";
            fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(inputData.getBytes());
            fos.close();
            return filename;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    public String readFile() {
//
//        String fileTitle = "test.json";
//        File file = new File(Environment.getExternalStorageDirectory(), fileTitle);
//
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(file));
//            String result = "";
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                result += line;
//            }
//            reader.close();
//            return result;
//
//        } catch (FileNotFoundException e1) {
//            Log.i("파일못찾음", e1.getMessage());
//        } catch (IOException e2) {
//            Log.i("읽기오류", e2.getMessage());
//        }
//        return "";
//    }
}