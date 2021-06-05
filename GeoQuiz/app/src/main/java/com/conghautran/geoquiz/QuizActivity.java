package com.conghautran.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String CHEATER="cheater";
    private static final String KEY_INDEX = "index";
    private static final String QUESTION_CHEATED = "cheated";
    private static final int REQUEST_CODE_CHEAT=0;

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mBackButton;
    private Button mCheatButton;
    private TextView mQuestionTextView;
    private int mCurrentIndex=0;
    private boolean mIsCheater;

    private Question[] mQuestionBank=new Question[]{
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_africa,false),
            new Question(R.string.question_americas,true),
            new Question(R.string.question_asia,true),

    };
    private int[] mCheatingBank=new int[mQuestionBank.length];

    private void updateQuestion(){
        int question=mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode==REQUEST_CODE_CHEAT){
            if(data == null){
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
        if(mIsCheater){
            mCheatingBank[mCurrentIndex]=1;
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX,mCurrentIndex);
        outState.putBoolean(CHEATER,mIsCheater);
        outState.putIntArray((QUESTION_CHEATED),mCheatingBank);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerResId();
        int messageResId=0;
        if(mIsCheater){
            messageResId = R.string.judgement_toast;
        }

        else{
            if(userPressedTrue==answerIsTrue){
                if(mCheatingBank[mCurrentIndex]!=1){
                    messageResId=R.string.correct_toast;
                }
                else{
                    messageResId=R.string.judgement_toast;
                }
            }
            else{
                if(mCheatingBank[mCurrentIndex]!=1){
                    messageResId=R.string.incorrect_toast;
                }
                else{
                    messageResId=R.string.judgement_toast;
                }
            }
        }
        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate(Bundle) Called");
        setContentView(R.layout.activity_quiz);


        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        mTrueButton =(Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               checkAnswer(true);
            }
        });

        mFalseButton=(Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);

            }
        });
        mNextButton=(ImageButton) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=(mCurrentIndex+1)%mQuestionBank.length;
                mIsCheater=false;
                updateQuestion();
            }
        });


        mBackButton=(ImageButton) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCurrentIndex-1<=0)
                {
                    mCurrentIndex=mQuestionBank.length;

                }
                mCurrentIndex=(mCurrentIndex-1)%mQuestionBank.length;
                updateQuestion();
            }
        });
        updateQuestion();

        mCheatButton=(Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean answerIsTrue=mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent intent =CheatActivity.newIntent(QuizActivity.this,answerIsTrue);
                startActivityForResult(intent,REQUEST_CODE_CHEAT);

            }
        });
        if(savedInstanceState != null)
        {
            mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
            mIsCheater=savedInstanceState.getBoolean(CHEATER,false);
            mCheatingBank= savedInstanceState.getIntArray((QUESTION_CHEATED));
        }
        updateQuestion();


    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

}