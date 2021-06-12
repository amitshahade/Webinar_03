package com.example.assignment_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    TextView sethour,setmin,setsec,setcol1,setcol2;
    Button pause,start;
    EditText etmin,etsec;
    int min=0,sec=0,second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sethour = findViewById(R.id.sethour);
        setmin = findViewById(R.id.setmin);
        setsec = findViewById(R.id.setsec);

        setcol1 = findViewById(R.id.setcol1);
        setcol2 = findViewById(R.id.setcol2);

        pause = findViewById(R.id.pause);
        start = findViewById(R.id.start);
        etmin = findViewById(R.id.etmin);
        etsec = findViewById(R.id.etsec);




        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                min = Integer.parseInt(etmin.getText().toString());
                sec = Integer.parseInt(etsec.getText().toString());
                System.out.println(min);
                System.out.println(sec);

                if(min>60 || sec>60)
                {
                    Toast.makeText(MainActivity.this, "minutes and seconds should be between 0 & 60", Toast.LENGTH_SHORT).show();
                    etmin.setText(null);
                    etsec.setText(null);
                }
                else
                {
                    System.out.println("else part worked");
                    second=((min*60)+sec)*1000;
                    CountTask countTask = new CountTask();
                    countTask.execute(second);
                    System.out.println("last part done");


                }
            }
        });

    }

    class CountTask extends AsyncTask<Integer ,Integer,Void>
    {



        @Override
        protected Void doInBackground(Integer... integers)
        {
            sec=integers[0];
            new CountDownTimer(sec, 1000) {
                public void onTick(long millisUntilFinished) {
                    System.out.println("sdvdsvv"+sec);
                    // Used for formatting digit to be in 2 digits only
                    // NumberFormat f = new DecimalFormat("00");
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;

                    int h = (int) hour;
                    int m = (int) min;
                    int s = (int) sec;
                    System.out.println(h);
                    System.out.println(m);
                    System.out.println(s);

                    for(int i=0;i<sec;i++)
                    {
                        publishProgress(s);
                    }
                }
                // When the task is over it will print 00:00:00 there
                public void onFinish()
                {
                    sethour.setText("finished");
                    setmin.setVisibility(View.GONE);
                    setsec.setVisibility(View.GONE);
                    setcol1.setVisibility(View.GONE);
                    setcol2.setVisibility(View.GONE);
                }
            }.start();
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


            boolean a,b,c;
//            sethour.setText(String.valueOf(h));
//            setmin.setText(String.valueOf(m));
//            setsec.setText(String.valueOf(s));
            System.out.println(values[0]);
            if(a=false)
            {
                sethour.setText(String.valueOf(values[0]));
                a=true;
            }

            if(b=false)
            {
                setmin.setText(String.valueOf(values[0]));
                b=true;
            }

            if(c=false)
            {
                setsec.setText(String.valueOf(values[0]));
                c=true;
            }

        }
    }


}