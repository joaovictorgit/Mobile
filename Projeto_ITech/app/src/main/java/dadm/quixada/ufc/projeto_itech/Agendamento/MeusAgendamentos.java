package dadm.quixada.ufc.projeto_itech.Agendamento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import dadm.quixada.ufc.projeto_itech.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MeusAgendamentos extends AppCompatActivity {

    private ArrayList<AgendamentoModel> listaDeAgendamentos = new ArrayList<AgendamentoModel>();
    private RecyclerView recyclerView_agendamentos;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapterAgendamentos adapter;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_agendamentos);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView_agendamentos = findViewById(R.id.recyclerView_agendamentos);
        recyclerView_agendamentos.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView_agendamentos.setLayoutManager(layoutManager);

        showData();

    }

    private void showData() {

        String idDoUsuario = firebaseAuth.getCurrentUser().getUid().toString();

        db.collection("Agendamentos")
                .whereEqualTo("idUsuario", idDoUsuario)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listaDeAgendamentos.clear();

                        for(DocumentSnapshot doc: task.getResult()){
                            AgendamentoModel agendamento = new AgendamentoModel(doc.getString("id"),
                                    doc.getString("idUsuario"), doc.getString("tipoDispositivo"),
                                    doc.getString("modeloDispositivo"), doc.getString("problema"),
                                    doc.getString("opcaoEntrega"), doc.getString("dia"),
                                    doc.getString("horario"), doc.getString("status"));

                            listaDeAgendamentos.add(agendamento);
                        }

                        adapter = new CustomAdapterAgendamentos(MeusAgendamentos.this, listaDeAgendamentos);

                        recyclerView_agendamentos.setAdapter(adapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MeusAgendamentos.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}