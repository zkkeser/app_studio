package kubilay.hangman2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Gameplay{
    //Properties of the class
    public String wordToGuess;
    public String currentState;
    public ArrayList<String> wordList;
    public String gameMode;

    //constructor of the class
    public Gameplay(String aWordToGuess, String aCurrentState, ArrayList<String> aWordList, String aGameMode) {
        wordToGuess = aWordToGuess;
        currentState = aCurrentState;
        wordList = aWordList;
        gameMode = aGameMode;
    }

    //Methods
    public void setWord(String aWord){
        wordToGuess = aWord;
    }

    public void setState(String newState){
        currentState = newState;
    }

    public void setWordList(ArrayList<String> newWordList){
        wordList = newWordList;
    }

    public void setGameMode(String aGameMode){
        gameMode = aGameMode;
    }

    public void createList(String[] words, int wordLength){
        ArrayList<String> wordList = new ArrayList<String>();

        for (int i = 0; i < words.length ; i++){
            if (words[i].length() == (wordLength)){
                wordList.add(words[i]);
            }
        }
        setWordList(wordList);
    }

}

public class MainActivity extends Activity {
    private static final int SETTINGS_INFO = 1;
    public static String gameMode = "";
    public static int wordLength = 4;
    public static int numberGuesses = 5;
    public static int guessesLeft = 5;
    public static String usedLetters = "";
    public static int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateSettings();

        Button submitButton = (Button)findViewById(R.id.submitButton);
        Button settingsButton = (Button)findViewById(R.id.settingsButton);
        Button highscoreButton = (Button)findViewById(R.id.highscoreButton);
        Button newGameButton = (Button)findViewById(R.id.newGameButton);
        final TextView messageTextView = (TextView)findViewById(R.id.messageTextView);
        final TextView usedTextView = (TextView)findViewById(R.id.usedTextView);
        final TextView wordTextView = (TextView)findViewById(R.id.wordTextView);
        final EditText inputEditText = (EditText)findViewById(R.id.inputEditText);
        final TextView guessesTextView = (TextView)findViewById(R.id.guessesTextView);

        final Gameplay hangman = new Gameplay("","",null,"evil");

        //create a list of words based on word length
        final String[] words = getResources().getStringArray(R.array.words);
        hangman.createList(words, wordLength);


        //generates a word and sets it as the word the user needs to guess (for "good" mode)
        String wordToGuess = generateWord(hangman.wordList);
        hangman.setWord(wordToGuess);
        hangman.setState(dashWord(wordToGuess));

        //create interface
        guessesTextView.setText("Guesses left: "+ guessesLeft);
        wordTextView.setText(dashWord(hangman.wordToGuess));



        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String letter = inputEditText.getText().toString().toUpperCase();
                inputEditText.setText("");

                //update the game good mode
                if (hangman.gameMode.equals("good") && !invalidInput(letter)) {
                    if (checker(hangman.wordToGuess, letter)) {
                        messageTextView.setText("Nice guess!");
                        wordTextView.setText(reconstruct(hangman.wordToGuess, hangman.currentState, letter));
                        hangman.setState(reconstruct(hangman.wordToGuess, hangman.currentState, letter));

                    } else {
                        messageTextView.setText("Guess again!");
                        wrongGuess();
                        guessesTextView.setText("Guesses left: " + guessesLeft);
                        usedLetters += letter + " ";
                        usedTextView.setText(usedLetters);
                    }
                //update game evil mode
                }else if (hangman.gameMode.equals("evil") && !invalidInput(letter)){
                    //look for best way to dodge the answer
                    //Create a list with dashed equivalents (----, -E--- etc)
                    ArrayList<String> universe = new ArrayList<>();
                    for (int i = 0; i< hangman.wordList.size() ; i++ ){
                        universe.add(reconstruct(hangman.wordList.get(i),hangman.currentState,letter));
                    }

                    //get most frequent dashed word for example: "----" appears most often
                    String popular = getPopularItem(universe);

                    //create new universe of words where dashed equivalent equals "----"
                    ArrayList<String> newUniverse = new ArrayList<>();
                    for(int i = 0; i< hangman.wordList.size(); i++) {
                        if (reconstruct(hangman.wordList.get(i), hangman.currentState, letter).equals(popular))
                            newUniverse.add(hangman.wordList.get(i));
                    }

                    hangman.setWordList(newUniverse);

                    //update the state of the game
                    if (!hangman.currentState.equals(popular)){ // if guess is right
                        messageTextView.setText("Nice guess!");
                        hangman.setState(popular);
                        wordTextView.setText(popular);
                    }else{                                      // if guess is wrong
                        messageTextView.setText("Guess again!");
                        wrongGuess();
                        guessesTextView.setText("Guesses left:" + guessesLeft);
                        usedLetters += letter + " ";
                        usedTextView.setText(usedLetters);
                    }
                }else{
                    messageTextView.setText("I'm sorry, but your input is invalid!!");
                }


