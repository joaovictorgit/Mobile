package dadm.quixada.ufc.projeto_itech;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dadm.quixada.ufc.bottom_navigation_view.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    public TextView txt_nome;
    public TextView txt_tipo;
    public TextView txt_preco;
    public ImageView icon;
    public ImageView icon_delete;
    View mView;

    public ViewHolder(@NonNull View itemView){
        super(itemView);

        mView = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

        txt_nome = mView.findViewById(R.id.prod_nome);
        txt_tipo = mView.findViewById(R.id.prod_tipo);
        txt_preco = mView.findViewById(R.id.prod_preco);
        icon = mView.findViewById(R.id.iconeProdutos);
        icon_delete = mView.findViewById(R.id.delete);
    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }
}









/*ArrayAdapter<Produto> {
    FirebaseFirestore db;

    public ProdutoAdapter(Context context, List<Produto> object){
        super(context, 0, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_prod,parent,false);
        }
        ImageView imageView, update, delete;
        TextView _nome, _tipo, _preco;
        db = FirebaseFirestore.getInstance();
        imageView = convertView.findViewById(R.id.iconeProdutos);
        update = convertView.findViewById(R.id.update);
        delete = convertView.findViewById(R.id.delete);

        _nome = convertView.findViewById(R.id.prod_nome);
        _tipo = convertView.findViewById(R.id.prod_tipo);
        _preco = convertView.findViewById(R.id.prod_preco);

        Produto produto = getItem(position);
        if(produto.getTipo().equals("Smartphone")){
            imageView.setImageResource(R.drawable.ic_phone);
        }
        if(produto.getTipo().equals("Tablet")){
            imageView.setImageResource(R.drawable.ic_tablet);
        }
        if(produto.getTipo().equals("Notebook")){
            imageView.setImageResource(R.drawable.ic_pc);
        }
        _nome.setText(produto.getNome());
        _tipo.setText(produto.getTipo());
        _preco.setText(produto.getPreco());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("ProdutoAdapter", "Deu bom");
            }
        });
        ListProd lista = new ListProd();
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(convertView.getContext(), ListProd.class);
                //intent.putExtra("index", position);
                lista.deletar(position);
            }
        });

        return convertView;
    }




}*/
