package com.example.notify;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int notificationId=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.setbtn).setOnClickListener(this);
        findViewById(R.id.cancelbtn).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        EditText edittext=findViewById(R.id.edittext);
        TimePicker timepicker=findViewById(R.id.timepicker);

    Intent intent=new Intent(MainActivity.this,AlarmReceiver.class);
    intent.putExtra("notificationId",notificationId);
    intent.putExtra("todo",edittext.getText().toString());

    PendingIntent alarmIntent= PendingIntent.getBroadcast(MainActivity.this,0,intent ,PendingIntent.FLAG_CANCEL_CURRENT);
    AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
    switch(view.getId()){
        case R.id.setbtn:
            int hour=timepicker.getCurrentHour();
            int minute=timepicker.getCurrentMinute();
            Calendar startTime= Calendar.getInstance();
            startTime.set(Calendar.HOUR_OF_DAY,hour);
            startTime.set(Calendar.MINUTE,minute);
            startTime.set(Calendar.SECOND,0);
            long alarmStartTime=startTime.getTimeInMillis();
            alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

            Toast.makeText(this,"DONE!",Toast.LENGTH_SHORT).show();
            break;

        case R.id.cancelbtn:
            alarm.cancel(alarmIntent);
            Toast.makeText(this,"CANCELLED!",Toast.LENGTH_SHORT).show();
            break;





    }

    }
}
