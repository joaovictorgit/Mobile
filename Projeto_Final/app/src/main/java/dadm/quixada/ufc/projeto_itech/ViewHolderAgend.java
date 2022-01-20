package dadm.quixada.ufc.projeto_itech;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dadm.quixada.ufc.bottom_navigation_view.R;

public class ViewHolderAgend extends RecyclerView.ViewHolder{
    public TextView nome_agen;
    public TextView desc_agen;
    public TextView data_agen;
    public ImageView icon;
    View aView;

    public ViewHolderAgend(@NonNull View itemView) {
        super(itemView);

        aView = itemView;

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

        nome_agen = aView.findViewById(R.id.agen_nome);
        desc_agen = aView.findViewById(R.id.agen_des);
        data_agen = aView.findViewById(R.id.agen_data);
        icon = aView.findViewById(R.id.iconCheck);
    }

    private ViewHolderAgend.ClickListener mClickListener;

    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolderAgend.ClickListener clickListener){
        mClickListener = clickListener;
    }
}
