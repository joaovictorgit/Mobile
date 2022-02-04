package dadm.quixada.ufc.projeto_itech.Usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import dadm.quixada.ufc.projeto_itech.Agendamento.ListAgendamentos;
import dadm.quixada.ufc.projeto_itech.Agendamento.CadAgendamento;
import dadm.quixada.ufc.projeto_itech.Mapa.Maps;
import dadm.quixada.ufc.projeto_itech.Produto.CadProduto;
import dadm.quixada.ufc.projeto_itech.Produto.ListProd;
import dadm.quixada.ufc.projeto_itech.Produto.ProdutosAdm;
import dadm.quixada.ufc.projeto_itech.R;

public class UsuarioAdmin extends AppCompatActivity {

    private Button btn_tela_produto, btn_tela_listar_produtos, btn_cad_produto, btn_cad_agend, btn_listar_agend;
    private BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_admin);
        getSupportActionBar().hide();

        bottomNavigation = findViewById(R.id.bottomNavigation);

        telaProduto();
        listarProdutos();
        listarAgendamentos();
        cadastrarProduto();
        cadastrarAgendamento();

        bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(UsuarioAdmin.this, UsuarioAdmin.class));

                    case R.id.mapa:
                        startActivity(new Intent(UsuarioAdmin.this, Maps.class));
                    default:
                        break;
                }
                return false;
            }
        });
    }

    private void telaProduto(){
        btn_tela_produto = findViewById(R.id.telaProdutos);
        btn_tela_produto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioAdmin.this, ProdutosAdm.class);
                startActivity(intent);
            }
        });
    }

    private void listarAgendamentos(){
        btn_listar_agend = findViewById(R.id.btnListar_Agendamentos);
        btn_listar_agend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioAdmin.this, ListAgendamentos.class);
                startActivity(intent);
            }
        });
    }

    private void listarProdutos() {
        btn_tela_listar_produtos = findViewById(R.id.btnListar_Produto);
        btn_tela_listar_produtos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioAdmin.this, ListProd.class);
                startActivity(intent);
            }
        });
    }

    private void cadastrarProduto(){
        btn_cad_produto = findViewById(R.id.btnCad_Produto);
        btn_cad_produto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioAdmin.this, CadProduto.class);
                startActivity(intent);
            }
        });
    }

    private void cadastrarAgendamento(){
        btn_cad_agend = findViewById(R.id.Agendamentos);
        btn_cad_agend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UsuarioAdmin.this, CadAgendamento.class);
                startActivity(intent);
            }
        });
    }
}