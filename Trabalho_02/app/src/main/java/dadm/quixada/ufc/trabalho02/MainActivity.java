package dadm.quixada.ufc.trabalho02;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import dadm.quixada.ufc.trabalho02.transactions.Carro;

public class MainActivity extends AppCompatActivity {

    int selected;
    ArrayList<Carro> listaCarro;
    ArrayAdapter adapter;
    ListView listViewCarro;
    Button btn_add, btn_edit;
    EditText campo_nome, edt_nome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selected = -1;
        listaCarro = new ArrayList<Carro>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaCarro);
        listViewCarro = findViewById(R.id.listView);
        listViewCarro.setAdapter(adapter);
        listViewCarro.setSelector(android.R.color.holo_blue_light);

        /*listViewCarro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selected = i;
            }
        });*/
        clicarAdd();
        clicarEdit();

    }

    public void clicarAdd(){
        //int contador = 0;
        edt_nome = findViewById(R.id.identificador);

        btn_add = findViewById(R.id.btnCadastrar);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("id", 0);
                intent.putExtra("nome", edt_nome.getText().toString());
                intent.putExtra("marca", "");
                intent.putExtra("cor", "");
                startActivityForResult(intent, Constants.REQUEST_ADD);
            }
        });

    }

    public void clicarEdit(){
        btn_edit = findViewById(R.id.btnEditar);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                campo_nome = findViewById(R.id.identificador);
                String nome = campo_nome.getText().toString();
                Carro carro = new Carro();
                for(Carro carro1 : listaCarro){
                    if(nome.equals(carro1.getNome())){
                        carro = carro1;
                    }
                }

                /*
                Carro carro = listaCarro.get(selected);
                */
                intent.putExtra("id", carro.getId());
                intent.putExtra("nome", carro.getNome());
                intent.putExtra("marca", carro.getMarca());
                intent.putExtra("cor", carro.getCor());

                startActivityForResult(intent, Constants.REQUEST_EDIT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Retorno da tela contatos com um conteudo para ser adicionado
        // Na segunda tela, o usuário clicou no botao ADD
        if (requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD) {
            String nome = (String) data.getExtras().get("nome");
            String marca = (String) data.getExtras().get("marca");
            String cor = (String) data.getExtras().get("cor");

            Carro carro = new Carro(nome, marca, cor);

            listaCarro.add(carro);
            adapter.notifyDataSetChanged();
        } else if (resultCode == Constants.RESULT_ADD && requestCode == Constants.REQUEST_EDIT) {
            String nome = (String) data.getExtras().get("nome");
            String marca = (String) data.getExtras().get("marca");
            String cor = (String) data.getExtras().get("cor");

            int id = (int) data.getExtras().get("id");

            for(Carro carro : listaCarro){
                if(carro.getId() == id){
                    carro.setNome(nome);
                    carro.setMarca(marca);
                    carro.setCor(cor);
                }
            }
            adapter.notifyDataSetChanged();
        }
        else if (resultCode == Constants.RESULT_CANCEL) {
            Toast.makeText(this, "Cancelar", Toast.LENGTH_LONG).show();
        }

    }

    /*private boolean verifica(){
        boolean result = true;
        campo_nome = findViewById(R.id.identificador);
        String nome = campo_nome.getText().toString();
        for(Carro carro1 : listaCarro){
            if(nome.equals(carro1.getNome())){
                result = false;
            }
        }
        return result;
    }*/

}