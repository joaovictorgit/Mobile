package dadm.quixada.ufc.projeto_itech.Agendamento;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import dadm.quixada.ufc.projeto_itech.Adapter.AgendamentoAdapter;
import dadm.quixada.ufc.projeto_itech.Components.Agendamento;
import dadm.quixada.ufc.projeto_itech.Mapa.Maps;
import dadm.quixada.ufc.projeto_itech.R;
import dadm.quixada.ufc.projeto_itech.Usuario.UsuarioAdmin;

public class ListAgendamentos extends AppCompatActivity {
    private ArrayList<AgendamentoModel> agendamentos;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AgendamentoAdapter agendamentoAdapter;
    private ImageView img_voltar;
    private BottomNavigationView bottomNavigation;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_agendamentos);
        getSupportActionBar().hide();

        bottomNavigation = findViewById(R.id.bottomNavigation);
        agendamentos = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_viewAgendamento);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        exibir();
        voltar();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(ListAgendamentos.this, UsuarioAdmin.class));
                        break;
                    case R.id.mapa:
                        startActivity(new Intent(ListAgendamentos.this, Maps.class));
                        break;
                }
                return false;
            }
        });
    }

    private void exibir(){
        db.collection("Agendamentos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    agendamentos.clear();
                    for(DocumentSnapshot documentSnapshot: task.getResult()) {
                        AgendamentoModel agendamento = new AgendamentoModel(
                                documentSnapshot.getString("id"),
                                documentSnapshot.getString("idUsuario"),
                                documentSnapshot.getString("tipoDispositivo"),
                                documentSnapshot.getString("modeloDispositivo"),
                                documentSnapshot.getString("problema"),
                                documentSnapshot.getString("opcaoEntrega"),
                                documentSnapshot.getString("dia"),
                                documentSnapshot.getString("horario"),
                                documentSnapshot.getString("status")
                        );
                        agendamentos.add(agendamento);
                    }
                    agendamentoAdapter = new AgendamentoAdapter(ListAgendamentos.this, agendamentos);
                    recyclerView.setAdapter(agendamentoAdapter);
                }
            }
        });
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

    public void update(String id, String nome, String modelo, String descricao, String data, String horario , String check){
        Intent intent = new Intent(ListAgendamentos.this, DadosAgendamento.class);
        intent.putExtra("id", id);
        intent.putExtra("nome", nome);
        intent.putExtra("modelo", modelo);
        intent.putExtra("descricao", descricao);
        intent.putExtra("data", data);
        intent.putExtra("horario", horario);
        intent.putExtra("check", check);
        startActivity(intent);
        finish();
    }
}