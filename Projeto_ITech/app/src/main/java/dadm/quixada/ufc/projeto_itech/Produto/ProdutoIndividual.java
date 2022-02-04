package dadm.quixada.ufc.projeto_itech.Produto;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import dadm.quixada.ufc.projeto_itech.Pedido.MeusPedidos;
import dadm.quixada.ufc.projeto_itech.Pedido.Pedido;
import dadm.quixada.ufc.projeto_itech.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.UUID;

public class ProdutoIndividual extends AppCompatActivity {

    private TextView txtView_nomeProduto, txtView_precoProduto, textView_descricao;
    private Button btn_adicionarProduto;
    private ImageView imageView_imagemDoProduto;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto_individual);

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        txtView_nomeProduto = findViewById(R.id.txtView_nomeProduto);
        txtView_precoProduto = findViewById(R.id.txtView_precoProduto);
        textView_descricao = findViewById(R.id.textView_descricao);
        imageView_imagemDoProduto = findViewById(R.id.imageView_imagemDoProduto);
        btn_adicionarProduto = findViewById(R.id.btn_adicionarProduto);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            String id = bundle.getString("id");
            String nome = bundle.getString("nome");
            String preco = bundle.getString("preco");
            String descricao = bundle.getString("descricao");

            storageReference = FirebaseStorage.getInstance().getReference();
            StorageReference profileRef = storageReference.child("produtos/"+id+"/profile.jpg");

            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(imageView_imagemDoProduto);
                }
            });

            txtView_nomeProduto.setText(nome);
            txtView_precoProduto.setText(preco);
            textView_descricao.setText(descricao);
        }

        btn_adicionarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = UUID.randomUUID().toString();
                String idProduto = bundle.getString("id");
                String idUsuario = firebaseAuth.getCurrentUser().getUid().toString();


                Pedido pedido = new Pedido(id, idProduto, idUsuario);

                db.collection("Pedidos")
                        .document(id)
                        .set(pedido)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(), "Pedido realizado com sucesso!", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(ProdutoIndividual.this, MeusPedidos.class));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Infelizmente não foi possível concretizar seu pedido!" , Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });
    }
}