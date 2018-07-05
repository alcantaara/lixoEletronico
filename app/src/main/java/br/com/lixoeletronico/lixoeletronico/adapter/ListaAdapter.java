package br.com.lixoeletronico.lixoeletronico.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import br.com.lixoeletronico.lixoeletronico.PerfilActivity;
import br.com.lixoeletronico.lixoeletronico.R;
import br.com.lixoeletronico.lixoeletronico.modelo.Agenda;

/**
 * Created by Marina on 29/05/2018.
 */

public class ListaAdapter extends BaseAdapter {

    private final List<Agenda> agendas;
    private final Context context;

    public ListaAdapter(Context context, List<Agenda> agendas) {
        this.context = context;
        this.agendas = agendas;
    }

    @Override
    public int getCount() {
        return agendas.size();
    }

    @Override
    public Object getItem(int i) {
        return agendas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        Agenda agenda = agendas.get(i);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_agenda, null);

        TextView campoSubCategoria = (TextView) view.findViewById(R.id.item_subCategoria);
        campoSubCategoria.setText(agenda.getSubCategoria());

        TextView campoQuantidade = (TextView) view.findViewById(R.id.item_quantidade);
        campoQuantidade.setText(agenda.getQuantidade());

        TextView campoData = (TextView) view.findViewById(R.id.item_data);
        campoData.setText(agenda.getData());

       ImageView campoFoto = (ImageView)view.findViewById(R.id.item_foto);
        String caminhoFoto = agenda.getFoto();

        if(caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduz = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            campoFoto.setImageBitmap(bitmapReduz);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        return view;
    }
}
