package com.londonappbrewery.quizzler;

public class TrueFalse {
    private int mQuestionID;
    private boolean mAnswer;

    public TrueFalse(int pQuestionID,boolean pAnswer){
        this.mQuestionID = pQuestionID;
        this.mAnswer = pAnswer;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int pQuestionID) {
        mQuestionID = pQuestionID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean pAnswer) {
        mAnswer = pAnswer;
    }
}
