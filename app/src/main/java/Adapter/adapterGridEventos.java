package Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trabalhodesandroidupf.EventoAdminActivity;
import com.example.trabalhodesandroidupf.MainActivity;
import com.example.trabalhodesandroidupf.R;

import java.util.ArrayList;

import Model.Evento;

public class adapterGridEventos extends ArrayAdapter<Evento> {

    Context context;
    int layoutResourceId;
    ArrayList<Evento> data = new ArrayList<Evento>();

    public adapterGridEventos(Context context, int layoutResourceId, ArrayList<Evento> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new RecordHolder();
            holder.nomeEv = (TextView) row.findViewById(R.id.tv_eventoAdmin);
            holder.dataEv = (TextView) row.findViewById(R.id.tv_quandoAdmin);
            holder.localEv = (TextView) row.findViewById(R.id.tv_ondeAdmin);
            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }

        final Evento item = data.get(position);
        holder.nomeEv.setText(item.getNome());
        holder.dataEv.setText(item.getData());
        holder.localEv.setText(item.getLocal());

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (context instanceof EventoAdminActivity) {
                    ((EventoAdminActivity)context).startDetalhesAdminActivity(item);
                }
            }
        });
        return row;

    }

    static class RecordHolder {
        TextView nomeEv;
        TextView dataEv;
        TextView localEv;
    }
}