package Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trabalhodesandroidupf.R;

import java.util.HashMap;
import java.util.List;

import Model.Participante;

public class adapterListaParticipantes extends BaseExpandableListAdapter {

    private List<String> lstGrupos;
    private HashMap<String, List<Participante>> lstItensGrupos;
    private Context context;

    public adapterListaParticipantes(Context context, List<String> grupos, HashMap<String, List<Participante>> itensGrupos) {
        // inicializa as variáveis da classe
        this.context = context;
        lstGrupos = grupos;
        lstItensGrupos = itensGrupos;
    }

    @Override
    public int getGroupCount() {
        // retorna a quantidade de grupos
        return lstGrupos.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // retorna a quantidade de itens de um grupo
        return lstItensGrupos.get(getGroup(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // retorna um grupo
        return lstGrupos.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // retorna um item do grupo
        return lstItensGrupos.get(getGroup(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        // retorna o id do grupo, porém como nesse exemplo
        // o grupo não possui um id específico, o retorno
        // será o próprio groupPosition
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        // retorna o id do item do grupo, porém como nesse exemplo
        // o item do grupo não possui um id específico, o retorno
        // será o próprio childPosition
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        // retorna se os ids são específicos (únicos para cada
        // grupo ou item) ou relativos
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        // cria os itens principais (grupos)

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        TextView tvGrupo = (TextView) convertView.findViewById(R.id.tv_titleParticipante);
        ImageView indicator = (ImageView) convertView.findViewById(R.id.indicator_group);

        tvGrupo.setText((String) getGroup(groupPosition));

        if (isExpanded) {
            indicator.setImageResource(R.drawable.ic_up);
        } else {
            indicator.setImageResource(R.drawable.ic_down);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        // cria os subitens (itens dos grupos)

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }

        TextView nome = (TextView) convertView.findViewById(R.id.tv_nomeParticipanteList);
        TextView email = (TextView) convertView.findViewById(R.id.tv_emailParticipanteList);

        Participante p = (Participante) getChild(groupPosition, childPosition);
        nome.setText(p.getNome());
        email.setText(p.getEmail());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // retorna se o subitem (item do grupo) é selecionável
        return true;
    }
}