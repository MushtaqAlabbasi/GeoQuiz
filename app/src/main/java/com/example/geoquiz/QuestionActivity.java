package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;


public class QuestionActivity extends AppCompatActivity {

    Button tBTN, fBTN, nBTN, pBTN, yemenBTN, egyptBTN, usaBTN, cheatingBTN;
    TextView qv, tScore, fScore, nameTv;
    CircleImageView imgV;
    String gender;
    LinearLayout hiddenLayout;

//
//    private Question[] gQuestionBank = new Question[] {
//            new Question(R.string.egypt_question, true),
//            new Question(R.string.moscow_question, false),
//            new Question(R.string.usa_question, true),
//
//    };
//
//    private Question[] hQuestionBank = new Question[] {
//            new Question(R.string.h_egypt_question, true),
//            new Question(R.string.h_usa_question, true),
//    };
//
//
//    private Question[] oQuestionBank = new Question[] {
//            new Question(R.string.o_question1, true),
//            new Question(R.string.o_question2, true),
//            new Question(R.string.o_question3, false),
//    };


    Question[] mQuestionBank = new Question[]{
            new Question(R.string.yemen_question1_1_1, true, "yemen", "easy"),
            new Question(R.string.yemen_question1_1_2, true, "yemen", "easy"),
            new Question(R.string.yemen_question1_1_3, false, "yemen", "easy"),

            new Question(R.string.yemen_question1_2_1, false, "yemen", "medium"),
            new Question(R.string.yemen_question1_2_2, false, "yemen", "medium"),
            new Question(R.string.yemen_question1_2_3, true, "yemen", "medium"),

            new Question(R.string.yemen_question1_3_1, false, "yemen", "difficult"),
            new Question(R.string.yemen_question1_3_2, true, "yemen", "difficult"),
            new Question(R.string.yemen_question1_3_3, true, "yemen", "difficult"),

            new Question(R.string.usa_question1_1_1, true, "usa","easy"),
            new Question(R.string.usa_question1_1_2, true, "usa","easy"),
            new Question(R.string.usa_question1_1_3, true, "usa","easy"),

            new Question(R.string.usa_question1_2_1, false, "usa","medium"),
            new Question(R.string.usa_question1_2_2, false, "usa","medium"),
            new Question(R.string.usa_question1_2_3, true, "usa","medium"),

            new Question(R.string.usa_question1_3_1, true, "usa","difficult"),
            new Question(R.string.usa_question1_3_2, true, "usa","difficult"),
            new Question(R.string.usa_question1_3_3, false, "usa","difficult"),


            new Question(R.string.egypt_question1_1_1, true, "egypt","easy"),
            new Question(R.string.egypt_question1_1_2, true, "egypt","easy"),
            new Question(R.string.egypt_question1_1_3, true, "egypt","easy"),

            new Question(R.string.egypt_question1_2_1, true, "egypt","medium"),
            new Question(R.string.egypt_question1_2_2, true, "egypt","medium"),
            new Question(R.string.egypt_question1_2_3, false, "egypt","medium"),

            new Question(R.string.egypt_question1_3_1, true, "egypt","difficult"),
            new Question(R.string.egypt_question1_3_2, true, "egypt","difficult"),
            new Question(R.string.egypt_question1_3_3, true, "egypt","difficult"),

    };



//            new Question(R.string.egypt_question1, false, "egypt"),
//            new Question(R.string.egypt_question2, false, "egypt"),
//            new Question(R.string.egypt_question3, false, "egypt"),
//            new Question(R.string.egypt_question4, true, "egypt"),
//
//            new Question(R.string.usa_question1, true, "usa"),
//            new Question(R.string.usa_question2, true, "usa"),
//            new Question(R.string.usa_question3, true, "usa"),
//            new Question(R.string.usa_question4, false, "usa"),};


    ArrayList<Question> runTimeArray = new ArrayList<>();
    ArrayList<Question> newList = new ArrayList<>();
//    ArrayList<Integer> ifAnsweredList = new ArrayList<>();


