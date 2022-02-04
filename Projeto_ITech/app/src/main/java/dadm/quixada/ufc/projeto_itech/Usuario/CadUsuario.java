package dadm.quixada.ufc.projeto_itech.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import dadm.quixada.ufc.projeto_itech.MainActivity;
import dadm.quixada.ufc.projeto_itech.R;

public class CadUsuario extends AppCompatActivity {

    private ImageView img_voltar;
    private Button cadastrarUsuario;
    private EditText edt_Nome;
    private EditText edt_Email;
    private EditText edt_Senha;
    private EditText edt_Telefone;
    private EditText edt_Rua;
    private EditText edt_Numero;
    private EditText edt_Bairro;

    FirebaseFirestore db;
    FirebaseAuth auth;
    String userId;
    DocumentReference documentReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        getSupportActionBar().hide();

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        edt_Nome = findViewById(R.id.edtNome);
        edt_Email = findViewById(R.id.edtEmail);
        edt_Senha = findViewById(R.id.edtSenha);
        edt_Telefone = findViewById(R.id.edtNumero);
        edt_Rua = findViewById(R.id.edtRua);
        edt_Numero = findViewById(R.id.edtNumeroRua);
        edt_Bairro = findViewById(R.id.edtBairro);
        cadastrarUsuario = findViewById(R.id.cadastrarUsuario);

        voltar();

        cadastrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edt_Nome.getText().toString();
                String email = edt_Email.getText().toString();
                String senha = edt_Senha.getText().toString();
                String telefone = edt_Telefone.getText().toString();
                String rua = edt_Rua.getText().toString();
                String n_rua = edt_Numero.getText().toString();
                String bairro = edt_Bairro.getText().toString();

                adicionarUsuario(nome, email, senha, telefone, rua, n_rua, bairro);
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

    private void adicionarUsuario(String nome, String email, String senha, String telefone, String rua, String n_rua, String bairro){
        Map<String, Object> usuarios = new HashMap<>();
        //String id = UUID.randomUUID().toString();



        auth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("CadUsuario", "Email autenticado com sucesso");
                        userId = auth.getCurrentUser().getUid();
                        documentReference = db.collection("Usuarios").document(userId);
                        usuarios.put("id", userId);
                        usuarios.put("nome", nome);
                        usuarios.put("email", email);
                        usuarios.put("senha", senha);
                        usuarios.put("telefone", telefone);
                        usuarios.put("endereco", rua);
                        usuarios.put("n_rua", n_rua);
                        usuarios.put("bairro", bairro);

                        documentReference.set(usuarios)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            Toast.makeText(CadUsuario.this, "Usuario adicionado com sucesso!", Toast.LENGTH_SHORT).show();
                                            edt_Nome.setText("");
                                            edt_Email.setText("");
                                            edt_Senha.setText("");
                                            edt_Telefone.setText("");
                                            edt_Rua.setText("");
                                            edt_Numero.setText("");
                                            edt_Bairro.setText("");

                                            Intent intent = new Intent(CadUsuario.this, MainActivity.class);
                                            startActivity(intent);
                                        }

                                    }
                                });
                    }
                });


    }
}