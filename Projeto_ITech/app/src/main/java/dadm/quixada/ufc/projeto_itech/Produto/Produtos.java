package dadm.quixada.ufc.projeto_itech.Produto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.UUID;

import dadm.quixada.ufc.projeto_itech.R;
public class Produtos extends AppCompatActivity {

    private static final int numberOfColumns = 2;

    private RecyclerView recyclerView_produtos;
    private RecyclerViewProdutosAdapter adapter;
    private ArrayList<ProdutoModel> listaDeProdutos = new ArrayList<ProdutoModel>();

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        db = FirebaseFirestore.getInstance();

        recyclerView_produtos = findViewById(R.id.recyclerView_produtos);

        String id = UUID.randomUUID().toString();

        recyclerView_produtos.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        showData();

    }

    private void showData() {

        db.collection("Produto")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        listaDeProdutos.clear();

                        for(DocumentSnapshot doc: task.getResult()){
                            ProdutoModel produto = new ProdutoModel(doc.getString("id"),
                                    doc.getString("nome"), doc.getString("tipo"),
                                    doc.getString("preco"), doc.getString("quantidade"),
                                    doc.getString("descricao"));

                            listaDeProdutos.add(produto);
                        }

                        adapter = new RecyclerViewProdutosAdapter(Produtos.this, listaDeProdutos);

                        recyclerView_produtos.setAdapter(adapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Produtos.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}