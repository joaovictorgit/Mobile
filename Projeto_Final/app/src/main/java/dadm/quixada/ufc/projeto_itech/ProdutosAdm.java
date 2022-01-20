package dadm.quixada.ufc.projeto_itech;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import dadm.quixada.ufc.bottom_navigation_view.R;

public class ProdutosAdm extends AppCompatActivity {

    private Button list_prod, cad_produto;
    ImageView img_voltar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_adm);

        getSupportActionBar().hide();

        voltar();
        listarProdutos();
        cadastrarProdutos();
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

    private void listarProdutos(){
        list_prod = findViewById(R.id.Listar);

        list_prod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdutosAdm.this, ListProd.class);
                startActivity(intent);
            }
        });
    }

    private void cadastrarProdutos(){
        cad_produto = findViewById(R.id.btnCad_Produto);
        cad_produto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProdutosAdm.this, CadProduto.class);
                startActivity(intent);
            }
        });
    }
}