package Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.trabalhodesandroidupf.MainActivity;
import com.example.trabalhodesandroidupf.R;

import java.util.ArrayList;

import Model.Evento;

public class adapterListaEventos extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<Evento> itens;
    private Context ctx;

    public adapterListaEventos(Context context, ArrayList<Evento> itens) {
        this.itens = itens;
        mInflater = LayoutInflater.from(context);
        this.ctx = context;
    }


    @Override
    public int getCount() {
        return this.itens.size();
    }

    @Override
    public Object getItem(int position) {
        return itens.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View row = view;
        RecordHolder holder = null;

        if (row == null) {
            row =  mInflater.inflate(R.layout.item_lista_evento, null);

            holder = new RecordHolder();
            holder.nomeEv = row.findViewById(R.id.tv_nomeEvento);
            holder.dataEv = row.findViewById(R.id.tv_dataEvento);
            holder.desc = row.findViewById(R.id.tv_descEvento);
            holder.more = row.findViewById(R.id.ib_more);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        final Evento item = itens.get(position);
        holder.nomeEv.setText(item.getNome());
        holder.dataEv.setText(item.getData());
        holder.desc.setText(item.getDescricao());

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ctx instanceof MainActivity) {
                    ((MainActivity)ctx).abreDetalhesEvento(item);
                }
            }
        });

        return row;
    }

    static class RecordHolder {
        TextView nomeEv;
        TextView dataEv;
        TextView desc;
        ImageButton more;
    }

}