package paulberinde_tampanariu.paulberinde_pset3;
/* Main activity for TodoList App */

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


// Main Activity declaration
public class MainActivity extends AppCompatActivity {
    private ArrayList<String> arrayListToDo;
    private ArrayAdapter<String> arrayAdapterToDo;
    DBHandler MyDBHandler = new DBHandler(this);
    CRUDOperations MyCRUD = new CRUDOperations(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize_view();
    }
    // Initializes and populates the list view
    public void initialize_view(){
        MyCRUD.open();
        ListView listView = (ListView) findViewById(R.id.taskView);
        arrayListToDo = new ArrayList<String>();
        Cursor entries = MyCRUD.fetch();
        while (entries.moveToNext()) {
            String entry = entries.getString(entries.getColumnIndex("task"));
            arrayListToDo.add(entry);
        }
        arrayAdapterToDo = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayListToDo);
        MyCRUD.close();
        listView.setAdapter(arrayAdapterToDo);

    }
    // Add_Button behaviour definition
    public void addButtonClick (View view) {
        String input = ((EditText) findViewById(R.id.taskText)).getText().toString();
        ((EditText) findViewById(R.id.taskText)).getText().clear();
        if (input.isEmpty()) {
            return;
        }
        else {
            MyCRUD.open();
            MyCRUD.insert(input);
            MyCRUD.close();
            initialize_view();
        }
    }

}
