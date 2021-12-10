package dadm.quixada.ufc.trabalho03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_add, btn_edit, btn_delete;
    private EditText editText;
    private ListView _listView;
    private List<Carro> car;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciar();
        cadastrar();
        editar();
        exibir();
        deletar();
    }

    private void iniciar(){
        btn_add = findViewById(R.id.btnCadastrar);
        btn_edit = findViewById(R.id.btnEditar);
        btn_delete = findViewById(R.id.btnDeletar);

        editText = (EditText) findViewById(R.id.identificador);

        _listView = findViewById(R.id.listView);

        db = FirebaseFirestore.getInstance();

        car = new ArrayList<>();
    }

    private void cadastrar(){
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    private void editar(){
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String campo = editText.getText().toString();
                Carro carro = new Carro();
                for(Carro c: car){
                    if(campo.equals(c.getNome())){
                        carro = c;
                    }
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("nome", carro.getNome());
                intent.putExtra("marca", carro.getMarca());
                intent.putExtra("cor", carro.getCor());
                startActivityForResult(intent, Constants.REQUEST_EDIT);
            }
        });

    }

    private void exibir(){
        db.collection("Carro").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        Carro carro = document.toObject(Carro.class);
                        car.add(carro);
                    }
                    _listView = findViewById(R.id.listView);
                    CarroAdapter carroAdapter = new CarroAdapter(MainActivity.this, car);
                    _listView.setAdapter(carroAdapter);
                    carroAdapter.notifyDataSetChanged();

                }else {
                    Log.d("MissionActivity", "Error getting documents: ", task.getException());
                }
            }
        });
    }

    private void deletar(){
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String campo = editText.getText().toString();
                Carro carro = new Carro();
                for(Carro c: car){
                    if(campo.equals(c.getNome())){
                        carro = c;
                    }
                }
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("nome", carro.getNome());
                intent.putExtra("marca", carro.getMarca());
                intent.putExtra("cor", carro.getCor());
                startActivityForResult(intent, Constants.REQUEST_EDIT);
            }
        });
    }
}