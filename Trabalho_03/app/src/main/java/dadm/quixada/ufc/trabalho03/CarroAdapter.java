package dadm.quixada.ufc.trabalho03;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CarroAdapter extends ArrayAdapter<Carro> {
    public CarroAdapter(Context context, List<Carro> object){
        super(context,0, object);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.item_cars,parent,false);
        }

        TextView _nome, _marca, _cor;

        _nome = convertView.findViewById(R.id.car_nome);
        _marca = convertView.findViewById(R.id.car_marca);
        _cor = convertView.findViewById(R.id.car_cor);

        Carro carro = getItem(position);

        _nome.setText(carro.getNome());
        _marca.setText(carro.getMarca());
        _cor.setText(carro.getCor());

        return convertView;

    }
}
