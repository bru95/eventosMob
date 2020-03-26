package Controller;

import android.content.Context;

import DAO.EventoRepository;
import Model.Evento;

public class EventoDetalhesAdminController {
    private Context ctx;
    private Evento evento;

    public EventoDetalhesAdminController(Context ctx, Evento evento) {
        this.ctx = ctx;
        this.evento = evento;
    }

    public void removerEvento() {
        EventoRepository db = new EventoRepository();
        db.deleteEvento(this.evento);
    }

    public Evento getEvento() {
        return this.evento;
    }

}
