package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView statusView;
    ProgressBar indicatorBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tx = (TextView) findViewById(R.id.textView13);

        Bundle b = this.getIntent().getExtras();
        String[] SendValues =b.getStringArray("values");

         statusView =(TextView) findViewById(R.id.textView12);
         indicatorBar = findViewById(R.id.progressBar);

         int result = Math.abs(Integer.parseInt(SendValues[0]) - Integer.parseInt(SendValues[1]));
         int rez_indicator = (int)(100- (double) result /25*100);
         indicatorBar.setProgress(100);
        if (result<12 && result>=0){
            statusView.setText("Введенные значения соответствуют отсутствию переутомления.");
            indicatorBar.setProgress(100);}
        else  if (result<18 && result>13){
            statusView.setText("Введенные значения соответствуют небольшому переутомления. Рекомендуется снижение нагрузки.");
            indicatorBar.setProgress(rez_indicator);}
        else  if (result<25 && result>18){
            statusView.setText("Введенные значения соответствуют высокому уровню переутомления. Рекомендуется снижение нагрузки или отпуск.");
            indicatorBar.setProgress(rez_indicator);}
        else {
            statusView.setText("Можно говорить либо о переутомлении, либо о заболевании сердечно-сосудистой системы или других проблемах со здоровьем.");
            indicatorBar.setProgress(0);}
    }
}