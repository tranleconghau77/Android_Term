package com.conghautran.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    private static final String EXTRA_ANSWER_IS_TRUE=
            "com.conghautran.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN=
            "com.conghautran.geoquiz.answer_is_true";
    private static final String ANSWER="cheated_answer";
    private static final String CHEATER="cheater";
    private static final String NAME="CheatActivity";
    private boolean mAnswerIsTrue;
    private boolean mHasCheated;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue){
        Intent intent = new Intent(packageContext,CheatActivity.class);
        intent.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return intent;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN,false);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean(CHEATER,mHasCheated);
        savedInstanceState.putBoolean(ANSWER,mAnswerIsTrue);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
        mAnswerTextView=findViewById(R.id.answer_text_view);

        mShowAnswer= findViewById(R.id.show_answer_button);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }
                else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShownResult(true);
                mHasCheated=true;
                mShowAnswer.setVisibility(View.INVISIBLE);
            }
            
        });
        if(savedInstanceState !=null){
            mAnswerIsTrue =savedInstanceState.getBoolean(ANSWER,false);
            mHasCheated=savedInstanceState.getBoolean(CHEATER,false);
            if(mHasCheated){
                setAnswerShownResult(true);
                if(mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                }else{
                    mAnswerTextView.setText(R.string.false_button);
                }
            }

        }
    }
    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);
    }
}