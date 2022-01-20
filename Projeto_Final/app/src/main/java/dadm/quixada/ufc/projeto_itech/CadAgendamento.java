package dadm.quixada.ufc.projeto_itech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dadm.quixada.ufc.bottom_navigation_view.R;

public class CadAgendamento extends AppCompatActivity {

    EditText nome_disp, modelo_disp, data, horario, descricao;
    FirebaseFirestore db;
    Button btn_cadastrar;
    ImageView img_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_agendamento);
        getSupportActionBar().hide();

        nome_disp = findViewById(R.id.edtDispositivo);
        modelo_disp = findViewById(R.id.edtModelo);
        data = findViewById(R.id.edtData);
        horario = findViewById(R.id.edtHorario);
        descricao = findViewById(R.id.edtDescricao);
        db = FirebaseFirestore.getInstance();
        btn_cadastrar = findViewById(R.id.cadAgendamento);

        btn_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = nome_disp.getText().toString();
                String modelo = modelo_disp.getText().toString();
                String _data = data.getText().toString();
                String _horario = horario.getText().toString();
                String desc = descricao.getText().toString();

                cadastrarAgendamento(nome, modelo, _data, _horario, desc);
            }
        });

        voltar();
    }

    private void voltar(){
        img_voltar = findViewById(R.id.back);
        img_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void cadastrarAgendamento(String nome, String modelo, String _data, String _horario, String desc){
        String id = UUID.randomUUID().toString();
        Map<String, Object> agendamentos = new HashMap<>();
        agendamentos.put("id", id);
        agendamentos.put("nome_dispositivo", nome);
        agendamentos.put("modelo_dispositivo", modelo);
        agendamentos.put("data", _data);
        agendamentos.put("horario", _horario);
        agendamentos.put("descricao", desc);
        agendamentos.put("check", "pendente");

        db.collection("Agendamento").document(id).set(agendamentos)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CadAgendamento.this, "Agendamento adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                        nome_disp.setText("");
                        modelo_disp.setText("");
                        data.setText("");
                        horario.setText("");
                        descricao.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CadAgendamento.this, "Erro ao agendar", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}