    private int mCurrentIndex = 0;
    private String mCurrentCatgory;
    private int tCounter = 0;
    private int fCounter = 0;
    private int numOfTrueQ = 0;
    private int numOfFalseQ = 0;
    private boolean cheatedQ = false;


//--------------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_question);


        tBTN = findViewById(R.id.t_btn);
        fBTN = findViewById(R.id.f_btn);


        cheatingBTN = findViewById(R.id.cheating);

        qv = findViewById(R.id.question);
        tScore = findViewById(R.id.t_score);
        fScore = findViewById(R.id.f_score);
        nameTv = findViewById(R.id.name_tv);
        imgV = findViewById(R.id.profile_image);

        hiddenLayout = findViewById(R.id.hidden_layout);



        intializing();



        cheatingBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean answer = newList.get(mCurrentIndex).ismAnswerTrue();

                cheatedQ = true;

                if (answer == true)
                    Toast.makeText(QuestionActivity.this, "True", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(QuestionActivity.this, "False", Toast.LENGTH_SHORT).show();

            }
        });


        tBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(true);
                updateScore();

                if (mCurrentIndex != newList.size() - 1) {
                    mCurrentIndex++;
                    updateQuestion();

                } else if (mCurrentIndex == newList.size() - 1) {
                    movingToResultActivity();
                }


            }
        });

        fBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                checkAnswer(false);
                updateScore();


                if (mCurrentIndex != newList.size() - 1) {
                    mCurrentIndex++;
                    updateQuestion();
                } else if (mCurrentIndex == newList.size() - 1) {

                   movingToResultActivity();
                }

            }
        });


        if (savedInstanceState != null) {

            mCurrentIndex = savedInstanceState.getInt("currentIndex");
            mCurrentCatgory = savedInstanceState.getString("currentCatgory");
            tCounter = savedInstanceState.getInt("currentCounter");
            hiddenLayout.setVisibility(savedInstanceState.getInt("hiddenLayout"));

            runTimeArray = (ArrayList<Question>) savedInstanceState.getSerializable("rTA");
//            ifAnsweredList = savedInstanceState.getIntegerArrayList("iAL");

            updateQuestion();
            tScore.setText(tCounter + "");

        }
    }




//----------------------methods-------------------

    private void intializing() {

         gender = getIntent().getStringExtra("gender");

        if (gender.equals("male"))
            imgV.setImageDrawable(getResources().getDrawable(R.drawable.male_img));
        else
            imgV.setImageDrawable(getResources().getDrawable(R.drawable.female_img));

        nameTv.setText("welcome  " + getIntent().getStringExtra("name"));

        fillArray(getIntent().getStringExtra("cat"));

        getRandomElement(runTimeArray);
    }



    void movingToResultActivity(){

        Intent intent = new Intent(QuestionActivity.this, Result.class);

        String tTotal = tScore.getText().toString();
        String fTotal = fScore.getText().toString();
        intent.putExtra("true_total", tTotal);
        intent.putExtra("false_total", fTotal);

        if (numOfTrueQ == newList.size())
            intent.putExtra("numStars", 3);
        else if (numOfTrueQ == 2)
            intent.putExtra("numStars", 2);
        else if (numOfTrueQ == 1)
            intent.putExtra("numStars", 1);

        startActivity(intent);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("currentIndex", mCurrentIndex);
        outState.putString("currentCatgory", mCurrentCatgory);
        outState.putInt("currentCounter", tCounter);
        outState.putSerializable("rTA", runTimeArray);
        outState.putString("gender", gender);
//        outState.putIntegerArrayList("iAL", ifAnsweredList);
        outState.putInt("hiddenLayout", hiddenLayout.getVisibility());

    }


    void fillArray(String qType) {

        runTimeArray.clear();
        mCurrentIndex = 0;
        for (Question q : mQuestionBank) {
            if (q.getmQuestoinCategory().equals(qType)) {
                runTimeArray.add(q);
            }
        }
//        updateQuestion();
    }

    public void getRandomElement(ArrayList<Question> runTimeArray) {
//        int totalItems
        Random rand = new Random();
        Question tempQuestion;
        boolean easyFound,mediumFound,difficultFound;
        easyFound=mediumFound=difficultFound=false;

        for (int i = 0; i < 3; i++) {
            int randomIndex = rand.nextInt(runTimeArray.size() - 1);

            tempQuestion=runTimeArray.get(randomIndex);

            if(! newList.contains(tempQuestion)) {

                if (!easyFound){
                    if (tempQuestion.getLevel().equalsIgnoreCase("easy")) {
                        newList.add(tempQuestion);
                        easyFound=true;
                        continue;
                    }
                }
                else if (!mediumFound){
                    if (tempQuestion.getLevel().equalsIgnoreCase("medium") ) {
                        newList.add(tempQuestion);
                        mediumFound=true;
                        continue;
                    }
                }
                else if (!difficultFound){
                    if (tempQuestion.getLevel().equalsIgnoreCase("difficult") ) {
                        newList.add(tempQuestion);
                        difficultFound=true;
                        continue;
                    }
                }

                i--;
                continue;

            }

            else {
                i--;
                continue;
            }
            }
        updateQuestion();
        }

