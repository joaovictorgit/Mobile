package dadm.quixada.ufc.trababalho_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        ListView myListView = findViewById(R.id.myList);
        // Populate the list View
        // Adaptar -1 Default & 2 Custom
        // ArrayAdapter and BaseAdapter

        String [] items = {"Som","Pager","LisView","AutoComplete","Spinner"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity3.this,
                android.R.layout.simple_list_item_1, items);

        myListView.setAdapter(arrayAdapter);

    }
}