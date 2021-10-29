package dadm.quixada.ufc.trababalho_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity6 extends AppCompatActivity {

    ToggleButton toggleButton1, toggleButton2;
    Button btnDisplay;
    RadioGroup rdoGroup;
    RadioButton rdoButton;
    TextView txtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        // ToggleButton
        addListenerOnButton();
        // RadioButton
        txtView = findViewById(R.id.txtSelect);
        rdoGroup = findViewById(R.id.rdo_Group);
        Button btnApply = findViewById(R.id.btn_aplicar);
        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = rdoGroup.getCheckedRadioButtonId();
                rdoButton = findViewById(radioId);

                txtView.setText("You choice: " + rdoButton.getText());
            }
        });

    }

    public void addListenerOnButton(){
        toggleButton1 = (ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuffer result = new StringBuffer();
                result.append("toggleButton1 : ").append(toggleButton1.getText());
                result.append("\ntoggleButton2 : ").append(toggleButton2.getText());

                Toast.makeText(MainActivity6.this, result.toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void checkButton(View view){
        int radioId = rdoGroup.getCheckedRadioButtonId();
        rdoButton = findViewById(radioId);

        Toast.makeText(this, "Selected Radio Button: " + rdoButton.getText(), Toast.LENGTH_SHORT).show();
    }
}