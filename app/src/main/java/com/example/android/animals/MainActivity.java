package com.example.android.animals;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.StringReader;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.datatype.Duration;

/**
 * This app is a quiz app for children asking the names of animals.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Global Variables for the app
     */
    int qnumber = 1;
    int noCorrectAnswer;

    {
        noCorrectAnswer = 0;
    }
    String namePlayer;

    {
        namePlayer = "";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * This method makes quiz screen and final screen invisible on the startup of the app
         */

        View startupVsb = (View) findViewById(R.id.quiz_screen);
        startupVsb.setVisibility(View.INVISIBLE);

        View finalVsb = (View) findViewById(R.id.final_screen);
        finalVsb.setVisibility(View.INVISIBLE);


        /**
         * This sends the initial value of qnumber to expecting methods for showing the values correctly on the startup.
         */
        displayQuNo(qnumber);
        ChangeDisplayPic(qnumber);
        animalSounds(qnumber);
    }

    /**
     * This method is to organize the acton of the START button. when clicked
     * checks if the name netered and also hides the opening screen + makes quiz screen visible
     */
    public void startGame(View view) {

        switch (view.getId()) {
            case R.id.start_button:

                /**
                 * This method  gets the player name and checks if filled or not.
                 * sends the name to the textview and to the final screen textview on the bottom of the screen
                 */
//         Get player's name
                EditText nameField = (EditText) findViewById(R.id.name_player);
                Editable nameEditable = nameField.getText();
                namePlayer = nameEditable.toString();
                displayPName(namePlayer);
//        displayFnal(namePlayer,noCorrectAnswer);


                if (namePlayer.matches("")) {
                    Toast.makeText(this, "Please enter the player name", Toast.LENGTH_SHORT).show();
                    return;
                }

                /**
                 * This method  hides the opening screen + makes quiz screen visible
                 */
                View buttonStart = (View) findViewById(R.id.opening_screen);
                buttonStart.setVisibility(View.INVISIBLE);

                View startupVsb = (View) findViewById(R.id.quiz_screen);
                startupVsb.setVisibility(View.VISIBLE);


                break;

            case R.id.start_over_button:

                /**
                 * This method  hides the final and opening screen + makes quiz screen visible
                 * initialise the program
                 */
                buttonStart = (View) findViewById(R.id.opening_screen);
                buttonStart.setVisibility(View.INVISIBLE);

                startupVsb = (View) findViewById(R.id.quiz_screen);
                startupVsb.setVisibility(View.VISIBLE);

                View finalVsb = (View) findViewById(R.id.final_screen);
                finalVsb.setVisibility(View.INVISIBLE);

                qnumber = 1;

                displayQuNo(qnumber);
                ChangeDisplayPic(qnumber);
                animalSounds(qnumber);

                break;
        }




    }

    /**
     * This method hides the opening and quiz screen when reaching question no 8 + makes final screen visible
     */

    private void finalScreen(int questionNo) {

        if(questionNo >=9)
        {

    View openscrnEnd = (View) findViewById(R.id.opening_screen);
        openscrnEnd.setVisibility(View.INVISIBLE);

    View quizscrnEnd = (View) findViewById(R.id.quiz_screen);
        quizscrnEnd.setVisibility(View.INVISIBLE);

    View finalVsb = (View) findViewById(R.id.final_screen);
        finalVsb.setVisibility(View.VISIBLE);

        }


    }



    /**
     * This method is to send email of the final results of the quiz *
     */
    public void sendMail(View view) {


        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Animals Quiz Result For " + namePlayer);
        i.putExtra(Intent.EXTRA_TEXT   , namePlayer+ " Answered " + noCorrectAnswer + " questions correctly" + "\n out of 8 difficult questions");
        try {
        startActivity(Intent.createChooser(i, "Send mail..."));
    } catch (android.content.ActivityNotFoundException ex) {
        Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
    }
    }




    /**
     * This method is called when the next button is clicked.
     */
    public void nextPicture(View view) {
        if (qnumber >=9) {
            displayFnal(namePlayer,noCorrectAnswer);
            return;
        }
        qnumber = qnumber + 1;
        displayQuNo(qnumber);
        ChangeDisplayPic(qnumber);
        animalSounds(qnumber);
        finalScreen(qnumber);
    }

    /**
     * This method is called when the previous button is clicked.
     */

    public void previousPicture(View view) {
        if (qnumber == 1) {
            return;
        }
        qnumber = qnumber - 1;
        displayQuNo(qnumber);
        ChangeDisplayPic(qnumber);
        animalSounds(qnumber);
        finalScreen(qnumber);
    }

    /**
     * This method is called when the sound button is clicked.
     */
    public void soundOfAnimal(View view) {
        displayQuNo(qnumber);
        ChangeDisplayPic(qnumber);
        animalSounds(qnumber);
        finalScreen(qnumber);
    }

    /**
     * This method is called when the answerAbutton is clicked.
     */
    public void answerA(View view) {
        Button answerTextA = (Button) findViewById(
                R.id.answerA_button);
        String answerA = answerTextA.getText().toString();
        answers(answerA, qnumber);
    }

    /**
     * This method is called when the answerBbutton is clicked.
     */
    public void answerB(View view) {
        Button answerTextA = (Button) findViewById(
                R.id.answerB_button);
        String answerB = answerTextA.getText().toString();
        answers(answerB, qnumber);
    }

    /**
     * This method is called when the answerAbutton is clicked.
     */
    public void answerC(View view) {
        Button answerTextA = (Button) findViewById(
                R.id.answerC__button);
        String answerC = answerTextA.getText().toString();
        answers(answerC, qnumber);
    }

    /**
     * This method displays the question number value on the screen.
     */
    private void displayQuNo(int questionNo) {
        TextView questionNOView = (TextView) findViewById(
                R.id.question_number);
        questionNOView.setText("" + questionNo);
    }

    /**
     * This method displays the player name  on the screen.
     */
    private void displayPName(String playerName) {
        TextView playerNameView = (TextView) findViewById(
                R.id.player_name);
        playerNameView.setText("Player: " + playerName);

    }

    /**
     * This method displays the final message on the screen.
     * Suppressing hardcoded text warnings?
     */
