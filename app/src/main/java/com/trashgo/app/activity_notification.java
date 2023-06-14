package com.trashgo.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

// sohenney activity_notification.java 추가
public class activity_notification extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificationsettings);

        /* 나중에 서버 구현하면 알림 띄우기 하면 됨
        // 알림 채널 정의
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Channel";
            String description = "My Channel Description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("my_channel_id", name, importance);
            channel.setDescription(description);

            // 알림 채널 등록
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

        // 서버와의 통신 구현
        // ...

        // 서버에서 알림을 띄우는 신호를 받으면
        // ...

        // 알림 생성
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "my_channel_id")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("Trash Go")
                .setContentText("Let's go to clean up our Earth!")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        // 알림 표시
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    */


        Switch soundSwitch = (Switch) findViewById(R.id.btnsound);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 소리 알림 활성화
                    AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                } else {
                    // 소리 알림 비활성화
                    AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                    audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                }
            }
        });

        Switch vibrationSwitch = (Switch) findViewById(R.id.btnbrrr);
        vibrationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 진동 알림 활성화
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibrator.hasVibrator()) {
                        long[] pattern = {1000};
                        vibrator.vibrate(pattern, -1);
                    }
                } else {
                    // 진동 알림 비활성화
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibrator.hasVibrator()) {
                        vibrator.cancel();
                    }
                }
            }
        });

        Spinner ringtoneSpinner = (Spinner) findViewById(R.id.ringtone_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.ringtones_array , android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ringtoneSpinner.setAdapter(adapter);
        ringtoneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean firstTime = true;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRingtone = parent.getItemAtPosition(position).toString();
                // 선택한 알림음으로 변경
                Uri ringtoneUri;
                switch (selectedRingtone) {
                    case "2023 알림음":
                        ringtoneUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.usualnotification);
                        break;
                    case "차임벨":
                        ringtoneUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bellnotification);
                        break;
                    case "페이스북 알림음":
                        ringtoneUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.facebooknotification);
                        break;
                    case "아이폰 알림음":
                        ringtoneUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.iphonenotification);
                        break;
                    case "뿅뿅 알림음":
                        ringtoneUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.popnotification);
                        break;
                    default:
                        ringtoneUri = Settings.System.DEFAULT_NOTIFICATION_URI;
                        break;
                }
                RingtoneManager.setActualDefaultRingtoneUri(getApplicationContext(), RingtoneManager.TYPE_NOTIFICATION, ringtoneUri);

                // 미리 들려주기 기능 추가
                if (!firstTime) {
                    Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), ringtoneUri);
                    ringtone.play();
                } else {
                    firstTime = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Spinner vibrationPatternSpinner = (Spinner) findViewById(R.id.vibration_pattern_spinner);
        ArrayAdapter<CharSequence> vibrationPatternAdapter = ArrayAdapter.createFromResource(this, R.array.vibration_patterns_array, android.R.layout.simple_spinner_item);
        vibrationPatternAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vibrationPatternSpinner.setAdapter(vibrationPatternAdapter);

        vibrationPatternSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean firstTime = true;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedVibrationPattern = parent.getItemAtPosition(position).toString();
                // 선택한 진동 패턴으로 변경
                long[] pattern;
                switch (selectedVibrationPattern) {
                    case "패턴 1":
                        pattern = new long[]{0, 500};
                        break;
                    case "패턴 2":
                        pattern = new long[]{0, 1000};
                        break;
                    case "패턴 3":
                        pattern = new long[]{0, 1500};
                        break;
                    default:
                        pattern = new long[]{0, 1000};
                        break;
                }

                if (!firstTime) {
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    if (vibrator.hasVibrator()) {
                        vibrator.vibrate(pattern, -1);
                    }
                } else {
                    firstTime = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
