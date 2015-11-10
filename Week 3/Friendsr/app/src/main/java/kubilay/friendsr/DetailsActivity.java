package kubilay.friendsr;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        Intent getName = getIntent();
        String friendName = getName.getExtras().getString("friendName");

        ImageView friend = (ImageView)findViewById(R.id.friendImage);
        TextView friendDescription = (TextView)findViewById(R.id.friendDescription);
        TextView friendFullName = (TextView)findViewById(R.id.friendFullName);

        //http://stackoverflow.com/questions/5760751/android-variable-passed-for-r-drawable-variablevalue
        friend.setImageResource(getResources().getIdentifier(friendName, "drawable", getPackageName()));

        Resources res = getResources();
        String[] friend_details = res.getStringArray(R.array.friend_details);
        String[] friend_full_names = res.getStringArray(R.array.friend_full_names);

        if (friendName.equals("chandler")){
            friendDescription.setText(friend_details[0]);
            friendFullName.setText(friend_full_names[0]);
        }
        if (friendName.equals("joey")){
            friendDescription.setText(friend_details[1]);
            friendFullName.setText(friend_full_names[1]);
        }
        if (friendName.equals("monica")){
            friendDescription.setText(friend_details[2]);
            friendFullName.setText(friend_full_names[2]);
        }
        if (friendName.equals("phoebe")){
            friendDescription.setText(friend_details[3]);
            friendFullName.setText(friend_full_names[3]);
        }
        if (friendName.equals("rachel")){
            friendDescription.setText(friend_details[4]);
            friendFullName.setText(friend_full_names[4]);
        }
        if (friendName.equals("ross")){
            friendDescription.setText(friend_details[5]);
            friendFullName.setText(friend_full_names[5]);
        }





    }
    public void sendRating(View view) {
        String buttonText = ((Button) view).getText().toString();

        Intent getName = getIntent();
        String friendName = getName.getExtras().getString("friendName");



        if(buttonText.equals("Hot")){
            Intent sendRating = new Intent();
            sendRating.putExtra("rating", "hot");
            sendRating.putExtra("friendName", friendName );
            setResult(RESULT_OK, sendRating);

            finish();
        }
        else if (buttonText.equals("Not")){
            Intent sendRating = new Intent();
            sendRating.putExtra("rating", "not");
            sendRating.putExtra("friendName", friendName );
            setResult(RESULT_OK, sendRating);

            finish();
        }

    }


}
