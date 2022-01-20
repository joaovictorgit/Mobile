package dadm.quixada.ufc.projeto_itech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import dadm.quixada.ufc.projeto_itech.Adapter.AgendamentoAdapter;
import dadm.quixada.ufc.projeto_itech.Components.Agendamento;
import dadm.quixada.ufc.bottom_navigation_view.R;

public class ListAgendamentos extends AppCompatActivity {
    private ArrayList<Agendamento> agendamentos;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AgendamentoAdapter agendamentoAdapter;
    private ImageView img_voltar;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_agendamentos);
        getSupportActionBar().hide();


        agendamentos = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recycler_viewAgendamento);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        exibir();
        voltar();
    }

    private void exibir(){
        db.collection("Agendamento").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    agendamentos.clear();
                    for(DocumentSnapshot documentSnapshot: task.getResult()) {
                        Agendamento agendamento = new Agendamento(
                                documentSnapshot.getString("id"),
                                documentSnapshot.getString("nome"),
                                documentSnapshot.getString("descricao"),
                                documentSnapshot.getString("data"),
                                documentSnapshot.getString("check")
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
}