package dadm.quixada.ufc.trabalho02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    public static int RESULT_ADD = 1;
    public static int RESULT_CANCEL = 2;

    EditText edtNome;
    EditText edtMarca;
    EditText edtCor;

    boolean edit;
    int id;

    Button btn_adicionar, btn_cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtNome = findViewById(R.id.editTextNome);
        edtMarca = findViewById(R.id.editTextMarca);
        edtCor = findViewById(R.id.editTextCor);

        edit = false;

        if(getIntent().getExtras() != null){
            String nome = (String)getIntent().getExtras().get("nome");
            String marca = (String)getIntent().getExtras().get("marca");
            String cor = (String)getIntent().getExtras().get("cor");
            id = (int)getIntent().getExtras().get("id");

            edtNome.setText(nome);
            if(!marca.equals("") && !cor.equals("")){
                edtMarca.setText(marca);
                edtCor.setText(cor);
            }
            edit = true;

        }
        adicionar();
        cancelar();
    }

    public void cancelar() {
        btn_cancelar = findViewById(R.id.bt_cancel);

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Constants.RESULT_CANCEL);
                finish();
            }
        });
    }

    public void adicionar() {
        btn_adicionar = findViewById(R.id.bt_add_edit);

        btn_adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();


                String nome = edtNome.getText().toString();
                String marca = edtMarca.getText().toString();
                String cor = edtCor.getText().toString();


                intent.putExtra("nome", nome);
                intent.putExtra("marca", marca);
                intent.putExtra("cor", cor);

                if(edit){
                    intent.putExtra("id",id);
                }

                setResult(Constants.RESULT_ADD, intent);
                finish();
            }
        });

    }
}