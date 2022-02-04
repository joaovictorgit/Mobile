package dadm.quixada.ufc.projeto_itech.Produto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dadm.quixada.ufc.projeto_itech.Mapa.Maps;
import dadm.quixada.ufc.projeto_itech.R;
import dadm.quixada.ufc.projeto_itech.Usuario.UsuarioAdmin;

public class CadProduto extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spn_produtos, spn_subcategoria;
    EditText edt_descricao, edt_preco, edt_qtd;
    String categoria, sub_categoria, descricao, preco, quantidade;
    Button addProduto;
    FirebaseFirestore db;
    ImageView img_voltar, iconImage;
    BottomNavigationView bottomNavigation;
    private static final int PICK_IMAGE_REQUEST = 1;
    Uri mImageUri;

    StorageReference storageReference;
    String id,id_image;
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
        iconImage = findViewById(R.id.iconImage);
        db = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        bottomNavigation = findViewById(R.id.bottomNavigation);

        voltar();



        if(getIntent().getExtras() != null){
            addProduto.setText("Atualizar");
            String id_update = (String) getIntent().getExtras().get("id");
            String desc = (String) getIntent().getExtras().get("descricao");
            String qtd = (String) getIntent().getExtras().get("quantidade");
            String preco = (String) getIntent().getExtras().get("preco");
            String nome = (String) getIntent().getExtras().get("nome");
            String tipo = (String) getIntent().getExtras().get("tipo");

            edt_descricao.setText(desc);
            edt_preco.setText(preco);
            edt_qtd.setText(qtd);

            addProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String new_preco = edt_preco.getText().toString();
                    String new_qtd = edt_qtd.getText().toString();
                    String new_desc = edt_descricao.getText().toString();
                    updateProduto(id_update, nome, tipo, new_preco, new_qtd, new_desc);
                }
            });
        }
        else{
            id = UUID.randomUUID().toString();
            id_image = id;
            addProduto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    descricao = edt_descricao.getText().toString();
                    preco = "R$ " + edt_preco.getText().toString();
                    quantidade = edt_qtd.getText().toString();
                    categoria = spn_produtos.getSelectedItem().toString();
                    sub_categoria = spn_subcategoria.getSelectedItem().toString();
                    Log.d("CadProduto", categoria + " " + sub_categoria);
                    adicionarProduto(categoria, sub_categoria, descricao, preco, quantidade);

                }
            });
        }

        iconImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                escolherImagem();
            }
        });

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(CadProduto.this, UsuarioAdmin.class));
                        break;
                    case R.id.mapa:
                        startActivity(new Intent(CadProduto.this, Maps.class));
                        break;
                }
                return false;
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

        Map<String, Object> produtos = new HashMap<>();
        //Intent intent = new Intent();

        //intent.putExtra("id_imagem", id_image);
        produtos.put("id", id);
        produtos.put("nome", categoria);
        produtos.put("tipo", subcategoria);
        produtos.put("descricao", descricao);
        produtos.put("preco", preco);
        produtos.put("quantidade", quantidade);
        produtos.put("imagem", id_image);

        db.collection("Produto").document(id).set(produtos)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CadProduto.this, "Produto adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                        edt_descricao.setText("");
                        edt_preco.setText("");
                        edt_qtd.setText("");
                        startActivity(new Intent(CadProduto.this, ListProd.class));
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

    private void updateProduto(String id, String categoria, String subcategoria, String preco, String quantidade, String descricao){
        Log.d("CadProduto", id);
        Map<String, Object> produtos = new HashMap<>();

        produtos.put("id", id);
        produtos.put("nome", categoria);
        produtos.put("tipo", subcategoria);
        produtos.put("descricao", descricao);
        produtos.put("preco", preco);
        produtos.put("quantidade", quantidade);

        db.collection("Produto").document(id).update(produtos).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(CadProduto.this, "Produto atualizado com sucesso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CadProduto.this, ListProd.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void escolherImagem(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
            && data != null && data.getData() != null){
            mImageUri = data.getData();
            Log.d("CadProduto", "Id da image: " + id_image);
            StorageReference arquivos = storageReference.child("produtos/"+id_image+"/profile.jpg");
            arquivos.putFile(mImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    Picasso.get().load(mImageUri).into(iconImage);
                }
            });
        }
    }
}