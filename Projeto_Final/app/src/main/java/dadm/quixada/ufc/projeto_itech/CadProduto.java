package dadm.quixada.ufc.projeto_itech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dadm.quixada.ufc.bottom_navigation_view.R;

public class CadProduto extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spn_produtos, spn_subcategoria;
    EditText edt_descricao, edt_preco, edt_qtd;
    String categoria, sub_categoria, descricao, preco, quantidade;
    Button addProduto;
    FirebaseFirestore db;
    ImageView img_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_produto);
        getSupportActionBar().hide();

        spn_produtos = findViewById(R.id.SpinnerProdutos);
        spn_subcategoria = findViewById(R.id.SpinnerSubCategoria);
        edt_descricao = findViewById(R.id.edtDescricao);
        edt_preco = findViewById(R.id.edtPreco);
        edt_qtd = findViewById(R.id.edtQtd);
        addProduto = findViewById(R.id.btnCadProdAdmin);
        db = FirebaseFirestore.getInstance();

        voltar();

        addProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descricao = edt_descricao.getText().toString();
                preco = "R$ " + edt_preco.getText().toString();
                quantidade = edt_qtd.getText().toString();
                categoria = spn_produtos.getSelectedItem().toString();
                sub_categoria = spn_subcategoria.getSelectedItem().toString();
                Log.d("CadProduto", categoria + " " + sub_categoria);
                adicionarProduto(categoria, sub_categoria,descricao, preco, quantidade);
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


    private void adicionarProduto(String categoria, String subcategoria, String descricao, String preco, String quantidade) {
        String id = UUID.randomUUID().toString();
        Map<String, Object> produtos = new HashMap<>();

        produtos.put("id", id);
        produtos.put("nome", categoria);
        produtos.put("tipo", subcategoria);
        produtos.put("descricao", descricao);
        produtos.put("preco", preco);
        produtos.put("quantidade", quantidade);

        db.collection("Produto").document(id).set(produtos)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CadProduto.this, "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                        edt_descricao.setText("");
                        edt_preco.setText("");
                        edt_qtd.setText("");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Erro ao adicionar o produto", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}