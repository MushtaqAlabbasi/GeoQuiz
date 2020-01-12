package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    Button yemenBTN, usaBTN, egyptBTN, sqBTN;
    RadioButton maleBTN, femaleBTN;
    EditText nameED;

    String currentCategory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        yemenBTN = findViewById(R.id.yemen_btn);
        egyptBTN = findViewById(R.id.egypt_btn);
        usaBTN = findViewById(R.id.usa_btn);
        sqBTN = findViewById(R.id.sq_btn);
        maleBTN = findViewById(R.id.radioMale);
        femaleBTN = findViewById(R.id.radioFemale);
        nameED = findViewById(R.id.name_ed);


        yemenBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCategory = "yemen";
                yemenBTN.setBackgroundResource(R.drawable.diabled_rounded_button);
                egyptBTN.setBackgroundResource(R.drawable.rounded_button);
                usaBTN.setBackgroundResource(R.drawable.rounded_button);
            }
        });

        egyptBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCategory = "egypt";
                egyptBTN.setBackgroundResource(R.drawable.diabled_rounded_button);
                usaBTN.setBackgroundResource(R.drawable.rounded_button);
                yemenBTN.setBackgroundResource(R.drawable.rounded_button);
            }
        });

        usaBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentCategory = "usa";
                usaBTN.setBackgroundResource(R.drawable.diabled_rounded_button);
                egyptBTN.setBackgroundResource(R.drawable.rounded_button);
                yemenBTN.setBackgroundResource(R.drawable.rounded_button);
            }
        });

        sqBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = nameED.getText().toString();

                if (currentCategory==null)
                    Toast.makeText(Home.this, " choose category ", Toast.LENGTH_SHORT).show();

                else if (isMyEditTextEmpty(nameED))
                    Toast.makeText(Home.this, " fill your name", Toast.LENGTH_SHORT).show();

                else {

                    final Intent i = new Intent(Home.this, QuestionActivity.class);
                    i.putExtra("cat", currentCategory);
                    i.putExtra("name", name);

                    if (maleBTN.isChecked())
                        i.putExtra("gender", "male");
                    else
                        i.putExtra("gender", "female");

                    startActivity(i);
                }

            }
        });


    }

    private boolean isMyEditTextEmpty(EditText myeditText) {
        return myeditText.getText().toString().trim().length() == 0;
    }

}//