//    @SuppressLint("SetTextI18n")

    public void displayFnal(String namePlayer, int noCorrectAnswer) {
        TextView tv = (TextView) findViewById(
                R.id.final_message);

//        String message = "Congratulations " + namePlayer + noCorrectAnswer + " correct out of 8" ;

        tv.setText(getString(R.string.final_message) + namePlayer + noCorrectAnswer + getString(R.string.correct_number));

    }

    /**
     * This method changes the images and the answer button text on the question view screen.
     */
    private void ChangeDisplayPic(int questionNo) {
        ImageView questionPic = (ImageView) findViewById(
                R.id.quizpic_view);

        Button answerTextA = (Button) findViewById(
                R.id.answerA_button);
        Button answerTextB = (Button) findViewById(
                R.id.answerB_button);
        Button answerTextC = (Button) findViewById(
                R.id.answerC__button);

        String One="Tiger";
        String Two="Bear";
        String Three="Dolphin";
        String Four="Dog";
        String Five="Cat";
        String Six="Elephant";
        String Seven="Lion";
        String Eight="Horse";

        if(questionNo==1)
        {
        questionPic.setImageResource(R.drawable.img_0);
            answerTextA.setText(One);
            answerTextB.setText(Six);
            answerTextC.setText(Three);
        }
        if(questionNo==2)
        {
            questionPic.setImageResource(R.drawable.img_1);
            answerTextA.setText(Four);
            answerTextB.setText(Two);
            answerTextC.setText(Five);
        }
        if(questionNo==3)
        {
            questionPic.setImageResource(R.drawable.img_2);
            answerTextA.setText(Three);
            answerTextB.setText(Six);
            answerTextC.setText(Eight);
        }
        if(questionNo==4)
        {
            questionPic.setImageResource(R.drawable.img_3);
            answerTextA.setText(Five);
            answerTextB.setText(Four);
            answerTextC.setText(Two);
        }
        if(questionNo==5)
        {
            questionPic.setImageResource(R.drawable.img_4);
                answerTextA.setText(Three);
                answerTextB.setText(Six);
                answerTextC.setText(Five);
        }
        if(questionNo==6)
        {
            questionPic.setImageResource(R.drawable.img_5);
                answerTextA.setText(Six);
                answerTextB.setText(Four);
                answerTextC.setText(Seven);
        }
        if(questionNo==7)
        {
            questionPic.setImageResource(R.drawable.img_6);
                answerTextA.setText(Three);
                answerTextB.setText(Five);
                answerTextC.setText(Seven);

        }
        if(questionNo==8)
        {
            questionPic.setImageResource(R.drawable.img_7);
                answerTextA.setText(One);
                answerTextB.setText(Eight);
                answerTextC.setText(Six);

        }
    }

    /**
     * This method plays the sound os animals.
     */
    private void animalSounds(int questionNo) {


        if(questionNo==1)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.tiger);
            mp.start();
        }

        if(questionNo==2)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.bear);
            mp.start();
        }

        if(questionNo==3)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.dolphin);
            mp.start();
        }

        if(questionNo==4)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.dog);
            mp.start();
        }

        if(questionNo==5)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.cat);
            mp.start();
        }

        if(questionNo==6)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.elephant);
            mp.start();
        }

        if(questionNo==7)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.lion);
            mp.start();
        }

        if(questionNo==8)
        {
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.horse);
            mp.start();
        }

        }

    /**
     * This method displays the responses to correct and wrong answers.
     * Also delays the response for wrong and right answers
     */
    private void answers(String answer,int questionNo) {
        ImageView questionPic = (ImageView) findViewById(
                R.id.quizpic_view);

        int TIME = 1500; //1500 ms (1,5 Seconds)

//        int delay = 1000;
//        int period = 50000;

        String One="Tiger";
        String Two="Bear";
        String Three="Dolphin";
        String Four="Dog";
        String Five="Cat";
        String Six="Elephant";
        String Seven="Lion";
        String Eight="Horse";

        if(questionNo==1 && answer==One)
        {
            questionPic.setImageResource(R.drawable.right);
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.rightanswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer + 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
//            Timer task = new Timer();
//            task.scheduleAtFixedRate(new TimerTask() {
//                @Override
//                public void run() {
//                    displayQuNo(qnumber);
//                    ChangeDisplayPic(qnumber);
//                    animalSounds(qnumber);
//                    finalScreen(qnumber);
//                }
//            }, delay, period);
            return;

        }
//
        if(questionNo==2 && answer==Two)
        {
            questionPic.setImageResource(R.drawable.right);
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.rightanswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer + 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
            return;

        }
//
        if(questionNo==3 && answer==Three)
        {
            questionPic.setImageResource(R.drawable.right);
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.rightanswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer + 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
            return;

        }
//
        if(questionNo==4 && answer==Four)
        {
            questionPic.setImageResource(R.drawable.right);
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.rightanswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer + 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
            return;

        }

        if(questionNo==5 && answer==Five)
        {
            questionPic.setImageResource(R.drawable.right);
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.rightanswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer + 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
            return;

        }

        if(questionNo==6 && answer==Six)
        {
            questionPic.setImageResource(R.drawable.right);
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.rightanswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer + 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
            return;

        }

        if(questionNo==7 && answer==Seven)
        {
            questionPic.setImageResource(R.drawable.right);
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.rightanswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer + 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
            return;

        }
        if(questionNo==8 && answer==Eight)
        {
            questionPic.setImageResource(R.drawable.right);
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.rightanswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer + 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
            return;

        }
        else
        {
            questionPic.setImageResource(R.drawable.wrong);
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show();
            final MediaPlayer mp = MediaPlayer.create(this, R.raw.wronganswer);
            mp.start();
            qnumber = questionNo + 1;
            noCorrectAnswer = noCorrectAnswer - 1;

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    displayQuNo(qnumber);
                    ChangeDisplayPic(qnumber);
                    animalSounds(qnumber);
                    finalScreen(qnumber);



                }
            }, TIME);
            return;
        }


    }


}
