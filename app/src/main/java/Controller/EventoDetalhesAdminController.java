package Controller;

import android.content.Context;

import DAO.EventoRepository;
import Model.Evento;

public class EventoDetalhesAdminController {
    private Context ctx;

    public EventoDetalhesAdminController(Context ctx) {
        this.ctx = ctx;
    }

    public void removerEvento(Evento evento) {
        EventoRepository db = new EventoRepository();
        db.deleteEvento(evento);
    }

}
