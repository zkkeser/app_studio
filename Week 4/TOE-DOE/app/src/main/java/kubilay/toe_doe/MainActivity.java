package kubilay.toe_doe;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<String> tasks;
    private ArrayAdapter<String> taskAdapter;
    private ListView listViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewTasks = (ListView) findViewById(R.id.listViewTasks);
        tasks = new ArrayList<String>();
        taskAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, tasks);
        listViewTasks.setAdapter(taskAdapter);





        //Long click listener that deletes an item
        //http://stackoverflow.com/questions/8846707/how-to-implement-a-long-click-listener-on-a-listview
        listViewTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> Adapter, View item, int pos, long id) {
                tasks.remove(pos);
                taskAdapter.notifyDataSetChanged();
                return true;
            }
        });

        //Retrieve saved data

    }

    private void saveSettings(){
        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();



    }

    //Force closed by user:
    @Override
    protected void onStop() {
        saveSettings();
        super.onStop();
    }

    public void addTask(View v) {
        EditText newTaskText = (EditText) findViewById(R.id.newTaskText);
        String itemText = newTaskText.getText().toString();
        taskAdapter.add(itemText);
        newTaskText.setText("");

    }




}
