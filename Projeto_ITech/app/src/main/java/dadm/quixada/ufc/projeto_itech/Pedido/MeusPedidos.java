package dadm.quixada.ufc.projeto_itech.Pedido;


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

import dadm.quixada.ufc.projeto_itech.R;

public class MeusPedidos extends AppCompatActivity {

    private ArrayList<Pedido> listaDePedidos = new ArrayList<Pedido>();
    private RecyclerView recyclerView_pedidos;
    private RecyclerView.LayoutManager layoutManager;
    private CustomAdapterPedido adapter;

    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_pedidos);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        recyclerView_pedidos = findViewById(R.id.recyclerView_pedidos);
        recyclerView_pedidos.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView_pedidos.setLayoutManager(layoutManager);

        showData();

    }

    private void showData() {

        String idDoUsuario = firebaseAuth.getCurrentUser().getUid();

        db.collection("Pedidos")
                .whereEqualTo("idUsuario", idDoUsuario)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        listaDePedidos.clear();

                        for(DocumentSnapshot doc: task.getResult()){
                            Pedido pedido = new Pedido(doc.getString("id"),
                                    doc.getString("idProduto"), doc.getString("idUsuario"));

                            listaDePedidos.add(pedido);
                        }

                        adapter = new CustomAdapterPedido(MeusPedidos.this, listaDePedidos);

                        recyclerView_pedidos.setAdapter(adapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MeusPedidos.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }


}