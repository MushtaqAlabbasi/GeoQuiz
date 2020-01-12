package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView tScore, fScore;
    ImageView fStar, sStar, thStar;
//    Button backBTN;

//    Changing the actions of the Back button
//    when Override onKeyDown() of Activity, here you can handle various keys or can Override
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event)  {
//        if (keyCode == KeyEvent.KEYCODE_BACK ) {
//            Intent intent = new Intent(Result.this, Home.class);
//            startActivity(intent);
//            return true;
//        }
//
//        return super.onKeyDown(keyCode, event);
//    }



//   or you Use the onBackPressed() function to override the action of the back button.(changing the actions of the Back button)
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Result.this, Home.class);
            startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        tScore = findViewById(R.id.t_score);
        fScore = findViewById(R.id.f_score);

        fStar = findViewById(R.id.fist_star);
        fStar.setVisibility(View.GONE);
        sStar = findViewById(R.id.second_star);
        sStar.setVisibility(View.GONE);
        thStar = findViewById(R.id.third_star);
        thStar.setVisibility(View.GONE);

//        backBTN=findViewById(R.id.back_BTN);

        tScore.setText(getIntent().getStringExtra("true_total"));
        fScore.setText(getIntent().getStringExtra("false_total"));

        int starsNum = getIntent().getIntExtra("numStars", 0);
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.clapping);

        if (starsNum == 3) {
            fStar.setVisibility(View.VISIBLE);
            sStar.setVisibility(View.VISIBLE);
            thStar.setVisibility(View.VISIBLE);
            mediaPlayer.start();

        } else if (starsNum == 2) {
            fStar.setVisibility(View.VISIBLE);
            sStar.setVisibility(View.VISIBLE);
        } else if (starsNum == 1) {
            fStar.setVisibility(View.VISIBLE);
        }


//        backBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Result.this, Home.class);
//                startActivity(intent);
//            }
//        });






    }
}
