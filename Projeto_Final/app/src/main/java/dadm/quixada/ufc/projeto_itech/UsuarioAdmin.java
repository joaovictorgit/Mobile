package dadm.quixada.ufc.projeto_itech;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import dadm.quixada.ufc.bottom_navigation_view.R;

public class UsuarioAdmin extends AppCompatActivity {

    private Button btn_tela_produto, btn_tela_listar_produtos, btn_cad_produto, btn_cad_agend, btn_listar_agend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_admin);
        getSupportActionBar().hide();

        telaProduto();
        listarProdutos();
        listarAgendamentos();
        cadastrarProduto();
        cadastrarAgendamento();

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