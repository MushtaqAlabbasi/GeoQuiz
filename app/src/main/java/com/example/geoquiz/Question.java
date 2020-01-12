package com.example.geoquiz;


import java.io.Serializable;

public class Question implements Serializable {

    private int mTextResId;
    private boolean mAnswerTrue;
    private String mQuestoinCategory;
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Question(int textResId, boolean answerTrue, String mQuestoinCategory,String level){
        this.mTextResId = textResId;
        this.mAnswerTrue = answerTrue;
        this.mQuestoinCategory=mQuestoinCategory;
        this.level=level;
    }

    public String getmQuestoinCategory() {
        return mQuestoinCategory;
    }

    public void setmQuestoinCategory(String mQuestoinCategory) {
        this.mQuestoinCategory = mQuestoinCategory;
    }

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }
}