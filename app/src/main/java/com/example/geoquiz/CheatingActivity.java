package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CheatingActivity extends AppCompatActivity {

    TextView cheatinView;

//    private static final String EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheating);

        cheatinView=findViewById(R.id.cheating_view);
        Intent intent = getIntent();
        String answer = intent.getStringExtra("ansewrIs");
        cheatinView.setText(answer);




    }


//    public static Intent newIntent(Context packageContext, boolean answerIsTrue) {
//        Intent intent = new Intent(packageContext, CheatingActivity.class);
//        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
//        return intent;
//    }
}
