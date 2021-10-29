package dadm.quixada.ufc.trababalho_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class GridItemActivity extends AppCompatActivity {

    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_item);

        textView = findViewById(R.id.griddata);
        imageView = findViewById(R.id.imageView);

        Intent intent = getIntent();
        textView.setText(intent.getStringExtra("name"));
        imageView.setImageResource(intent.getIntExtra("image",0));


    }
}