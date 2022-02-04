package dadm.quixada.ufc.projeto_itech.Produto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

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
import java.util.List;

import dadm.quixada.ufc.projeto_itech.Adapter.ProdutoAdapter;
import dadm.quixada.ufc.projeto_itech.Components.Produto;
import dadm.quixada.ufc.projeto_itech.Mapa.Maps;
import dadm.quixada.ufc.projeto_itech.R;
import dadm.quixada.ufc.projeto_itech.Usuario.UsuarioAdmin;

public class ListProd extends AppCompatActivity {
    //private List<Produto> prod;
    private ListView _listView;
    private ImageView img_voltar;

    RecyclerView recyclerView;
    List<Produto> produtos;
    RecyclerView.LayoutManager layoutManager;
    ProdutoAdapter produtoAdapter;
    FirebaseFirestore db;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prod);
        getSupportActionBar().hide();

        bottomNavigation = findViewById(R.id.bottomNavigation);
        db = FirebaseFirestore.getInstance();
        produtos = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
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
                        startActivity(new Intent(ListProd.this, UsuarioAdmin.class));
                        break;
                    case R.id.mapa:
                        startActivity(new Intent(ListProd.this, Maps.class));
                        break;
                }
                return false;
            }
        });
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
                                    documentSnapshot.getString("preco"),
                                    documentSnapshot.getString("quantidade"),
                                    documentSnapshot.getString("descricao")
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

    public void update(String id, String nome, String tipo ,String descricao, String preco, String quantidade){
        Intent intent = new Intent(ListProd.this, CadProduto.class);
        intent.putExtra("id", id);
        intent.putExtra("nome", nome);
        intent.putExtra("tipo", tipo);
        intent.putExtra("descricao", descricao);
        intent.putExtra("preco", preco);
        intent.putExtra("quantidade", quantidade);
        startActivity(intent);
        finish();
    }
}