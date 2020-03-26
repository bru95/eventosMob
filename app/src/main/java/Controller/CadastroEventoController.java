package Controller;

import DAO.EventoRepository;
import Model.Evento;

public class CadastroEventoController {

    public CadastroEventoController() {

    }

    public boolean novoEvento(String nome, String descricao, String data, Double valor, Integer vagas, String local) {
        Evento e = new Evento(String.valueOf(System.currentTimeMillis()), nome, descricao, data, valor, vagas, local);
        EventoRepository db = new EventoRepository();
        db.addEvento(e);
        return true;
    }
}
