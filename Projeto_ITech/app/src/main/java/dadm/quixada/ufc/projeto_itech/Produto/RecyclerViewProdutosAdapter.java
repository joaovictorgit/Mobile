package dadm.quixada.ufc.projeto_itech.Produto;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dadm.quixada.ufc.projeto_itech.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewProdutosAdapter extends RecyclerView.Adapter<ViewHolder>{

    private ArrayList<ProdutoModel> listaDeProdutos;
    private Produtos produtos;
    private StorageReference storageReference;

    public RecyclerViewProdutosAdapter(Produtos produtos, ArrayList<ProdutoModel> listaDeProdutos) {
        this.produtos = produtos;
        this.listaDeProdutos = listaDeProdutos;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.produto_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = listaDeProdutos.get(position).getId();
                String nome = listaDeProdutos.get(position).getNome();
                String preco = listaDeProdutos.get(position).getPreco();
                String descricao = listaDeProdutos.get(position).getDescricao();

                Intent intent = new Intent(produtos, ProdutoIndividual.class);
                intent.putExtra("id", id);
                intent.putExtra("nome", nome);
                intent.putExtra("preco", preco);
                intent.putExtra("descricao", descricao);

                produtos.startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference profileRef = storageReference.child("produtos/"+listaDeProdutos.get(position).getId()+"/profile.jpg");

        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.imagemDoProduto);
            }
        });

        holder.txtView_preco.setText(listaDeProdutos.get(position).getPreco());
        holder.txtView_descricaoProduto.setText(listaDeProdutos.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return listaDeProdutos.size();
    }


}
