package dadm.quixada.ufc.projeto_itech.Produto;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dadm.quixada.ufc.projeto_itech.R;

class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtView_preco, txtView_descricaoProduto;
    ImageView imagemDoProduto;
    View view;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        view = itemView;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mClickListener.onItemLongClick(v, getAdapterPosition());
                return true;
            }
        });

        txtView_preco = view.findViewById(R.id.txtView_preco);
        txtView_descricaoProduto = view.findViewById(R.id.txtView_descricaoProduto);
        imagemDoProduto = view.findViewById(R.id.imagemDoProduto);

    }

    private ViewHolder.ClickListener mClickListener;

    public interface ClickListener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    void setOnClickListener(ViewHolder.ClickListener clickListener) {
        mClickListener = clickListener;
    }
}
