package dadm.quixada.ufc.projeto_itech.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dadm.quixada.ufc.projeto_itech.Components.Agendamento;
import dadm.quixada.ufc.projeto_itech.ListAgendamentos;
import dadm.quixada.ufc.bottom_navigation_view.R;
import dadm.quixada.ufc.projeto_itech.ViewHolderAgend;

public class AgendamentoAdapter extends RecyclerView.Adapter<ViewHolderAgend>{
    ListAgendamentos listAgendamentos;
    List<Agendamento> agendamentos;

    public AgendamentoAdapter(ListAgendamentos listAgendamentos, List<Agendamento> agendamentos) {
        this.listAgendamentos = listAgendamentos;
        this.agendamentos = agendamentos;
    }

    @NonNull
    @Override
    public ViewHolderAgend onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_agend, parent, false);

        ViewHolderAgend viewHolderAgend = new ViewHolderAgend(itemView);
        viewHolderAgend.setOnClickListener(new ViewHolderAgend.ClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String nome_agend = agendamentos.get(position).getNome();
                String desc = agendamentos.get(position).getDescricao();
                String data = agendamentos.get(position).getData();

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        return viewHolderAgend;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAgend holder, int position) {
        holder.nome_agen.setText(agendamentos.get(position).getNome());
        holder.desc_agen.setText(agendamentos.get(position).getDescricao());
        holder.data_agen.setText(agendamentos.get(position).getData());
        String image = agendamentos.get(position).getCheck();
        if(image.equals("concluido")){
            holder.icon.setImageResource(R.drawable.ic_check);
        }
        if(image.equals("pendente")){
            holder.icon.setImageResource(R.drawable.ic_report);
        }
        if(image.equals("excluido")){
            holder.icon.setImageResource(R.drawable.ic_delete_agend);
        }
    }

    @Override
    public int getItemCount() {
        return agendamentos.size();
    }
}




/*ArrayAdapter<Agendamento> {
    public AgendamentoAdapter(Context context, List<Agendamento> object){
        super(context, 0, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_agend,parent,false);
        }
        ImageView imageView;
        TextView _nome, _des, _data;
        ImageView iconImage = convertView.findViewById(R.id.iconCheck);
        //imageView = convertView.findViewById(R.id.iconeProdutos);
        _nome = convertView.findViewById(R.id.agen_nome);
        _des = convertView.findViewById(R.id.agen_des);
        _data = convertView.findViewById(R.id.agen_data);

        Agendamento agendamento = getItem(position);
        if(agendamento.getCheck().equals("pendente")){
            iconImage.setImageResource(R.drawable.ic_report);
        }
        if(agendamento.getCheck().equals("concluido")){
            iconImage.setImageResource(R.drawable.ic_check);
        }
        if(agendamento.getCheck().equals("excluido")){
            iconImage.setImageResource(R.drawable.ic_delete_agend);
        }
        _nome.setText(agendamento.getNome());
        _des.setText(agendamento.getDescricao());
        _data.setText(agendamento.getData());

        return convertView;
    }
}
*/