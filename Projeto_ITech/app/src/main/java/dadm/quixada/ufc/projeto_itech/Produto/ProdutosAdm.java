package dadm.quixada.ufc.projeto_itech.Produto;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dadm.quixada.ufc.projeto_itech.Mapa.Maps;
import dadm.quixada.ufc.projeto_itech.R;
import dadm.quixada.ufc.projeto_itech.Usuario.UsuarioAdmin;

public class ProdutosAdm extends AppCompatActivity {

    private Button list_prod, cad_produto;
    ImageView img_voltar;
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos_adm);

        getSupportActionBar().hide();
        bottomNavigation = findViewById(R.id.bottomNavigation);

        voltar();
        listarProdutos();
        cadastrarProdutos();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(ProdutosAdm.this, UsuarioAdmin.class));
                        break;
                    case R.id.mapa:
                        startActivity(new Intent(ProdutosAdm.this, Maps.class));
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