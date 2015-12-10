package kubilay.hangman2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class History{
    //Properties of the class
    public String highscoreList;

    //constructor of the class
    public History(String aHighscoreList) {
        highscoreList = aHighscoreList;
    }

    //class methods
    public void setHighscores(String aHighscoreList){
        highscoreList = aHighscoreList;
    }

    public void addHighscore(String newHighscore){
        highscoreList = newHighscore +"\n" + highscoreList;
    }
}
public class HistoryViewActivity extends Activity{
    TextView scoreList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        final History highscore = new History("");

        //load data
        String savedList = getPreferences(Context.MODE_PRIVATE).getString("LIST", "EMPTY");
        if(!savedList.equals("EMPTY")){
            highscore.setHighscores(savedList);
        }



        Button submitButton = (Button)findViewById(R.id.submitButton);
        Button refreshButton = (Button)findViewById(R.id.refreshButton);
        final EditText nameText = (EditText)findViewById(R.id.nameText);
        TextView scoreView = (TextView)findViewById(R.id.userScoreView);
        scoreList = (TextView)findViewById(R.id.scoreList);



        scoreList.setText(highscore.highscoreList);

        //Get score from previous activity
        Intent getScore = getIntent();
        final String score = getScore.getExtras().getString("highscore");
        scoreView.setText("Your score is: " + score + ". Input your name below and submit!");

        //Combine name and score to append to list
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name = nameText.getText().toString();
                String entry = name+","+score;
                highscore.addHighscore(entry);
                nameText.setText("");
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                scoreList.setText(highscore.highscoreList);
            }
        });

        if(savedInstanceState !=null){
            String list = savedInstanceState.getString("LIST");
            scoreList.setText(list);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (!scoreList.getText().toString().equals("")) {
            outState.putString("LIST", scoreList.getText().toString());
            super.onSaveInstanceState(outState);
        }
    }

    private void saveSettings(){
        if (!scoreList.getText().toString().equals("")) {
            SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
            editor.putString("LIST", scoreList.getText().toString());
            editor.commit();
        }
    }

    @Override
    protected void onStop() {
        saveSettings();
        super.onStop();
    }
}


