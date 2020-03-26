package Controller;

import DAO.EventoRepository;
import Model.Evento;

public class CadastroEventoController {

    private Evento eventoEdit;

    public CadastroEventoController(Evento ev) {
        this.eventoEdit = ev;
    }

    public boolean salvarEvento(String nome, String descricao, String data, Double valor, Integer vagas, String local) {
        if (eventoEdit == null) {
            this.novoEvento(nome, descricao, data, valor, vagas, local);
        } else {
            this.editEvento(nome, descricao, data, valor, vagas, local);
        }
        return true;
    }

    private void editEvento(String nome, String descricao, String data, Double valor, Integer vagas, String local) {
        eventoEdit.setNome(nome);
        eventoEdit.setDescricao(descricao);
        eventoEdit.setData(data);
        eventoEdit.setValor(valor);
        eventoEdit.setVagas(vagas);
        eventoEdit.setLocal(local);

        EventoRepository db = new EventoRepository();
        db.addEvento(eventoEdit);
    }

    private void novoEvento(String nome, String descricao, String data, Double valor, Integer vagas, String local) {
        Evento e = new Evento(String.valueOf(System.currentTimeMillis()), nome, descricao, data, valor, vagas, local);
        EventoRepository db = new EventoRepository();
        db.addEvento(e);
    }

    public Evento getEventoEdit() {
        return eventoEdit;
    }
}
