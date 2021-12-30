package com.example.Kontrolwork;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controlwork.R;

public class ResultActivity extends AppCompatActivity {
    TextView statusView;
    ProgressBar indicatorBar;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        result = getIntent().getExtras().getString(Codes.INTENT_KEY);
        statusView =(TextView) findViewById(R.id.textView12);
        indicatorBar = findViewById(R.id.progressBar);

        switch (result) {
            case Codes.GOOD_RESULT:
                statusView.setText(Codes.GOOD_RESULT);
                indicatorBar.setProgress(100);
                break;
            case Codes.MEDIUM_RESULT:
                statusView.setText(Codes.MEDIUM_RESULT);
                indicatorBar.setProgress(70);
                break;
            case Codes.BAD_RESULT:
                statusView.setText(Codes.BAD_RESULT);
                indicatorBar.setProgress(30);
                break;
            default:
                statusView.setText("Можно говорить либо о переутомлении, либо о заболевании сердечно-сосудистой системы или других проблемах со здоровьем.");
                indicatorBar.setProgress(0);
                break;
        }
    }
}
