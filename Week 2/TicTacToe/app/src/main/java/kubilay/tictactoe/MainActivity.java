package kubilay.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class Board {
    //Properties of the class
    public int tl;
    public int tm;
    public int tr;
    public int ml;
    public int mm;
    public int mr;
    public int bl;
    public int bm;
    public int br;

    //constructor of the class
    public Board(int atl, int atm, int atr, int aml, int amm, int amr, int abl, int abm, int abr) {
        tl = atl;
        tm = atm;
        tr = atr;
        ml = aml;
        mm = amm;
        mr = amr;
        bl = abl;
        bm = abm;
        br = abr;
    }

    //Move methods 0= empty 1 = O and 2 = X
    public void setTL(int aMove) {
        tl = aMove;
    }

    public void setTM(int aMove) {
        tm = aMove;
    }

    public void setTR(int aMove) {
        tr = aMove;
    }

    public void setML(int aMove) {
        ml = aMove;
    }

    public void setMM(int aMove) {
        mm = aMove;
    }

    public void setMR(int aMove) {
        mr = aMove;
    }

    public void setBL(int aMove) {
        bl = aMove;
    }

    public void setBM(int aMove) {
        bm = aMove;
    }

    public void setBR(int aMove) {
        br = aMove;
    }

    //Win method, checks if the game is won or not
    public int win() {
        if ((tl == 1 && tm == 1&& tr == 1) ||
                (ml == 1 && mm == 1&& mr == 1) ||
                (bl == 1 && bm == 1&& br == 1) ||
                (tl == 1 && mm == 1&& br == 1) ||
                (bl == 1 && mm == 1&& tr == 1)){
            return 1;
        }else if (((tl == 2 && tm == 2&& tr == 2) ||
                (ml == 2 && mm == 2&& mr == 2) ||
                (bl == 2 && bm == 2&& br == 2) ||
                (tl == 2 && mm == 2&& br == 2) ||
                (bl == 2 && mm == 2&& tr == 2))){
            return 2;
        }else{
            return 0;
        }
    }

}

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Board tictactoe = new Board(0,0,0,0,0,0,0,0,0);

        //Button refferences
        Button tlButton = (Button)findViewById(R.id.tlButton);
        Button tmButton = (Button)findViewById(R.id.tmButton);
        Button trButton = (Button)findViewById(R.id.trButton);
        Button mlButton = (Button)findViewById(R.id.mlButton);
        Button mmButton = (Button)findViewById(R.id.mmButton);
        Button mrButton = (Button)findViewById(R.id.mrButton);
        Button blButton = (Button)findViewById(R.id.blButton);
        Button bmButton = (Button)findViewById(R.id.bmButton);
        Button brButton = (Button)findViewById(R.id.brButton);
        Button resetButton = (Button)findViewById(R.id.resetButton);
        TextView winText = (TextView)findViewById(R.id.winText);

        //top-left button listeners
        tlButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Button tlButton = (Button)findViewById(R.id.tlButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        tlButton.setText("O");
                        tictactoe.setTL(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");

                    }
                }
        );
        tlButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button tlButton = (Button) findViewById(R.id.tlButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        tlButton.setText("X");
                        tictactoe.setTL(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );

        //top-mid button listeners
        tmButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Button tMButton = (Button)findViewById(R.id.tmButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        tMButton.setText("O");
                        tictactoe.setTM(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");

                    }
                }
        );
        tmButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button tmButton = (Button) findViewById(R.id.tmButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        tmButton.setText("X");
                        tictactoe.setTM(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );
        //top-right button listeners
        trButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Button trButton = (Button)findViewById(R.id.trButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        trButton.setText("O");
                        tictactoe.setTR(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                    }
                }
        );
        trButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button trButton = (Button) findViewById(R.id.trButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        trButton.setText("X");
                        tictactoe.setTR(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );
        //mid-left button listeners
        mlButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Button mlButton = (Button)findViewById(R.id.mlButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        mlButton.setText("O");
                        tictactoe.setML(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                    }
                }
        );
        mlButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button mlButton = (Button) findViewById(R.id.mlButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        mlButton.setText("X");
                        tictactoe.setML(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );

        //mid-mid button listeners
        mmButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Button mmButton = (Button)findViewById(R.id.mmButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        mmButton.setText("O");
                        tictactoe.setMM(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                    }
                }
        );
        mmButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button mmButton = (Button) findViewById(R.id.mmButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        mmButton.setText("X");
                        tictactoe.setMM(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );
        //mid-right listeners
        mrButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Button mrButton = (Button)findViewById(R.id.mrButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        mrButton.setText("O");
                        tictactoe.setMR(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                    }
                }
        );
        mrButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button mrButton = (Button) findViewById(R.id.mrButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        mrButton.setText("X");
                        tictactoe.setMR(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );
        //bottom left listeners
        blButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Button blButton = (Button)findViewById(R.id.blButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        blButton.setText("O");
                        tictactoe.setBL(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                    }
                }
        );
        blButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button blButton = (Button) findViewById(R.id.blButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        blButton.setText("X");
                        tictactoe.setBL(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );
        //bottom mid listeners
        bmButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Button bmButton = (Button)findViewById(R.id.bmButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        bmButton.setText("O");
                        tictactoe.setBM(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                    }
                }
        );
        bmButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button bmButton = (Button) findViewById(R.id.bmButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        bmButton.setText("X");
                        tictactoe.setBM(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );
        //bottom right listeners
        brButton.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Button brButton = (Button)findViewById(R.id.brButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        brButton.setText("O");
                        tictactoe.setBR(1);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                    }
                }
        );
        brButton.setOnLongClickListener(
                new Button.OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        Button brButton = (Button) findViewById(R.id.brButton);
                        TextView winText = (TextView) findViewById(R.id.winText);
                        brButton.setText("X");
                        tictactoe.setBR(2);
                        int winner = tictactoe.win();
                        if (winner == 1) winText.setText("The winner is O!!");
                        if (winner == 2) winText.setText("The winner is X!!");
                        return true;
                    }
                }
        );

        resetButton.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Button resetButton = (Button) findViewById(R.id.resetButton);
                        TextView winText = (TextView)findViewById(R.id.winText);
                        Button tlButton = (Button) findViewById(R.id.tlButton);
                        Button tmButton = (Button) findViewById(R.id.tmButton);
                        Button trButton = (Button) findViewById(R.id.trButton);
                        Button mlButton = (Button) findViewById(R.id.mlButton);
                        Button mmButton = (Button) findViewById(R.id.mmButton);
                        Button mrButton = (Button) findViewById(R.id.mrButton);
                        Button blButton = (Button) findViewById(R.id.blButton);
                        Button bmButton = (Button) findViewById(R.id.bmButton);
                        Button brButton = (Button) findViewById(R.id.brButton);

                        winText.setText("");
                        resetButton.setText("");
                        tlButton.setText("");
                        tmButton.setText("");
                        trButton.setText("");
                        mlButton.setText("");
                        mmButton.setText("");
                        mrButton.setText("");
                        blButton.setText("");
                        bmButton.setText("");
                        brButton.setText("");

                        tictactoe.setTL(0);
                        tictactoe.setTM(0);
                        tictactoe.setTR(0);
                        tictactoe.setML(0);
                        tictactoe.setMM(0);
                        tictactoe.setMR(0);
                        tictactoe.setBL(0);
                        tictactoe.setBM(0);
                        tictactoe.setBR(0);


                    }
                }
        );

    }


}
