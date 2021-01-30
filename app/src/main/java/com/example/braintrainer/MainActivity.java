package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int TOTAL_TIME = 10100;
    ArrayList<Integer> answerOptions = new ArrayList<>();
    int locationOfCorrectAnswer;
    int totalQuestions = 0;
    int totalCorrectAnswers = 0;
    TextView scoreText;
    TextView problemText;
    TextView timerText;
    TextView resultText;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    Button goButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        totalQuestions = 0;
        totalCorrectAnswers = 0;
        scoreText.setText("0/0");
        newQuestion();
        startTimer();
    }

    private void startTimer() {
        new CountDownTimer(TOTAL_TIME, 1000) {

            @Override
            public void onTick(long l) {
                timerText.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultText.setText("Done!!");
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void submitAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag())){
            resultText.setText("Correct Answer!!");
            totalCorrectAnswers += 1;
        }else{
            resultText.setText("Wrong Answer!!!");
        }
        scoreText.setText(Integer.toString(totalCorrectAnswers) + "/" + Integer.toString(totalQuestions));
        newQuestion();
    }

    public void showGame(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        startGame();
    }


    public void newQuestion(){
        Random random = new Random();

        int a = random.nextInt(31);
        int b = random.nextInt(31);

        problemText.setText(Integer.toString(a) + " + " + Integer.toString(b));
        answerOptions.clear();
        locationOfCorrectAnswer = random.nextInt(4);
        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                answerOptions.add(a+b);
            }else{
                int wrongAnswer = random.nextInt(61);
                while(wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(61);
                }
                answerOptions.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answerOptions.get(0)));
        button1.setText(Integer.toString(answerOptions.get(1)));
        button2.setText(Integer.toString(answerOptions.get(2)));
        button3.setText(Integer.toString(answerOptions.get(3)));
        totalQuestions += 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button) findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout = (ConstraintLayout) findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);
    }

    private void startGame() {
        problemText = (TextView) findViewById(R.id.problemText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        resultText = (TextView) findViewById(R.id.resultText);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        newQuestion();

        scoreText.setText(Integer.toString(totalCorrectAnswers) + "/" + Integer.toString(totalQuestions));
        timerText = (TextView) findViewById(R.id.timerText);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        startTimer();
    }
}