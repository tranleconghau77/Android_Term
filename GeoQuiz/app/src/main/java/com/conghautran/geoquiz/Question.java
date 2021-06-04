package com.conghautran.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerResId() {
        return mAnswerTrue;
    }

    public void setAnswerResId(boolean answerResId) {
        mAnswerTrue = answerResId;
    }

    public Question(int textResId, boolean answerTrue){
        mTextResId=textResId;
        mAnswerTrue=answerTrue;
    }


    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }
}
