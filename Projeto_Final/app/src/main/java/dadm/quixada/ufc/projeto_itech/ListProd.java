package dadm.quixada.ufc.projeto_itech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import dadm.quixada.ufc.projeto_itech.Adapter.ProdutoAdapter;
import dadm.quixada.ufc.projeto_itech.Components.Produto;
import dadm.quixada.ufc.bottom_navigation_view.R;

public class ListProd extends AppCompatActivity {
    //private List<Produto> prod;
    private ListView _listView;
    private ImageView img_voltar;

    RecyclerView recyclerView;
    List<Produto> produtos;
    RecyclerView.LayoutManager layoutManager;
    ProdutoAdapter produtoAdapter;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prod);
        getSupportActionBar().hide();

        db = FirebaseFirestore.getInstance();
        produtos = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        exibir();
        voltar();

    }

    private void exibir(){
        db.collection("Produto")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        produtos.clear();
                        for(DocumentSnapshot documentSnapshot: task.getResult()){
                            Produto prod = new Produto(
                                    documentSnapshot.getString("id"),
                                    documentSnapshot.getString("nome"),
                                    documentSnapshot.getString("tipo"),
                                    documentSnapshot.getString("preco")
                                    );
                            produtos.add(prod);
                        }
                        produtoAdapter = new ProdutoAdapter(ListProd.this, produtos);
                        recyclerView.setAdapter(produtoAdapter);
                    }
                });
    }

    public void deletar(int index){
        db.collection("Produto").document(produtos.get(index).getId())
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("LisProd", "Foi");
                        exibir();
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