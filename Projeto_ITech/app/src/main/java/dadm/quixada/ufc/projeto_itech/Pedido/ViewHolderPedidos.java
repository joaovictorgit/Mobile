package dadm.quixada.ufc.projeto_itech.Pedido;



import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dadm.quixada.ufc.projeto_itech.Agendamento.ViewHolder;
import dadm.quixada.ufc.projeto_itech.R;

public class ViewHolderPedidos extends RecyclerView.ViewHolder{

    TextView textView_nome, textView_preco, textView_tipo;
    View view;

    public ViewHolderPedidos(@NonNull View itemView) {
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

        textView_nome = view.findViewById(R.id.textView_nome);
        textView_preco = view.findViewById(R.id.textView_preco);
        textView_tipo = view.findViewById(R.id.textView_tipo);


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