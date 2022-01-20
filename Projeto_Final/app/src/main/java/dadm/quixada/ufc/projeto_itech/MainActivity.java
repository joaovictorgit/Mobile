package dadm.quixada.ufc.projeto_itech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dadm.quixada.ufc.bottom_navigation_view.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_logar, btn_cad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        logar();
        cadastrar();
    }

    private void logar(){
        btn_logar = findViewById(R.id.btnEntrar);
        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UsuarioAdmin.class);
                startActivity(intent);
            }
        });
    }

    private void cadastrar(){
        btn_cad = findViewById(R.id.btnCadastrar);
        btn_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadUsuario.class);
                startActivity(intent);
            }
        });
    }

}