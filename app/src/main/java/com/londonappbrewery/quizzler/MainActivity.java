package com.londonappbrewery.quizzler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {




    // TODO: Declare member variables here:

    Button mButtonTrue ,mButtonFalse;
    TextView mQuestionTextView;
    int mIndex;
    int mQuestion;
    boolean mCorrect;
    TextView mScore;
    ProgressBar mProgressBar;
    int Score;
   // AlertDialog.Builder mAlertDialog;




    // TODO: Uncomment to create question bank
    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse(R.string.question_1, true),
            new TrueFalse(R.string.question_2, true),
            new TrueFalse(R.string.question_3, true),
            new TrueFalse(R.string.question_4, true),
            new TrueFalse(R.string.question_5, true),
            new TrueFalse(R.string.question_6, false),
            new TrueFalse(R.string.question_7, true),
            new TrueFalse(R.string.question_8, false),
            new TrueFalse(R.string.question_9, true),
            new TrueFalse(R.string.question_10, true),
            new TrueFalse(R.string.question_11, false),
            new TrueFalse(R.string.question_12, false),
            new TrueFalse(R.string.question_13,true)
    };
    // TODO: Declare constants here
    int mPROGRESS_BAR = (int) Math.ceil(100.0 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null){
            Score = savedInstanceState.getInt("ScoreKey");
            mIndex = savedInstanceState.getInt("IndexKey");


        }
        else {
            Score = 0;
            mIndex = 0;
        }
        mButtonTrue = findViewById(R.id.true_button);
        mButtonFalse = findViewById(R.id.false_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mScore = findViewById(R.id.score);
        mProgressBar = findViewById(R.id.progress_bar);

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        mScore.setText("Score "+Score +"/"+mQuestionBank.length );








        final View.OnClickListener myListner = new View.OnClickListener() { //Obj refrence creating OnClickListener()
            @Override
            public void onClick(View v) {
                Log.d("test","Click");
                Toast.makeText(getApplicationContext(),"TRUE PRESSED!!!",Toast.LENGTH_SHORT).show();// Toast msg Anonymous creating
                checkAnswer(true);
                UpdateQuestion();
            }
        };
        mButtonTrue.setOnClickListener(myListner); // using the obj for button response

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // Anonymous creating OnclickListener()
                Toast myToast = Toast.makeText(getApplicationContext(),"FALSE PRESSED!!!",Toast.LENGTH_SHORT); //Toast msg obj reference
                myToast.show();
                checkAnswer(false);
                UpdateQuestion();
            }
        });
    }
    private void UpdateQuestion(){
        mIndex = (mIndex +1 ) % mQuestionBank.length;
        Log.d("index"," "+mIndex);

    mQuestion  = mQuestionBank[mIndex].getQuestionID();
    mQuestionTextView.setText(mQuestion);
    mProgressBar.incrementProgressBy(mPROGRESS_BAR);

     mScore.setText("Score "+Score +"/"+mQuestionBank.length );
        if (mIndex == 0){
            AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(this);
//            mAlertDialog.create() ;
//            mAlertDialog.getContext();
            mAlertDialog.setCancelable(false);
            mAlertDialog.setTitle("GAME OVER");
            mAlertDialog.setMessage("Your Score "+Score+" points");
            mAlertDialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                   finish();
                }
            });
            mAlertDialog.show();

        }
    }
    public void checkAnswer(boolean pUserSelect){
        mCorrect = mQuestionBank[mIndex].isAnswer();

        if (mCorrect == pUserSelect){
            Toast.makeText(getApplicationContext(),R.string.correct_toast,Toast.LENGTH_SHORT).show();
            Score = (Score+1) % mQuestionBank.length;
        }
        else {
            Toast.makeText(getApplicationContext(),R.string.incorrect_toast,Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("ScoreKey",Score);
        outState.putInt("IndexKey",mIndex);
      //  outState.
    }


}
