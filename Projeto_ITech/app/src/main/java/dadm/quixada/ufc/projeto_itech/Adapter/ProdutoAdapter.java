package dadm.quixada.ufc.projeto_itech.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dadm.quixada.ufc.projeto_itech.R;
import dadm.quixada.ufc.projeto_itech.Components.Produto;
import dadm.quixada.ufc.projeto_itech.Produto.ListProd;
import dadm.quixada.ufc.projeto_itech.Holder.ViewHolder;

public class ProdutoAdapter extends RecyclerView.Adapter<ViewHolder> {
    ListProd listActivity;
    List<Produto> produtos;
    Context context;

    public ProdutoAdapter(ListProd listActivity, List<Produto> produtos) {
        this.listActivity = listActivity;
        this.produtos = produtos;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_prod, viewGroup, false);

        //handle item clicks
        ViewHolder viewHolder = new ViewHolder(itemView);
        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // this will be called when user click item
                String nome = produtos.get(position).getNome();
                String tipo = produtos.get(position).getTipo();
                String preco = produtos.get(position).getPreco();
                Toast.makeText(listActivity, nome + " " + tipo + " " + preco, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // this will be called when user long click item
                AlertDialog.Builder builder = new AlertDialog.Builder(listActivity);
                String [] options = {"Update","Delete"};
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,@SuppressLint("RecyclerView") int i){

        holder.txt_nome.setText(produtos.get(i).getNome());
        holder.txt_tipo.setText(produtos.get(i).getTipo());
        holder.txt_preco.setText(produtos.get(i).getPreco());
        String image = holder.txt_nome.getText().toString();
        if(image.equals("Smartphone")){
            holder.icon.setImageResource(R.drawable.ic_phone);
        }
        if(image.equals("Tablet")){
            holder.icon.setImageResource(R.drawable.ic_tablet);
        }
        if(image.equals("Notebook")){
            holder.icon.setImageResource(R.drawable.ic_computer);
        }
        
        holder.icon_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listActivity.deletar(i);
            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = produtos.get(i).getId();
                String nome = produtos.get(i).getNome();
                String tipo = produtos.get(i).getTipo();
                String des = produtos.get(i).getDescricao();
                String qtd = produtos.get(i).getQuantidade();
                String preco = produtos.get(i).getPreco();
                Log.d("ProdutoAdapter", id);
                listActivity.update(id, nome, tipo, des, preco, qtd);
            }
        });
    }

    @Override
    public int getItemCount(){
        return produtos.size();
    }

}
