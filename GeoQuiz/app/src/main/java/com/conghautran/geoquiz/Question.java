package com.conghautran.geoquiz;

public class Question {
    private int mTextResId;
    private boolean mAnswerResId;

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerResId() {
        return mAnswerResId;
    }

    public void setAnswerResId(boolean answerResId) {
        mAnswerResId = answerResId;
    }

    public Question(int textResId, boolean answerTrue){
        mTextResId=textResId;
        mAnswerResId=answerTrue;
    }

}
