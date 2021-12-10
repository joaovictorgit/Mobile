package dadm.quixada.ufc.trabalho03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    private EditText nome, marca, cor;
    private Button add_edit, delete;
    private ArrayList<Carro> car;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db = FirebaseFirestore.getInstance();

        nome = (EditText) findViewById(R.id.editTextNome);
        marca = (EditText) findViewById(R.id.editTextMarca);
        cor = (EditText) findViewById(R.id.editTextCor);

        add_edit = findViewById(R.id.bt_add_edit);
        delete = findViewById(R.id.bt_cancel);

        car = new ArrayList<>();

        if(getIntent().getExtras() != null){
            String id = (String) getIntent().getExtras().get("nome");
            Log.d("MainActivity2", "Esse nome é " + id);

            String nome1 = (String) getIntent().getExtras().get("nome");
            String marca1 = (String) getIntent().getExtras().get("marca");
            String cor1 = (String) getIntent().getExtras().get("cor");

            nome.setText(nome1);
            marca.setText(marca1);
            cor.setText(cor1);

            add_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String _nome = nome.getText().toString();
                    String _marca = marca.getText().toString();
                    String _cor = cor.getText().toString();

                    Map<String, Object> new_car = new HashMap<>();
                    new_car.put("nome", _nome);
                    new_car.put("marca", _marca);
                    new_car.put("cor", _cor);

                    Log.d("MainActivity2", "Esse nome é " + _nome + " " + _marca + " " + _cor);
                    DocumentReference df = db.collection("Carro").document(_nome);

                    db.collection("Carro").whereEqualTo("nome", nome1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();
                            db.collection("Carro").document(documentID).delete().addOnSuccessListener(
                                    new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            df.set(new_car).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {
                                                    Toast.makeText(MainActivity2.this, "Dados atualizados com sucesso!", Toast.LENGTH_SHORT).show();
                                                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                        }
                                    }
                            );
                        }
                    });




                }
            });
        }
        else {
            add_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String _nome = nome.getText().toString();
                    String _marca = marca.getText().toString();
                    String _cor = cor.getText().toString();

                    car.add(new Carro(_nome,_marca,_cor));

                    Map<String, Object> carro = new HashMap<>();
                    carro.put("nome", _nome);
                    carro.put("marca", _marca);
                    carro.put("cor", _cor);

                    db.collection("Carro").document(_nome).set(carro).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(MainActivity2.this, "Dados cadastrados com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                            startActivity(intent);
                        }
                    });

                }
            });
        }

        deletar();
    }
    private void deletar(){
        if(getIntent().getExtras() != null) {
            String id = (String) getIntent().getExtras().get("nome");
            Log.d("MainActivity2", "Esse nome é " + id);

            String nome1 = (String) getIntent().getExtras().get("nome");
            String marca1 = (String) getIntent().getExtras().get("marca");
            String cor1 = (String) getIntent().getExtras().get("cor");

            nome.setText(nome1);
            marca.setText(marca1);
            cor.setText(cor1);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.collection("Carro").whereEqualTo("nome", nome1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            DocumentSnapshot documentSnapshot = task.getResult().getDocuments().get(0);
                            String documentID = documentSnapshot.getId();
                            db.collection("Carro").document(documentID).delete().addOnSuccessListener(
                                    new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(MainActivity2.this, "Dados deletados com sucesso", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                                            startActivity(intent);
                                        }
                                    }
                            );
                        }
                    });
                }
            });
        }
    }
}