package dadm.quixada.ufc.projeto_itech.Agendamento;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

import dadm.quixada.ufc.projeto_itech.Mapa.Maps;
import dadm.quixada.ufc.projeto_itech.R;
import dadm.quixada.ufc.projeto_itech.Usuario.UsuarioAdmin;

public class DadosAgendamento extends AppCompatActivity {

    ImageView img_check, img_delete, img_update;
    EditText edtDispositivo, edtModelo, edtData, edtHorario, edtDescricao;
    FirebaseFirestore db;
    BottomNavigationView bottomNavigation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_agendamento);

        img_check = findViewById(R.id.check);
        img_delete = findViewById(R.id.delete);
        img_update = findViewById(R.id.update);
        edtDispositivo = findViewById(R.id.edtDispositivo);
        edtModelo = findViewById(R.id.edtModelo);
        edtData = findViewById(R.id.edtData);
        edtHorario = findViewById(R.id.edtHorario);
        edtDescricao = findViewById(R.id.edtDescricao);
        db = FirebaseFirestore.getInstance();
        bottomNavigation = findViewById(R.id.bottomNavigation);
        /*
        intent.putExtra("id", id);
        intent.putExtra("nome", nome);
        intent.putExtra("modelo", modelo);
        intent.putExtra("descricao", descricao);
        intent.putExtra("data", data);
        intent.putExtra("horario", horario);
        intent.putExtra("check", check);
        */
        if(getIntent().getExtras() != null){
            String id = (String) getIntent().getExtras().get("id");
            String nome = (String) getIntent().getExtras().get("nome");
            String modelo = (String) getIntent().getExtras().get("modelo");
            String desc = (String) getIntent().getExtras().get("descricao");
            String data = (String) getIntent().getExtras().get("data");
            String hora = (String) getIntent().getExtras().get("horario");
            String check = (String) getIntent().getExtras().get("check");

            if(check.equals("Concluido")){
                img_check.setAlpha(1.0F);
            }
            if(check.equals("Pendente")){
                img_update.setAlpha(1.0F);
            }
            if(check.equals("Excluido")){
                img_delete.setAlpha(1.0F);
            }

            edtDispositivo.setText(nome);
            edtModelo.setText(modelo);
            edtData.setText(data);
            edtHorario.setText(hora);
            edtDescricao.setText(desc);

            img_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    atualizar(id, "Concluido");
                }
            });

            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    atualizar(id, "Excluido");
                }
            });


        }

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(DadosAgendamento.this, UsuarioAdmin.class));
                        break;
                    case R.id.mapa:
                        startActivity(new Intent(DadosAgendamento.this, Maps.class));
                        break;
                }
                return false;
            }
        });
    }
    private void atualizar(String id, String check){
        db.collection("Agendamentos").document(id).update(
                "status", check
        ).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(DadosAgendamento.this, "Agendamento atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DadosAgendamento.this, ListAgendamentos.class);
                startActivity(intent);
                finish();
            }
        });
    }
}