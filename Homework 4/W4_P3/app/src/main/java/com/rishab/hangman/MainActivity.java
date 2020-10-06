package com.rishab.hangman;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextView typedWord;
    private HashMap<Integer, String[]> wordBanks;
    private String ans;
    private String playerInput;
    private ImageView hangman;
    private Integer life;
    private int[] stages;
    private int correct;
    private TextView hintText;
    private Button hintButton;
    private int hintCount;
    private String[] hint;
    private ArrayList<Integer> buttonsPressed = new ArrayList<>();

    //setting up all the variables - textViews, Buttons, WordBanks, hangman images
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        typedWord = findViewById(R.id.typedWord);
        hangman = findViewById(R.id.hangman);
        hintButton = findViewById(R.id.hintButton);
        wordBanks = new HashMap<>();
        wordBanks.put(0, new String[]{"Computer", "An essential", "Our major", "Technology"});
        wordBanks.put(1, new String[]{"coronavirus", "Virus", "Mask", "Quarantine"});
        wordBanks.put(2, new String[]{"Summer", "Season", "Hot", "No School"});
        wordBanks.put(3, new String[]{"All-nighters", "Exam Week", "Sleep Deprived", "College Students"});
        wordBanks.put(4, new String[]{"England", "Country", "Cold", "London"});
        hintText = findViewById(R.id.hintText);
        stages = new int[]{R.drawable.step0, R.drawable.step1, R.drawable.step2, R.drawable.step3, R.drawable.step4, R.drawable.step5, R.drawable.step6};

        //If onCreate is called from a fresh start of the activity, then call loadActivity, else if its from an activity like screen rotation, do not rerender
        if (savedInstanceState == null) {
            loadActivity();
        }
    }

    //loadActivity picks a new word, resets the hints, refresh the image status and re-enable all the buttons.
    private void loadActivity() {
        pickWord();
        hintCount = -1;
        life = 0;
        correct = 0;
        playerInput = buildString(ans.length());
        typedWord.setText(playerInput);
        if (hintText != null) {
            hintText.setText("hint??");
            hintButton.setEnabled(true);
        }
        if (!buttonsPressed.isEmpty()){
            for (int button : buttonsPressed) {
                findViewById(button).setEnabled(true);
            }
        };
        buttonsPressed.clear();
        hangman.setImageResource(stages[0]);
    }

    //this is the onclick Function when a letter button is pressed
    public void touchLetter(View view) {
        buttonsPressed.add(view.getId()); //add the button ID that is pressed to an Array to store its states
        String input = ((Button) view).getText().toString();
        int index = ans.indexOf(input);
        if (index != -1) {
              //if answer word contains the the letter users chose, and has not been chosen before then replace '-' with letter
             //Replace all instance of the letter in the current game status
                while (index != -1) {
                    playerInput = playerInput.substring(0, index * 2) + input + playerInput.substring(index * 2 + 1);
                    ans = ans.replaceFirst(input, "-");
                    index = ans.indexOf(input);
                    correct++;
                }
                typedWord.setText(playerInput);
        } else {
            //if answer word does not contain the the letter users chose, then increase the life or turn and change state of image
            life++;
            if (life < 7) {
                hangman.setImageResource(stages[life]);
            }
        }
        //disable the letter button pressed
        if (!ans.contains(input)) {
            view.setEnabled(false);
        }
        checkRounds(); //check game status
    }

    //checks if the user lost or won, display corresponding toast
    public void checkRounds() {
        if (life >= 6) {
            Toast.makeText(getApplicationContext(), "You Lost! Your score is " + correct * 5 + "!",
                    Toast.LENGTH_LONG).show();
        } else if (correct == ans.length()) {
            Toast.makeText(getApplicationContext(), "You won! Your score is " + correct * 5 + " !",
                    Toast.LENGTH_LONG).show();
        }
    }

    //helper function that builds the user display of the word
    public String buildString(int len) {
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < len; i++) {
            ans.append("_ ");
        }

        return ans.toString();
    }

    //helper function that randomly pick a word and its hint for the round
    public void pickWord() {
        int index = (int) (Math.random() * ((4) + 1));
        ans = Objects.requireNonNull(wordBanks.get(index))[0].toUpperCase();
        hint = wordBanks.get(index);
    }

    //onclick function for the newGame button, call loadActivity and creates a new round
    public void newGame(View view) {
        loadActivity();
    }

    //saves all the important variables from current game round
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("ans", ans);
        outState.putString("playerInput", playerInput);
        outState.putInt("correct", correct);
        outState.putInt("life", life);
        outState.putInt("hintCount", hintCount);
        outState.putStringArray("hint", hint);
        outState.putIntegerArrayList("buttonsPressed", buttonsPressed);

    }

    //restores all the textViews, images, hint, button status on restoreInstance
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ans = savedInstanceState.getString("ans");
        playerInput = savedInstanceState.getString("playerInput");
        Log.i("restored", "onRestoreInstanceState: " + playerInput);
        correct = savedInstanceState.getInt("correct");
        life = savedInstanceState.getInt("life");
        hint = savedInstanceState.getStringArray("hint");
        hintCount = savedInstanceState.getInt("hintCount");
        typedWord.setText(playerInput);
        hangman.setImageResource(stages[life]);
        if (hintText != null) {
            if (hintCount == -1) {
                hintText.setText("hint??");
            } else {
                hintText.setText(hint[hintCount]);
                if (hintCount >=2){
                    hintButton.setEnabled(false);
                }
            }

        }
        buttonsPressed = savedInstanceState.getIntegerArrayList("buttonsPressed");
        assert buttonsPressed != null;
        if (!buttonsPressed.isEmpty()) {
            for (int button : buttonsPressed) {
                findViewById(button).setEnabled(false);
            }
        }
    }

    //onclick Function for the hint button
    public void displayHint(View view) {
        hintCount++;
        if (hintCount < 2) {
            hintText.setText(hint[hintCount]);
        } else {
            hintText.setText(hint[hintCount]);
            view.setEnabled(false);
        }
        life++;
        if (life < 7) {
            hangman.setImageResource(stages[life]);
        }
        checkRounds();
    }
}