package com.example.Kontrolwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.controlwork.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    Spinner s_day, s_month, s_year,s_pol;
    TextView textView9;
    EditText editText_puls, editText_puls2;
    Button button_next;
    int day ,month, year , sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> days = new ArrayList<String>();
        for (int i = 1; i <= 31; i++) {
            days.add(Integer.toString(i));
        }

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        String[] Months = new String[] { "January", "February",
                "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };

        String[] Pol = new String[] { "Male", "Female" };

        s_day = (Spinner) findViewById(R.id.spinner_day);
        s_month = (Spinner) findViewById(R.id.spinner_month);
        s_year = (Spinner) findViewById(R.id.spinner_year);
        s_pol = (Spinner) findViewById(R.id.spinner_pol);

        editText_puls = (EditText) findViewById(R.id.editText_puls);
        editText_puls2 = (EditText) findViewById(R.id.editText_puls2);
        textView9 = (TextView) findViewById(R.id.textView9);
        button_next = (Button) findViewById(R.id.button_next);
        button_next.setOnClickListener(this::OnClick);

        ArrayAdapter<String> adapter_day = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, days);
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Months);
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, years);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter_pol = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Pol);
        adapter_pol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s_day.setAdapter(adapter_day);
        s_month.setAdapter(adapter_month);
        s_year.setAdapter(adapter_year);
        s_pol.setAdapter(adapter_pol);
        s_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day =i;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        s_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                month =i;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        s_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        s_pol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sex =i+1;
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }
    public void getResponse() {
        new Thread(() -> {
            try {
                String parameters = "day=" + s_day.getSelectedItem() + "&month=" + 12 + "&year=" + Integer.parseInt(s_year.getSelectedItem().toString()) + "&sex=" + sex + "&m1=" + Integer.parseInt(editText_puls.getText().toString()) + "&m2=" + Integer.parseInt(editText_puls2.getText().toString());
                URL url = new URL(Codes.LINK);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod(Codes.METHOD);
                connection.setRequestProperty(Codes.HOST_KEY, Codes.HOST_VALUE);
                connection.setRequestProperty(Codes.CONNECTION_KEY, Codes.CONNECTION_VALUE);
                connection.setRequestProperty(Codes.CACHE_KEY, Codes.CACHE_VALUE);
                connection.setRequestProperty(Codes.DNT_KEY, Codes.DNT_VALUE);
                connection.setRequestProperty(Codes.UPGRADE_KEY, Codes.UPGRADE_VALUE);
                connection.setRequestProperty(Codes.ACCEPT_KEY, Codes.ACCEPT_VALUE);
                connection.setRequestProperty(Codes.ENCODING_KEY, Codes.ENCODING_VALUE);
                connection.setRequestProperty(Codes.LANGUAGE_KEY, Codes.LANGUAGE_VALUE);
                connection.setRequestProperty(Codes.TYPE_KEY, Codes.TYPE_VALUE);connection.setRequestProperty(Codes.LENGTH_KEY, String.valueOf(parameters.length()));
                connection.setDoInput(Codes.INPUT);
                connection.setDoOutput(Codes.OUTPUT);
                connection.connect();

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(parameters.getBytes(StandardCharsets.UTF_8));
                outputStream.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String data;
                StringBuilder response = new StringBuilder();
                while ((data = bufferedReader.readLine()) != null) {
                    response.append(data);
                }
                bufferedReader.close();

                String parsedResponse = new String(response.toString().getBytes(), StandardCharsets.UTF_8).replaceAll(Codes.RESPONSE_PATTERN, "");
                //textView9.setText(parsedResponse.toString());
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra(Codes.INTENT_KEY, parsedResponse);
                startActivity(intent);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }).start();
    }


    public void OnClick(View v){
        getResponse();

    }


}