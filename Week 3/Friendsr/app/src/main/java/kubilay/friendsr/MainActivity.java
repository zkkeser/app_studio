package kubilay.friendsr;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        String[] friend_names = res.getStringArray(R.array.friend_names);

        TextView chandler = (TextView)findViewById(R.id.chandlerShortName);
        TextView joey = (TextView)findViewById(R.id.joeyShortName);
        TextView monica = (TextView)findViewById(R.id.monicaShortName);
        TextView phoebe = (TextView)findViewById(R.id.phoebeShortName);
        TextView rachel = (TextView)findViewById(R.id.rachelShortName);
        TextView ross = (TextView)findViewById(R.id.rossShortName);

        chandler.setText(friend_names[0]);
        joey.setText(friend_names[1]);
        monica.setText(friend_names[2]);
        phoebe.setText(friend_names[3]);
        rachel.setText(friend_names[4]);
        ross.setText(friend_names[5]);





    }


    public void sendChandler(View view) {
        Intent openDetail = new Intent(this,DetailsActivity.class);
        final int result = 1;
        openDetail.putExtra("friendName", "chandler");
        startActivityForResult(openDetail, result);
    }
    public void sendJoey(View view) {
        Intent openDetail = new Intent(this,DetailsActivity.class);
        final int result = 1;
        openDetail.putExtra("friendName","joey" );
        startActivityForResult(openDetail, result);
    }
    public void sendMonica(View view) {
        Intent openDetail = new Intent(this,DetailsActivity.class);
        final int result = 1;
        openDetail.putExtra("friendName","monica" );
        startActivityForResult(openDetail, result);
    }
    public void sendPhoebe(View view) {
        Intent openDetail = new Intent(this,DetailsActivity.class);
        final int result = 1;
        openDetail.putExtra("friendName","phoebe" );
        startActivityForResult(openDetail, result);
    }
    public void sendRachel(View view) {
        Intent openDetail = new Intent(this,DetailsActivity.class);
        final int result = 1;
        openDetail.putExtra("friendName","rachel" );
        startActivityForResult(openDetail, result);
    }
    public void sendRoss(View view) {
        Intent openDetail = new Intent(this,DetailsActivity.class);
        final int result = 1;
        openDetail.putExtra("friendName","ross" );
        startActivityForResult(openDetail, result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String friendThatGotRating = data.getStringExtra("friendName");
        String rating = data.getStringExtra("rating");

        TextView chandler = (TextView)findViewById(R.id.chandlerShortName);
        TextView joey = (TextView)findViewById(R.id.joeyShortName);
        TextView monica = (TextView)findViewById(R.id.monicaShortName);
        TextView phoebe = (TextView)findViewById(R.id.phoebeShortName);
        TextView rachel = (TextView)findViewById(R.id.rachelShortName);
        TextView ross = (TextView)findViewById(R.id.rossShortName);

        if (rating.equals("hot")){
            //change name of friend green
            if (friendThatGotRating.equals("chandler")){
                chandler.setTextColor(Color.parseColor("#00CC00"));
            }
            else if (friendThatGotRating.equals("joey")){
                joey.setTextColor(Color.parseColor("#00CC00"));
            }

            else if (friendThatGotRating.equals("monica")){
                monica.setTextColor(Color.parseColor("#00CC00"));
            }
            else if (friendThatGotRating.equals("phoebe")){
                phoebe.setTextColor(Color.parseColor("#00CC00"));
            }

            else if (friendThatGotRating.equals("rachel")){
                rachel.setTextColor(Color.parseColor("#00CC00"));
            }
            else if (friendThatGotRating.equals("ross")){
                ross.setTextColor(Color.parseColor("#00CC00"));
            }

            }
        else if (rating.equals("not")){
            //change name of friend red
            if (friendThatGotRating.equals("chandler")){
                chandler.setTextColor(Color.parseColor("#F00808"));
            }
            else if (friendThatGotRating.equals("joey")){
                joey.setTextColor(Color.parseColor("#F00808"));
            }

            else if (friendThatGotRating.equals("monica")){
                monica.setTextColor(Color.parseColor("#F00808"));
            }
            else if (friendThatGotRating.equals("phoebe")){
                phoebe.setTextColor(Color.parseColor("#F00808"));
            }

            else if (friendThatGotRating.equals("rachel")){
                rachel.setTextColor(Color.parseColor("#F00808"));
            }
            else if (friendThatGotRating.equals("ross")){
                ross.setTextColor(Color.parseColor("#F00808"));
            }
        }


    }
}
