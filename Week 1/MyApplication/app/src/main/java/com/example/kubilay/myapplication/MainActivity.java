package com.example.kubilay.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference button
        Button kubiButton1 = (Button)findViewById(R.id.kubiButton1);
        Button kubiButton2 = (Button)findViewById(R.id.kubiButton2);

        //Listeners for first button
        kubiButton1.setOnClickListener(
                //Interface
                new Button.OnClickListener(){
                    //Callback method
                    public void onClick(View v){
                        TextView kubiText1 = (TextView)findViewById(R.id.kubiText1);
                        kubiText1.setText("Good job!");
                    }
                }
        );

        kubiButton1.setOnLongClickListener(
                //Interface
                new Button.OnLongClickListener(){
                    //Callback method
                    public boolean onLongClick(View v){
                        TextView kubiText1 = (TextView)findViewById(R.id.kubiText1);
                        kubiText1.setText("Long click! I noticed!");
                        return true;
                    }
                }
        );

        //Listeners for second button that changes colour
        kubiButton2.setOnClickListener(
                //Interface
                new Button.OnClickListener(){
                    //Callback method
                    public void onClick(View v){
                        TextView kubiText1 = (TextView)findViewById(R.id.kubiText1);
                        kubiText1.setTextColor(0xFF00FF00);

                    }
                }
        );

        kubiButton2.setOnLongClickListener(
                //Interface
                new Button.OnLongClickListener(){
                    //Callback method
                    public boolean onLongClick(View v){
                        TextView kubiText1 = (TextView)findViewById(R.id.kubiText1);
                        kubiText1.setTextColor(0xff0000ff);
                        return true;
                    }
                }
        );
    }
}
