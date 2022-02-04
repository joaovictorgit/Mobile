package dadm.quixada.ufc.projeto_itech;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class Perfil extends AppCompatActivity {

    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private DocumentReference documentReference;

    private EditText edtText_nome, edtText_telefone, edtText_endereco, edtText_bairro;
    private Button button_atualizarPerfil;
    private ImageView imageView_profilePic;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        edtText_nome = findViewById(R.id.edtText_nome);
        edtText_telefone = findViewById(R.id.edtText_telefone);
        edtText_endereco = findViewById(R.id.edtText_endereco);
        edtText_bairro = findViewById(R.id.edtText_bairro);
        button_atualizarPerfil = findViewById(R.id.button_atualizarPerfil);
        imageView_profilePic = findViewById(R.id.imageView_profilePic);
        storageReference = FirebaseStorage.getInstance().getReference();

        StorageReference profileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(imageView_profilePic);
            }
        });

        loadData();

        button_atualizarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizarDados();
            }
        });

        imageView_profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent abrirGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(abrirGaleria, 1000);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == Activity.RESULT_OK){
            Uri uriDaImagem = data.getData();

            uploadDaImagemParaStorage(uriDaImagem);
        }

    }

    private void uploadDaImagemParaStorage(Uri uriDaImagem) {

        StorageReference fileRef = storageReference.child("users/"+fAuth.getCurrentUser().getUid()+"/profile.jpg");

        fileRef.putFile(uriDaImagem).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(imageView_profilePic);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadData() {
        String idUsuario = fAuth.getCurrentUser().getUid().toString();
        DocumentReference docRef = fStore.collection("Usuarios").document(idUsuario);


        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                edtText_nome.setText(document.getString("nome"));
                edtText_telefone.setText(document.getString("telefone"));
                edtText_endereco.setText(document.getString("endereco"));
                edtText_bairro.setText(document.getString("bairro"));
            }
        });

    }

    private void atualizarDados() {
        String nome = edtText_nome.getText().toString();
        String telefone = edtText_telefone.getText().toString();
        String endereco = edtText_endereco.getText().toString();
        String bairro = edtText_bairro.getText().toString();

        String idUsuario = fAuth.getCurrentUser().getUid().toString();
        DocumentReference docRef = fStore.collection("Usuarios").document(idUsuario);

        docRef.update("nome", nome, "telefone", telefone,
                "endereco", endereco, "bairro", bairro)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Perfil.this, HomeUsuario.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Infelizmente não foi possível atualizar seus dados!", Toast.LENGTH_SHORT).show();
                    }
                });



    }


}