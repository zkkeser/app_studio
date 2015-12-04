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

    //constructor of the class
    public Gameplay(String aWordToGuess, String aCurrentState, ArrayList<String> aWordList, ArrayList<String> aUsedLetters) {
        wordToGuess = aWordToGuess;
        currentState = aCurrentState;
        wordList = aWordList;
    }

    //Methods
    public void setWord(String aWord){
        wordToGuess = aWord;
    }

    //keeps track of which letters are guessed already
    public void setState(String newState){
        currentState = newState;
    }

    public void setWordList(ArrayList<String> newWordList){
        wordList = newWordList;
    }



    public void createList(String[] allWords, int wordLength){
        ArrayList<String> wordList = new ArrayList<String>();

        for (int i = 0; i < allWords.length ; i++){
            if (allWords[i].length() == (wordLength)){
                wordList.add(allWords[i]);
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
        final TextView playerMessage = (TextView)findViewById(R.id.playerMessage);
        final TextView usedLettersText = (TextView)findViewById(R.id.usedLettersText);
        final TextView wordTextView = (TextView)findViewById(R.id.wordToGuess);
        final EditText inputEditText = (EditText)findViewById(R.id.inputEditText);
        final TextView guessesTextView = (TextView)findViewById(R.id.guessesLeft);
        final Gameplay hangman = new Gameplay("","",null, null);

        //Create a list of words based on word length
        String[] allWords = getResources().getStringArray(R.array.words_small);
        hangman.createList(allWords, wordLength);


        //Generates a word and sets it as the word the user needs to guess
        String wordToGuess = generateWord(hangman.wordList);
        hangman.setWord(wordToGuess);
        hangman.setState(dashWord(wordToGuess));

        //Create interface
        guessesTextView.setText("Guesses left: "+ numberGuesses);
        wordTextView.setText(dashWord(hangman.wordToGuess));

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String letter = inputEditText.getText().toString().toUpperCase();
                inputEditText.setText("");

                //Update the game good mode
                if (gameMode.equals("good") && !invalidInput(letter)) {
                    if (checker(hangman.wordToGuess, letter)) {
                        playerMessage.setText("Nice guess!");
                        wordTextView.setText(reconstruct(hangman.wordToGuess, hangman.currentState, letter));
                        hangman.setState(reconstruct(hangman.wordToGuess, hangman.currentState, letter));

                    } else {
                        playerMessage.setText("Guess again!");
                        wrongGuess();
                        guessesTextView.setText("Guesses left: " + numberGuesses);
                        usedLetters += letter + " ";
                        usedLettersText.setText(usedLetters);
                    }
                //Update game evil mode
                }else if (gameMode.equals("evil") && !invalidInput(letter)){
                    //look for best way to dodge the answer
                    ArrayList<String> universe = new ArrayList<>();
                    for (int i = 0; i< hangman.wordList.size() ; i++ ){
                        universe.add(reconstruct(hangman.wordList.get(i),hangman.currentState,letter));
                    }
                    String popular = getPopularItem(universe);
                    ArrayList<String> newUniverse = new ArrayList<>();
                    for(int i = 0; i< hangman.wordList.size(); i++){
                        if (reconstruct(hangman.wordList.get(i),hangman.currentState,letter).equals(popular))
                            newUniverse.add(hangman.wordList.get(i));
                    hangman.setWordList(newUniverse);

                    //update the state of the game
                    if (!hangman.currentState.equals(popular)){ // if guess is right
                        playerMessage.setText("Nice guess!");
                        hangman.setState(popular);
                        wordTextView.setText(popular);
                    }else{                                      // if guess is wrong
                        playerMessage.setText("Guess again!");
                        wrongGuess();
                        guessesTextView.setText("Guesses left:" + numberGuesses);
                        usedLetters += letter + " ";
                        usedLettersText.setText(usedLetters);
                    }
                    }
                }else{
                    playerMessage.setText("I'm sorry, but you're input is invalid!!");
                }


                if (winChecker(hangman.currentState)) {
                    score = numberGuesses * wordLength;
                    playerMessage.setText("You won! Your score is "+ score + "!");

                }else if (numberGuesses == 0){
                    playerMessage.setText("You lost, I'm sorry.");
                }
            }
        });

        // Start a new game, using the new settings
        newGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                updateSettings();
                final TextView wordTextView = (TextView)findViewById(R.id.wordToGuess);
                final TextView guessesTextView = (TextView)findViewById(R.id.guessesLeft);


                //Create a list of words based on word length
                String[] allWords = getResources().getStringArray(R.array.words_small);
                hangman.createList(allWords, wordLength);

                //Generates a word and sets it as the word the user needs to guess
                String wordToGuess = generateWord(hangman.wordList);
                hangman.setWord(wordToGuess);
                hangman.setState(dashWord(wordToGuess));

                //Create interface
                guessesTextView.setText("Guesses left: "+ numberGuesses);
                wordTextView.setText(dashWord(hangman.wordToGuess));

                //Reset interface
                playerMessage.setText("");
                usedLettersText.setText("");
            }
        });

        // Open settings screen
        settingsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intentPreferences = new Intent(getApplicationContext(), SettingsActivity.class);

                startActivityForResult(intentPreferences, SETTINGS_INFO);
            }
        });
        // Open highscores screen
        highscoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentHighscores = new Intent(getApplicationContext(), HistoryViewActivity.class);
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

    //Get preferences from settings menu
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

    }

    public void wrongGuess(){
        MainActivity.numberGuesses = MainActivity.numberGuesses - 1;
    }

    public boolean checker(String wordToGuess, String letter){
        if (wordToGuess.contains(letter)){
        return true;
        }else return false;
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

    public boolean invalidInput(String letter){
        String allLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (MainActivity.usedLetters.contains(letter) || 1 < letter.length() || !allLetters.contains(letter)){
            return true;
        }else{
            return false;
        }
    }

    //This selects a random word from the list of words and returns it
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

    //Fills in the blanks (dashes)
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

    public boolean winChecker(String state){
        if (state.contains("-")){
            return false;
        }else {
            return true;
        }
    }
}


