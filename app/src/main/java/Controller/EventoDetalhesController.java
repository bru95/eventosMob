package Controller;

import android.content.Context;

import DAO.EventoRepository;
import Model.Evento;
import Model.Participante;
import Utils.sharedPreferencesController;

public class EventoDetalhesController {

    private Context ctx;
    private Evento evento;

    public EventoDetalhesController(Context ctx, Evento evento) {
        this.ctx = ctx;
        this.evento = evento;
    }


    public void insereParticipante(Evento ev, String nome, String email) {
        sharedPreferencesController shared = new sharedPreferencesController(this.ctx);
        shared.setInfoParticipante(nome, email);

        ev.addParticipante(new Participante(nome, email));

        EventoRepository db = new EventoRepository();
        db.updateEvento(ev);

    }

    public Evento getEvento() {
        return this.evento;
    }
}