//            for (Question q : newList) {
//                for (Question rq : runTimeArray) {
//                    if (q.getLevel().equals(rq.getLevel())) ;
//                      runTimeArray.remove(rq);
//                }

//        for (int i = 0; i < runTimeArray.size(); i++) {
//            int randomIndex = rand.nextInt(runTimeArray.size() - 1);
//            if (runTimeArray.get(randomIndex).getLevel().equals("easy")) {
//                for (int c = 0; c < newList.size(); c++) {
//                    if (newList.isEmpty())
//                        newList.add(runTimeArray.get(randomIndex));
//                }
//            }
//        }
//
//        for (int i = 0; i < runTimeArray.size(); i++) {
//            int randomIndex = rand.nextInt(runTimeArray.size() - 1);
//            if (runTimeArray.get(randomIndex).getLevel().equals("medium")) {
//                for (int c = 0; c < newList.size(); c++) {
//                    if (newList.size() == 1)
//                        newList.add(runTimeArray.get(randomIndex));
//                }
//            }
//        }
//
//        for (int i = 0; i < runTimeArray.size(); i++) {
//            int randomIndex = rand.nextInt(runTimeArray.size() - 1);
//            if (runTimeArray.get(randomIndex).getLevel().equals("difficult")) {
//                for (int c = 0; c < newList.size(); c++) {
//                    if (newList.size() == 3)
//                        newList.add(runTimeArray.get(randomIndex));
//                }
//            }
//        }
//


    private void updateQuestion() {
        qv.setText(newList.get(mCurrentIndex).getmTextResId());
//        mCurrentIndex=(mCurrentIndex+1)%runTimeArray.size();
//        qv.setText(runTimeArray.get(mCurrentIndex).getmTextResId());

    }


    private void checkAnswer(boolean userPressedTrue) {

        boolean answerIsTrue = newList.get(mCurrentIndex).ismAnswerTrue();

        int massage = 0;

        if (answerIsTrue == userPressedTrue && cheatedQ == false) {
            massage = R.string.correct;
            numOfTrueQ++;
            if (newList.get(mCurrentIndex).getLevel().equals("easy"))
                tCounter += 1;
            else if (newList.get(mCurrentIndex).getLevel().equals("medium"))
                tCounter += 2;
            else if (newList.get(mCurrentIndex).getLevel().equals("difficult"))
                tCounter += 3;

        } else {
            massage = R.string.incorrect;
            fCounter++;
            numOfFalseQ++;
        }

//        tScore.setText(tCounter + "");

        Toast.makeText(QuestionActivity.this, massage, Toast.LENGTH_SHORT).show();
    }

    private void updateScore() {
        tScore.setText(String.valueOf(tCounter));
        fScore.setText(String.valueOf(fCounter));
    }


}