                if (winChecker(hangman.currentState)) {
                    score = numberGuesses*10 / (numberGuesses + 1 - guessesLeft);
                    if(hangman.gameMode.equals("evil")) score = score*2;

                    messageTextView.setText("You won! Your score is "+ score + "!");

                }else if (guessesLeft == 0){
                    messageTextView.setText("You lost, I'm sorry.");
                }
            }
        });

        // start a new game, using the new settings
        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateSettings();
                final TextView wordTextView = (TextView)findViewById(R.id.wordTextView);
                final TextView guessesTextView = (TextView)findViewById(R.id.guessesTextView);
                hangman.setGameMode(gameMode);

                //Create a list of words based on word length
                String[] words = getResources().getStringArray(R.array.words);
                hangman.createList(words, wordLength);

                //Generates a word and sets it as the word the user needs to guess
                String wordToGuess = generateWord(hangman.wordList);
                hangman.setWord(wordToGuess);
                hangman.setState(dashWord(wordToGuess));

                //Create interface
                guessesTextView.setText("Guesses left: "+ guessesLeft);
                wordTextView.setText(dashWord(hangman.wordToGuess));

                //clear old interface & info
                messageTextView.setText("");
                usedLetters = "";
                usedTextView.setText("Used Letters");
                score = 0;

            }
        });

        // open settings screen
        settingsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intentPreferences = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(intentPreferences, SETTINGS_INFO);
            }
        });

        // open highscores screen
        highscoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentHighscores = new Intent(getApplicationContext(), HistoryViewActivity.class);
                intentHighscores.putExtra("highscore", Integer.toString(score) );
                startActivity(intentHighscores);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SETTINGS_INFO){
            updateSettings();
        }
    }

    //get preferences from settings menu
    private void updateSettings(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((this));
        if(sharedPreferences.getBoolean("pref_evil",true)){
            MainActivity.gameMode = "evil";
        }else{
            MainActivity.gameMode= "good";
        }
        String wordLengthStr = sharedPreferences.getString("pref_word_length", "4");
        MainActivity.wordLength = Integer.parseInt(wordLengthStr);

        String numberGuessesStr = sharedPreferences.getString("pref_number_guesses", "5");
        MainActivity.numberGuesses = Integer.parseInt(numberGuessesStr);
        MainActivity.guessesLeft = Integer.parseInt(numberGuessesStr);

    }

    //this selects a random word from the list of words and returns it
    public String generateWord(List<String> array){
        Random r = new Random();
        return array.get(r.nextInt(array.size()));
    }

    public String dashWord(String word){
        String dashedWord = "";
        for (int i = 0; i < word.length(); i++) {
            dashedWord += "-";
        }
        return dashedWord;
    }

    public boolean invalidInput(String letter){
        String allLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (MainActivity.usedLetters.contains(letter) || 1 < letter.length() || !allLetters.contains(letter)){
            return true;
        }else{
            return false;
        }
    }

    public boolean checker(String wordToGuess, String letter){
        if (wordToGuess.contains(letter)){
            return true;
        }else return false;
    }

    //fills in the blanks (dashes)
    public String reconstruct(String word, String state, String letter){
        String newWord = "";
        for (int i =0; i< word.length(); i++){
            if (Character.toString(word.charAt(i)).equals(letter)) {
                newWord = newWord + Character.toString(word.charAt(i));
            }else if(!Character.toString(state.charAt(i)).equals("-")){   // if we already have the letter
                newWord = newWord + Character.toString(state.charAt(i));
            }else {
                newWord = newWord + "-";
            }
        }
        return newWord;
    }

    public void wrongGuess(){
        MainActivity.guessesLeft = MainActivity.guessesLeft - 1;
    }

    //finds out which category is the largest
    public String getPopularItem(List<String>  array){
        String element = "";
        int count = 0;
        for (int i = 0; i<array.size(); i++){
            String tempElement = array.get(i);
            int tempCount = 0;
            for (int j = 0; j<array.size(); j++){
                if(array.get(j).equals(tempElement))
                    tempCount++;
            if (tempCount>count){
                element = tempElement;
                count = tempCount;
            }
            }
        }
        return element;
    }

    public boolean winChecker(String state){
        if (state.contains("-")){
            return false;
        }else {
            return true;
        }
    }
}


