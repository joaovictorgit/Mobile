package dadm.quixada.ufc.projeto_itech.Agendamento;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dadm.quixada.ufc.projeto_itech.R;

public class ViewHolder extends RecyclerView.ViewHolder{

    TextView textView_tipoDispositivo, textView_modelo, textView_dia, textView_horario;
    ImageView imageView;
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

        textView_tipoDispositivo = view.findViewById(R.id.textView_tipoDispositivo);
        textView_modelo = view.findViewById(R.id.textView_modelo);
        textView_dia = view.findViewById(R.id.textView_dia);
        textView_horario = view.findViewById(R.id.textView_horario);
        imageView = view.findViewById(R.id.iconImage);

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
