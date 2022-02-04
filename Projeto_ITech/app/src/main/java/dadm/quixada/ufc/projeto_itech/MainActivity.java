package dadm.quixada.ufc.projeto_itech;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import dadm.quixada.ufc.projeto_itech.Usuario.CadUsuario;
import dadm.quixada.ufc.projeto_itech.Usuario.UsuarioAdmin;

public class MainActivity extends AppCompatActivity {

    private Button btn_logar, btn_cad;
    private EditText email, senha;

    FirebaseAuth auth;
    String user_email, user_senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        email = findViewById(R.id.campoEmail);
        senha = findViewById(R.id.campoSenha);
        auth = FirebaseAuth.getInstance();



        logar();
        cadastrar();
    }

    private void logar(){

        btn_logar = findViewById(R.id.btnEntrar);
        btn_logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_email = email.getText().toString();
                user_senha = senha.getText().toString();

                String partys[] = user_email.split("@");

                auth.signInWithEmailAndPassword(user_email, user_senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            if(partys[1].equals("itech.com")){
                                Intent intent = new Intent(MainActivity.this, UsuarioAdmin.class);
                                startActivity(intent);
                            }else{
                                // Vai para tela de usuário
                                Intent intent = new Intent(MainActivity.this, HomeUsuario.class);
                                startActivity(intent);
                            }

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Email ou Senha incorreto(s)", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void cadastrar(){
        btn_cad = findViewById(R.id.btnCadastrar);
        btn_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadUsuario.class);
                startActivity(intent);
            }
        });
    }

}